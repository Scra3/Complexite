
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;

public class Minisat {

    /**
     * @param args the command line arguments
     */
    String minisatLine;
    List<Pair<Integer, Integer>> pairList = new ArrayList<Pair<Integer, Integer>>();
    static int nbArretes = 1000;
    public void Minisat() {

    }

    public String getMinisatLine() {
        return minisatLine;
    }

    public void setMinisatLine(String minisatLine) {
        if (this.minisatLine == null) {
            this.minisatLine = minisatLine;

        } else {
            this.minisatLine = this.minisatLine + minisatLine;
        }
    }

    public List<Pair<Integer, Integer>> getPairList() {
        return pairList;
    }

    public void setPairList(List<Pair<Integer, Integer>> pairList) {
        this.pairList = pairList;
    }

    public void trois_col(int G[]) {
        // initialisation
        int tableauSave[][] = new int[G[0]][3];
        int j = 0;
        // debut algo
        this.setMinisatLine("p cnf");
        int r = G[0] * 3;
        this.setMinisatLine(" " + Integer.toString(r));
        r = G[0] + (G[1] * 3);
        this.setMinisatLine(" " + Integer.toString(r) + "  \n");

        for (int i = 2; i < G.length; i = i + 2) {
            this.getPairList().add(new Pair<Integer, Integer>(G[i], G[i + 1]));
        }

        for (int i = 0; i < G[0]; i++) {
            for (int c = 0; c < 3; c++) {
                j++;
                this.setMinisatLine(j + " ");
                tableauSave[i][c] = j;
            }
            this.setMinisatLine("0 \n");

        }

        for (int i = 0; i < this.getPairList().size(); i++) {

            for (int k = 0; k < 3; k++) {
                String v = Integer.toString(tableauSave[this.getPairList().get(i).getKey() - 1][k]);
                String n = Integer.toString(tableauSave[this.getPairList().get(i).getValue() - 1][k]);
                this.setMinisatLine("-" + v + " " + "-" + n + " 0 \n");
            }
        }
    }

    public void getCouleur(String reponse) {

        String[] splitReponse = reponse.split(" ");

        Integer[][] couples = new Integer[splitReponse.length / 3][3];

        String rouge = "rouge";
        String vert = "vert";
        String bleu = "bleu";

        int a = -1;
        for (int i = 0; i < splitReponse.length / 3; i++) {
            for (int j = 0; j < 3; j++) {
                a++;
                couples[i][j] = Integer.parseInt(splitReponse[a]);
            }

        }

        for (int i = 0; i < couples.length; i++) {
            for (int j = 0; j < 3; j++) {
                if (couples[i][j] > 0) {
                    int x = i;
                    x = x + 1;
                    if (j == 0) {

                        System.out.println(" Sommet " + x + " : couleur : " + rouge);
                    }
                    if (j == 1) {
                        System.out.println(" Sommet " + x + " : couleur : " + vert);
                    }
                    if (j == 2) {
                        System.out.println(" Sommet " + x + " : couleur : " + bleu);
                    }
                }
            }
        }

    }

 

    public int[] genererGraphe(int sommets) {

        int arretes = 0;
        int sommetA = 0;
        int sommetB = 0;
        
        int a = 1;
        



        // generer arretes
        arretes = (int) (Math.random() * (nbArretes)) + 1;
        nbArretes = arretes;
        int tailleG = 2 + (2 * arretes);
        int G[] = new int[tailleG];
        
        G [0] = sommets;
        G [1] = arretes;
        
        // generer couples
        for (int i = 0; i < arretes; i++) {
            sommetA = (int) (Math.random() * (sommets )) + 1;
            sommetB = (int) (Math.random() * (sommets )) + 1;
            G[a + 1] = sommetA;
            G[a + 2] = sommetB;
            a = a + 2;
        }
        return G;
    }

    public void printLinuxCommand(String command) throws Exception {
        System.out.println(command);
        String line = "";
        Process process = Runtime.getRuntime().exec(command);
        Reader r = new InputStreamReader(process.getInputStream());
        BufferedReader in = new BufferedReader(r);

        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }

        in.close();
    }

    public String lireFichier(File fichier) {
        String chaine = "";

        try {
            FileInputStream ips = new FileInputStream(fichier);
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne = "";
            while ((ligne = br.readLine()) != null) {
                System.out.println(ligne);
                chaine += ligne + "\n";
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return chaine;
    }

    public static void main(String[] args) throws Exception {
        Minisat minisat = new Minisat();
        //int G[]  = null ;
        
        // générer graphe
        int[] G = minisat.genererGraphe(1000);
        // graphe satisf
	// = {6, 10, 1, 2, 2, 3, 3, 4, 4, 1, 5, 2, 5, 3, 5, 4, 5,1,1,6,2,6};


        for(int i = 0; i < G.length; i++){
        	System.out.print(G[i]+ " " );
        }
        if (args.length < 1) {

            String chemin = "output.cnf";
            minisat.trois_col(G);

            File fichier = new File(chemin);

            // Creation du fichier
            fichier.createNewFile();

            FileWriter writer = new FileWriter(fichier);
            try {
                writer.write(minisat.getMinisatLine());
            } catch (IOException e) {
                System.out.println("Impossible de creer le fichier");
            } finally {
                writer.close();
            }

            minisat.printLinuxCommand("minisat" + " " + fichier.getAbsolutePath() + " " + "debug.cnf");
            minisat.printLinuxCommand("java Minisat debug.cnf");

        } else {
            String chemin = "debug.cnf";
            File fichier = new File(chemin);

            String contenu = minisat.lireFichier(fichier);
            String[] reponse = contenu.split("\n");

            if (reponse.length > 1) {
                minisat.printLinuxCommand("clear");
                System.out.println("Le graphe est SATISFAISABLE");
                System.out.println("Coloriage : ");
                minisat.getCouleur(reponse[1]);
		System.out.println("Nombre arretes : " + nbArretes);
		System.out.println("Sommets : 1000" );

            } else {
                minisat.printLinuxCommand("clear");
                System.out.println("Le graphe est INSATISFAISABLE");
            }
        }

    }

}
