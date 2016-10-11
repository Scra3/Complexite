/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minisat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;

/**
 *
 * @author Scra
 */
public class MiniSat {

    /**
     * @param args the command line arguments
     */
    String minisatLine;
    List<Pair<Integer, Integer>> pairList = new ArrayList<Pair<Integer, Integer>>();

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
                String v = Integer.toString(tableauSave[this.getPairList().get(i).getKey()-1][k]);
                String n = Integer.toString(tableauSave[this.getPairList().get(i).getValue()-1][k]);
                this.setMinisatLine("-" + v + " " + "-" + n + " 0 \n"); 
            }
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        MiniSat minisat = new MiniSat();
        
        int G[] = {6, 11, 1, 2, 2, 3, 3, 4, 4, 1, 5, 2, 5, 3, 5, 4, 5, 6,5,1,1,6,2,6};
        minisat.trois_col(G);
        System.out.println(minisat.getMinisatLine());
    }

}
