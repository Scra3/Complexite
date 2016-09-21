package tp1;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Scra
 */
public class TP1 {

    /**
     * @param args the command line arguments
     */
    public Map algorithmeSimple(int tableau[]) {
        Map<String, Integer> sousSequenceT1 = new HashMap<String, Integer>();
        int sauvegarde = 0; // permet de sauvegarde la valeur max de la séquence
        int resultat = 0; // variable tampon stockant le resultat de la séquence
        int l, i, j;

        for (l = 0; l < tableau.length; l++) {
            for (i = l; i < tableau.length; i++) {
                for (j = l; j <= i; j++) {
                    resultat = tableau[j] + resultat;
                }
                if (sauvegarde < resultat) {
                    sauvegarde = resultat;
                    sousSequenceT1.put("MAX", i);
                    sousSequenceT1.put("MIN", l);
                }
                resultat = 0;
            }
        }
        return sousSequenceT1;
    }

    public Map algorithmeOptimise(int tableau[]) {
        Map<String, Integer> sousSequenceT1 = new HashMap<String, Integer>();
        int sauvegarde = -100000; // permet de sauvegarde la valeur max de la séquence
        int resultat = 0; // variable tampon stockant le resultat de la séquence
        int l, i, j;

        for (l = 0; l < tableau.length; l++) {
            for (i = l; i < tableau.length; i++) {
                resultat = resultat + tableau[i];
                if (sauvegarde < resultat) {
                    sauvegarde = resultat;
                    sousSequenceT1.put("MAX", i);
                    sousSequenceT1.put("MIN", l);
                }
            }
            resultat = 0;
        }
        sousSequenceT1.put("SUM", sauvegarde);
        return sousSequenceT1;
    }

    static Map<String, Integer> sequence = new HashMap<String, Integer>();

    public Map diviserRegnerMax(int tableau[], Map<String, Integer> sequenceMax) {

        // DECLARATIONS 
        Map<String, Integer> sousSequenceT1 = new HashMap<String, Integer>();
        Map<String, Integer> sousSequenceT2 = new HashMap<String, Integer>();
        Map<String, Integer> sousSequence = new HashMap<String, Integer>();

        int[] T1 = null;
        int[] T2 = null;
        int sum = 0;
        int min = 0;
        int max = 0;
        int mediane = 0;

        //TRAITEMENTS
        if (tableau.length < 4) {
            sousSequenceT1 = algorithmeOptimise(tableau);
        } else {
            if (tableau.length % 2 == 0) {

                mediane = tableau.length / 2 - 1;

                T1 = new int[(int) Math.floorDiv(tableau.length, 2)];
                T2 = new int[tableau.length / 2];

                for (int i = 0; i < Math.floorDiv(tableau.length, 2); i++) {
                    T1[i] = tableau[i];
                }
                int a = 0;

                for (int i = Math.floorDiv(tableau.length, 2); i < tableau.length; i++) {
                    T2[a] = tableau[i];
                    a++;
                }

            }
            if (tableau.length % 2 != 0) {

                mediane = Math.floorDiv(tableau.length, 2);

                T1 = new int[Math.floorDiv(tableau.length, 2)];
                T2 = new int[Math.floorDiv(tableau.length, 2) + 1];

                for (int i = 0; i < Math.floorDiv(tableau.length, 2); i++) {
                    T1[i] = tableau[i];
                }

                int a = 0;
                for (int i = Math.floorDiv(tableau.length, 2); i < tableau.length; i++) {
                    T2[a] = tableau[i];
                    a++;
                }
            }

            sousSequenceT1 = diviserRegnerMax(T1, sequenceMax);
            sousSequenceT1 = caculerMediane(true, sousSequenceT1, mediane, tableau.length);
            sousSequenceT2 = diviserRegnerMax(T2, sequenceMax);
            sousSequenceT2 = caculerMediane(false, sousSequenceT2, mediane, tableau.length);
            sousSequence = calculerChevauchement(tableau, sousSequenceT1, sousSequenceT2);
            sauvegarderMax(sousSequence);
        }
        return sousSequenceT1;
    }

    public static Map<String, Integer> caculerMediane(boolean B, Map<String, Integer> sequence_1, int mediane, int tailleTableau) {
        Map<String, Integer> sequenceMax = new HashMap<String, Integer>();
        int sum1, min1, max1 = 0;

        max1 = sequence_1.get("MAX");
        min1 = sequence_1.get("MIN");
        sum1 = sequence_1.get("SUM");

        //T1
        if (B == true) {
            sequenceMax.put("MAX", max1);
            sequenceMax.put("MIN", min1);
            sequenceMax.put("SUM", sum1);
        } else {
            sequenceMax.put("MAX", mediane + max1);
            sequenceMax.put("MIN", mediane + min1);
            sequenceMax.put("SUM", sum1);
        }

        return sequenceMax;
    }

    public static void sauvegarderMax(Map<String, Integer> sousSequence) {
        if (sousSequence.get("SUM") > sequence.get("SUM") || sequence == null) {
            sequence.put("MAX", sousSequence.get("MAX"));
            sequence.put("MIN", sousSequence.get("MIN"));
            sequence.put("SUM", sousSequence.get("SUM"));
        }
    }

    public static Map<String, Integer> calculerChevauchement(int tableau[], Map<String, Integer> T1, Map<String, Integer> T2) {
        Map<String, Integer> sequenceChevauchement = new HashMap<String, Integer>();
        int sumCh, minCh, maxCh;

        sumCh = 0;
        minCh = T1.get("MIN");
        maxCh = T2.get("MAX");

        for (int i = minCh; i <= maxCh; i++) {
            sumCh = sumCh + tableau[i];
        }

        sequenceChevauchement.put("SUM", sumCh);
        sequenceChevauchement.put("MIN", minCh);
        sequenceChevauchement.put("MAX", maxCh);

        if (sumCh > T1.get("SUM") && sumCh > T2.get("SUM")) {
            return sequenceChevauchement;
        } else if (sumCh < T1.get("SUM") && T2.get("SUM") < T1.get("SUM")) {
            return T1;
        } else if (sumCh < T2.get("SUM") && T2.get("SUM") > T1.get("SUM")) {
            return T2;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        TP1 algos = new TP1();
        // TODO code application logic here

        Map<String, Integer> sequenceMax = new HashMap<>();
        sequence.put("MAX", 0);
        sequence.put("MIN", 0);
        sequence.put("SUM", -100000);
        int T[] = new int[10];
        T[0] = 44;
        T[1] = -54;
        T[2] = -100;
        T[3] = 62;
        T[4] = -65;
        T[5] = 82;
        T[6] = 81;
        T[7] = 82;
        T[8] = 86;
        T[9] = 64;

        algos.diviserRegnerMax(T, sequenceMax);
        System.out.println(sequence);
    }

}
