

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Scra
 */
public class algo3 {

    static Map<String, Integer> sequenceT1 = new HashMap<String, Integer>();
    static Map<String, Integer> sequenceT2 = new HashMap<String, Integer>();
    static Map<String, Integer> sequenceMax = new HashMap<String, Integer>();

    static int T[];
    static int tour = 0;

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

    public Map diviserRegnerMax(int tableau[], Map<String, Integer> sequenceMax) {

        // DECLARATIONS 
        //Sous séquence de gauche
        Map<String, Integer> sousSequenceT1 = new HashMap<String, Integer>();
        //Sous séquence de droite 
        Map<String, Integer> sousSequenceT2 = new HashMap<String, Integer>();
        // Sous déquence du millieu
        Map<String, Integer> sousSequence = new HashMap<String, Integer>();

        // les sous tableaux du tableau principal
        int[] T1 = null;
        int[] T2 = null;
        // variable d'opérandes et d'indices
        int sum = 0;
        int min = 0;
        int max = 0;
        //médiane du tableau courant
        int mediane = 0;
        tour++;
        //TRAITEMENTS
        //On vérifie si le tableau est inférieur à la longueur 4
        if (tableau.length < 4) {
            // on cherche la sous séquence qui nous retourne l' indice min l' indice max et la somme de la séquence
            sousSequenceT1 = algorithmeOptimise(tableau);

            if (tour == 1) {
                sequenceMax.put("SUM", sousSequenceT1.get("SUM"));
                sequenceMax.put("MIN", sousSequenceT1.get("MIN"));
                sequenceMax.put("MAX", sousSequenceT1.get("MAX"));
            }
            return sousSequenceT1;

        } else {

            // DIVISION DU TABLEAU COURANT
            // cacule de la médiane
            mediane = Math.floorDiv(tableau.length, 2) - 1;
            // cacule de la taille de T1
            T1 = new int[(int) Math.floorDiv(tableau.length, 2)];

            //Calcule de la taille de T2
            //Si tableau est paire on divise par deux 
            if (tableau.length % 2 == 0) {
                T2 = new int[tableau.length / 2];

            }// si il est imparaire on prend le planchet de la division et on rajoute 1 
            //: 5/2 = 2.5 => 2 +1 = 3 donc T1 sera de taille 2 et T2 de taille 3 
            else if (tableau.length % 2 != 0) {
                T2 = new int[Math.floorDiv(tableau.length, 2) + 1];
            }
            // Implémentation des tableaux
            for (int i = 0; i < Math.floorDiv(tableau.length, 2); i++) {
                T1[i] = tableau[i];
            }
            int a = 0;

            for (int i = Math.floorDiv(tableau.length, 2); i < tableau.length; i++) {
                T2[a] = tableau[i];
                a++;
            }

            sousSequenceT1 = diviserRegnerMax(T1, sequenceMax);
            sousSequenceT2 = diviserRegnerMax(T2, sequenceMax);

            // On recalcul les indices
            sousSequenceT1 = caculerMediane(true, sousSequenceT1, mediane, tableau.length);
            sousSequenceT2 = caculerMediane(false, sousSequenceT2, mediane, tableau.length);

            // on calcul le chevauchement et retourne le plus grand des trois sous séquences
            sousSequence = calculerChevauchement(tableau, sousSequenceT1, sousSequenceT2);

            // on sauvegarde la plus grande séquence
            sauvegarderMax(sousSequence);
            
            return sousSequence;
        }
    }

    public static Map<String, Integer> caculerMediane(boolean B, Map<String, Integer> sequence_1, int mediane, int tailleTableau) {
        Map<String, Integer> sequenceMax = new HashMap<String, Integer>();
        int sum1, min1, max1 = 0;

        max1 = sequence_1.get("MAX");
        min1 = sequence_1.get("MIN");
        sum1 = sequence_1.get("SUM");

        if (B == true) {
            sequenceMax.put("MAX", max1);
            sequenceMax.put("MIN", min1);
            sequenceMax.put("SUM", sum1);
        } else {
            sequenceMax.put("MAX", mediane + max1 + 1);
            sequenceMax.put("MIN", mediane + min1 + 1);
            sequenceMax.put("SUM", sum1);
        }

        return sequenceMax;
    }

    public static void sauvegarderMax(Map<String, Integer> sousSequence) {

        if (sousSequence.get("SUM") > sequenceMax.get("SUM")) {
            sequenceMax.put("MAX", sousSequence.get("MAX"));
            sequenceMax.put("MIN", sousSequence.get("MIN"));
            sequenceMax.put("SUM", sousSequence.get("SUM"));
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

        if (sumCh >= T1.get("SUM") && sumCh >= T2.get("SUM")) {
            return sequenceChevauchement;
        } else if (sumCh <= T1.get("SUM") && T2.get("SUM") <= T1.get("SUM")) {
            return T1;
            // (sumCh <= T2.get("SUM") && T2.get("SUM") >= T1.get("SUM"))
        } else {
            return T2;
        }
    }

    public static void main(String[] args) {

        algo3 algos = new algo3();
        //Initialisation des sequences 

        sequenceT1.put("MAX", 0);
        sequenceT1.put("MIN", 0);
        sequenceT1.put("SUM", 0);

        sequenceT2.put("MAX", 0);
        sequenceT2.put("MIN", 0);
        sequenceT2.put("SUM", 0);

        sequenceMax.put("MAX", 0);
        sequenceMax.put("MIN", 0);
        sequenceMax.put("SUM", 0);

        // TODO code application logic here
    
	if (args.length == 0) {
			System.out.println("ERROR : ARGUMENTS MANQUANT. SYNTAXE DEMANDEE: A B....X");
			System.exit(1);
		}else{
			T = new int[args.length];
			for (int i = 0; i < args.length; i++) {
				T[i] = Integer.parseInt(args[i]);
			}
		}
		
	
	algos.diviserRegnerMax(T, sequenceMax);
		
	System.out.print(sequenceMax.get("MIN") +" "+sequenceMax.get("MAX"));

        // initialisation du tableau 



    }

}
