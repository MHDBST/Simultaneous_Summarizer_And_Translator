package wordNet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.SynsetType;
import edu.smu.tspell.wordnet.WordNetDatabase;

public class JAWS {

	public static void main(String[] args) throws Exception {
		new JAWS("earnings");
	}

	HashSet<String> allSynsets;

	final String dictDir = "C:/Program Files (x86)/WordNet/2.1/dict";
	int synsetLength;
	private ArrayList<String>[] synsetArr ;

	public JAWS(String w) throws Exception {
		System.setProperty("wordnet.database.dir", dictDir);
		allSynsets = new HashSet<String>();
		getSynonyms(w);

	}

	@SuppressWarnings("unchecked")
	public void getSynonyms(String noun) throws IOException {

		WordNetDatabase database = WordNetDatabase.getFileInstance();
		Synset[] synsets = database.getSynsets(noun, SynsetType.NOUN);
		synsetLength = synsets.length;
		synsetArr = new ArrayList[synsetLength];
		int i=0;
		
		for (Synset s : synsets) {
			synsetArr[i] = new ArrayList<String>();
//			System.err.println("hhii");
			// synsetsArr.add(s.toString());
			for (String s2 : s.getWordForms()) {
//				System.out.print(s2 + "    ");
				allSynsets.add(s2);
				
				synsetArr[i].add(s2);
//				System.out.println(synsetArr[i].size());
			}
			i++;
//			System.out.println();
		}
	}

	public int getSynsetSize() {
		return synsetLength;
	}

	public HashSet<String> getAllSynsets() {
		return allSynsets;
	}
	
	public ArrayList<String>[] getSynsetArr() {
//		System.out.println(synsetArr.length);
		return synsetArr;
	}

}
