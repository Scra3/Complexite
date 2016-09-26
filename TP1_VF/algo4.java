/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algo4;

/**
 *
 * @author b16007026
 */
public class Algo4 {

    static int T[];

    public static class LineaireValues {

        private int min, max, somme;

        public LineaireValues(int min, int max, int somme) {
            this.min = min;
            this.max = max;
            this.somme = somme;
        }

        public int getMin() {
            return min;
        }

        public int getMax() {
            return max;
        }

        public int getSomme() {
            return somme;
        }

        public String toString() {
            return getMin() + " " + getMax();
        }

    }

    public static LineaireValues algoLineaire(int tableau[]) {

        int m[] = new int[tableau.length];

        int maximum = tableau[0];
        int indiceMAX = 0, indiceMIN = m[0];
        int resultat = 0;
        int mImois1 = 0;
        int mI = 0;

        m[0] = tableau[0];
        for (int i = 1; i < tableau.length; i++) {
            mImois1 = m[i - 1];

            if (mImois1 >= 0) {
                m[i] = mImois1 + tableau[i];
            } else {
                m[i] = tableau[i];

            }

            if (m[i] > maximum) {

                if (maximum > m[i - 1]) {
                    indiceMIN = i;
                }
                maximum = m[i];

                indiceMAX = i;
            }

        }

        return new LineaireValues(indiceMIN, indiceMAX, maximum);
    }

    public static void main(String[] args) {

        Algo4 algos = new Algo4();

        /*if (args.length == 0) {
         System.out.println("ERROR : ARGUMENTS MANQUANT. SYNTAXE DEMANDEE: A B....X");
         System.exit(1);
         }else{
         T = new int[args.length];
         for (int i = 0; i < args.length; i++) {
         T[i] = Integer.parseInt(args[i]);
         }
         }*/
        T = new int[]{44,-54,-100,62,-65,82,-81,-82,86,-64};
        LineaireValues lv = Algo4.algoLineaire(T);
        System.out.println(lv);
    }
}



/
////////////////////

package tp1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author b16007026
 */
public class algo4 {

    public static void algo4() {
        int T[] = new int[]{68, 18, 1, 17, -29, 22, -74, 44, 9, -79, -15};

        int sommeMax = 0;
        int indiceMAX = 0;
        int indiceMIN = 0;
        int entierCourant = 0;
        int maximum = 0;

        for (int i = 0; i < T.length; i++) {

            entierCourant = T[i];
            // Cherche le max entre entierCourant et sommeMAX + entierCourant
            if (entierCourant > sommeMax) {
                // entierCourant devient le nouveau max
                sommeMax = entierCourant;
            } else {
                sommeMax = sommeMax + entierCourant;
            }
            // Si notre max change, alors il devient le nouvel indice minimum
            if (entierCourant > maximum) {
                indiceMIN = i;
            }
            
            if (maximum > sommeMax) {
                maximum = maximum;
            } else {
                maximum = sommeMax;
            }
            
            if (maximum == sommeMax) {
                indiceMAX = i;
            }
        }
        System.out.println(indiceMIN);

        System.out.println(indiceMAX);

    }

}


