import java.util.HashMap;
import java.util.Map;

public class algo2 {

	static Map<String, Integer> sequenceT1 = new HashMap<String, Integer>();
	static int T[];

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

	public static void main(String[] args) {
	
		algo2 algos = new algo2();
		
		if (args.length == 0) {
			System.out.println("ERROR : ARGUMENTS MANQUANT. SYNTAXE DEMANDEE: A B....X");
			System.exit(1);
		}else{
			T = new int[args.length];
			for (int i = 0; i < args.length; i++) {
				T[i] = Integer.parseInt(args[i]);
			}
		}
		System.out.print(algos.algorithmeOptimise(T).get("MIN") +" "+algos.algorithmeOptimise(T).get("MAX"));
	}
}
