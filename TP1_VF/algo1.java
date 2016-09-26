import java.util.HashMap;
import java.util.Map;

public class algo1 {

	static Map<String, Integer> sequenceT1 = new HashMap<String, Integer>();
	
	static int T[];
	
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

	public static void main(String[] args) {
	
		algo1 algos = new algo1();
		
		if (args.length == 0) {
			System.out.println("ERROR : ARGUMENTS MANQUANT. SYNTAXE DEMANDEE: A B....X");
			System.exit(1);
		}else{
			T = new int[args.length];
			for (int i = 0; i < args.length; i++) {
				T[i] = Integer.parseInt(args[i]);
			}
		}
		System.out.print(algos.algorithmeSimple(T).get("MIN") +" "+algos.algorithmeSimple(T).get("MAX"));
	}
}
