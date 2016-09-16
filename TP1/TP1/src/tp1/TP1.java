/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        Map<String, Integer> indices = new HashMap<String, Integer>();
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
                    indices.put("MAX", i);
                    indices.put("MIN", l);
                }
                resultat = 0;
            }
        }
        return indices;
    }

    public Map algorithmeOptimise(int tableau[]) {
        Map<String, Integer> indices = new HashMap<String, Integer>();
        int sauvegarde = 0; // permet de sauvegarde la valeur max de la séquence
        int resultat = 0; // variable tampon stockant le resultat de la séquence
        int l, i, j;

        for (l = 0; l < tableau.length; l++) {
            for (i = l; i < tableau.length; i++) {
                resultat = resultat + tableau[i];
                if (sauvegarde < resultat) {
                    sauvegarde = resultat;
                    indices.put("MAX", i);
                    indices.put("MIN", l);
                }
            }
            resultat = 0;
        }
        return indices;
    }

    public Map diviserRegnerMax(int tableau[]) {
        Map<String, Integer> indices = new HashMap<String, Integer>();
        int[] T1 = null;
        int[] T2 = null;

        if (tableau.length <= 1) {
            // calculer Max
            indices = algorithmeOptimise(tableau);
        } else {
            if (tableau.length > 1 && tableau.length % 2 == 0) {

                T1 = new int[(int) Math.floorDiv(tableau.length, 2)];
                T2 = new int[tableau.length / 2];

                for (int i = 0; i < Math.floorDiv(tableau.length, 2); i++) {
                    T1[i] = tableau[i];
                }

                int a = 0;
                for (int i = tableau.length - 1; i >= tableau.length / 2; i--) {
                    T2[a] = tableau[i];
                    a++;
                }
                for (int i = 0;
                        i < tableau.length / 2; i++) {
                    System.out.print(" " + T1[i] + " ");

                }

                System.out.println("");

                for (int i = 0;
                        i < tableau.length / 2; i++) {
                    System.out.print(" " + T2[i] + " ");

                }

                System.out.println("");

            }
            if (tableau.length > 1 && tableau.length % 2 != 0) {

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
                for (int i = 0;
                        i < tableau.length / 2; i++) {
                    System.out.print(" " + T1[i] + " ");

                }

                System.out.println("");

                for (int i = 0; i < Math.floorDiv(tableau.length, 2) + 1; i++) {
                    System.out.print(" " + T2[i] + " ");

                }

                System.out.println("");

            }
            diviserRegnerMax(T1);
            diviserRegnerMax(T2);
        }
        return indices;
    }

    public static void main(String[] args) {
        TP1 algos = new TP1();
        // TODO code application logic here
        int T[] = new int[10];
        T[0] = 44;
        T[1] = -54;
        T[2] = -100;
        T[3] = 62;
        T[4] = -65;
        T[5] = 82;
        T[6] = -81;
        T[7] = -82;
        T[8] = 86;
        T[9] = -64;

        System.out.println(algos.diviserRegnerMax(T));
    }

}
