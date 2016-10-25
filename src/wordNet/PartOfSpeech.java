package wordNet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.aliasi.hmm.HiddenMarkovModel;
import com.aliasi.hmm.HmmDecoder;
import com.aliasi.tag.Tagging;
import com.aliasi.tokenizer.RegExTokenizerFactory;
import com.aliasi.tokenizer.Tokenizer;
import com.aliasi.tokenizer.TokenizerFactory;
import com.aliasi.util.Streams;

public class PartOfSpeech {

	public static void main(String[] args) throws ClassNotFoundException,
			IOException {
		new PartOfSpeech("I have a books children women saw");
	}

	static final int N_GRAM = 8;
	static final int NUM_CHARS = 256;
	static final double LAMBDA_FACTOR = 8.0;

	HashSet<String> nouns;// ////// yek hash set baraye negah darie noun haye
							// mojud dar jomle
	HashMap<String, HashSet<String>> syns; // ////// yek hash set baraye negah
											// darie synset haye
	// tamae kalamate mojud dar jomle

	HashMap<String, ArrayList<String>[]> nounMap;

	final String modelDir = "C:/Users/MsB/Desktop/proje/lingpipe/pos-en-general-brown.HiddenMarkovModel";

	Tagging<String> tagging;

	@SuppressWarnings("deprecation")
	public PartOfSpeech(String s) throws IOException, ClassNotFoundException {
		TokenizerFactory TOKENIZER_FACTORY = new RegExTokenizerFactory(
				"(-|'|\\d|\\p{L})+|\\S");

		FileInputStream fileIn = new FileInputStream(modelDir);
		ObjectInputStream objIn = new ObjectInputStream(fileIn);
		HiddenMarkovModel hmm = (HiddenMarkovModel) objIn.readObject();
		Streams.closeInputStream(objIn);

		HmmDecoder decoder = new HmmDecoder(hmm);

		char[] cs = s.toCharArray();
		Tokenizer tokenizer = TOKENIZER_FACTORY.tokenizer(cs, 0, cs.length);
		String[] tokens = tokenizer.tokenize();
		List<String> tokenList = Arrays.asList(tokens);
		tagging = decoder.tag(tokenList);
		setNouns();

	}

	public HashSet<String> setNouns() {
		nouns = new HashSet<String>();

		for (int i = 0; i < tagging.size(); ++i) {
			// System.out.println("hereeeeeeeeee");
			if ("nn".equals(tagging.tag(i)) || "nns".equals(tagging.tag(i)))
				nouns.add(tagging.token(i));
			// System.out.println(tagging.token(i)+"  " + tagging.tag(i));
		}

		return nouns;
	}

	public HashMap<String, HashSet<String>> createSynHash() throws Exception {
		syns = new HashMap<String, HashSet<String>>();
//		nounMap = new HashMap<String, Integer>();
		nounMap = new HashMap<String, ArrayList<String>[]>();
		
		
		JAWS jaws;
		for (String s : nouns) {
//			System.out.println(s);
			jaws = new JAWS(s);
			jaws.getSynsetSize();
//			nounMap.put(s, jaws.getSynsetSize());
			nounMap.put(s, jaws.getSynsetArr());
//			System.out.println(s + " " + nounMap.size());
			
			
			// syns.addAll(jaws.getSynsetsArr());
			syns.put(s, jaws.getAllSynsets());
//			jaws.getSynsetSize();
			// System.out.println(jwi.getSynsetsArr());
		}
		return syns;

	}

	public HashSet<String> getNouns() {
		return nouns;
	}

	public HashMap<String, ArrayList<String>[]> getNounMap() {
		return nounMap;
	}

}
