
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		return str.substring(1, str.length());
	}

	public static int levenshtein(String word1, String word2) {
		
		// make sure all the words are lover case
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();

		if(word1.isEmpty()){
			return word2.length();
		}

		if(word2.isEmpty()){
			return word1.length();
		}

		// 
		if(word1.charAt(0) == word2.charAt(0)){
			// immediately return the value of levenshtein and do not reach the last return statment
			return levenshtein(tail(word2), tail(word1));
		}

		int deletions = levenshtein(tail(word1), word2); 
		int insertions = levenshtein(word1, tail(word2));
		int substitutions = levenshtein(tail(word1), tail(word2));

		return 1 + Math.min(Math.min(deletions, insertions),substitutions);
		
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		for(int i = 0; i < dictionary.length; i++){
			dictionary[i] = in.readString();
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		// assume that the first word has minimum distance
		int min = levenshtein(word, dictionary[0]);
		String lookAlike = dictionary[0];

		for(int i = 1; i < dictionary.length; i++){
			// if there is a word that more similar to the given word store it.
			if (min > levenshtein(word, dictionary[i])){
				min = levenshtein(word, dictionary[i]);
				lookAlike = dictionary[i];
			}
		}

		if(min > threshold){
			return word;
		}
		else{
			return lookAlike;
		}
		
	}

}
