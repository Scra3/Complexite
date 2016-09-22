package tp1b;

public class TP1B {

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
            return "LineaireValues[" + getMin() + ", " + getMax() + ", " + getSomme() + "]";
        }

    }

    public static LineaireValues algoLineaire(int tableau[]) {

        int m[] = new int[tableau.length];

        int maximum = tableau[1];
        int indiceMAX = 0, indiceMIN = 0;

        m[1] = tableau[1];
        for (int i = 2; i < tableau.length; i++) {
            if (m[i - 1] > 0) {
                m[i] = m[i - 1] + tableau[i];
            } else {
                m[i] = tableau[i];

                if (m[i] > m[i + 1]) {

                    indiceMIN = i;
                }
            }
            if (m[i] > maximum) {
                maximum = m[i];
                indiceMAX = i;
            }
        }

        return new LineaireValues(indiceMIN, indiceMAX, maximum);
    }

    public static void main(String[] args) {
        TP1B algos = new TP1B();
        int[] T = new int[10];
        T[0] = 100;
        T[1] = 80;
        T[2] = 50;
        T[3] = 0;
        T[4] = 50;
        T[5] = 0;
        T[6] = -10;
        T[7] = 10;
        T[8] = 10;
        T[9] = 10;

        int T2[] = new int[]{1000, 101, -102, 2, 3};

        LineaireValues lv = TP1B.algoLineaire(T2);
        System.out.println(lv);
    }
}
