

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		for(int i = 0; i < dictionary.length; i++){
			dictionary[i] = in.readString();
		}

		return dictionary;
	}

	public static boolean existInDictionary(String word, String []dictionary) {
		for(int i = 0; i < dictionary.length; i++){
			if(dictionary[i].equals(word))
				return true;
		}
		
		return false;
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {

		// Base case: do nothing (return) if hashtag is an empty string.
        if (hashtag.isEmpty()) {
            return;
        }
		
        int N = hashtag.length();
		// make sure all the letters are lower cases
		hashtag = hashtag.toLowerCase();

		int newStart = 0;
		// a loop that "finds" the words
        for (int i = 1; i <= N; i++) {
			String word = hashtag.substring(0 , i);
			// if the loop found a word, call the function again and make the word shorter
			if(existInDictionary(word, dictionary) == true){
				newStart = i;
				System.out.println(word);
				break;
			}
        }
		if (newStart != 0){
			breakHashTag(hashtag.substring(newStart, N), dictionary);
		
		return;
		
    }

}
