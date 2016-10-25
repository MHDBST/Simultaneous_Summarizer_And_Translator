package summarization;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

import pageRank.Graph;
import pageRank.PageRank;
import wordNet.PartOfSpeech;

public class BasicSteps {

	private final static int size = 12;
	String text; // kole matn
	static ArrayList<Data> datum; // class do bodi az jome, emtiazha
	double[] sc; // araye komaki baraye negahdari emtiaze har jomle
	int[] length; // araye negah darande tule har jomle
	private HashSet<String> stWoHash; // hash baraye negah darie stop word ha
	private HashSet<String> emWoHash; // hash baraye negah darie emphasize word
										// ha
	private Stemmer s;
	String textLowered; // case sensetivi az bein bere
	static HashMap<String, Integer> words; // kalamat ba tedade tekrareshun
											// (aval adad nabuda jozeshun alan
											// hast)
	private int maxTerm = 0; // tedad tekrare kalame ba bishtarin tekrar dar
								// kole matn
	HashSet<String> titleWords; // negahdarande kalamate title baraye emale
								// marhale 5

	ArrayList<String> stemWrods; // baraye marhale 12, tamame kalamate gheire
									// stop word va stem shode yek jomle ra
									// negah dari mikonad va ba an graph misazad
									// ta page rank ra ruye an emal konad

	Graph g; // graphe marbut b 12
	PageRank pr;

	NameEntity ne; // //// for 8

	public BasicSteps(String text) {

		this.text = text;
		sc = new double[size];
		datum = new ArrayList<Data>();
		stWoHash = new HashSet<String>();
		emWoHash = new HashSet<String>();
		s = new Stemmer();
		words = new HashMap<String, Integer>();
		titleWords = new HashSet<String>();
		g = new Graph();
		ne = new NameEntity();

	}

	public void steps() throws Exception {
		countWords();
		// StringTokenizer st = new StringTokenizer(text, ".\n");
		StringTokenizer st = new StringTokenizer(text, "\n");
		int index = 0; // // tedade jomalat
		String sentence;
		int maxLenght = 1;
		// onvan ro az hame joda kardam , in do khat baraye marhae 5
		clearTitle(st.nextToken());
		int titleSize = titleWords.size();

		while (st.hasMoreTokens()) {
			index++;
			sentence = new String(st.nextToken());
			Data data = new Data(size);
			data.setSentence(sentence);
			float s0 = sentenceLocation(index);
			data.setScoresI(0, s0);
			for (int i = 0; i < 12; i++)
				data.setScoresI(i, 0);

			int l = calLength(sentence);
			if (l > maxLenght)
				maxLenght = l;
			data.setLength(l);

			stemWrods = new ArrayList<String>(); // ///////12 negahdarie
													// kalaamt////////////

			float[] m = sentenceProcess(sentence);
			float aveTF = m[0]; // ///////3//////////
			data.setScoresI(2, (float) aveTF);

			data.setScoresI(3, 0); // ///////////// melake 4om TF-IDF hast k dar
									// tak sanade mani nadare

			float resTT = m[1]; // ///////5 shbahat b title/////////
			data.setScoresI(4, (float) resTT / titleSize);

			float cent = m[2]; // ///////////6 mizane central budan, tedad
								// kalamate moshtarak ba sayer
								// jomalat////////////////////
			data.setScoresI(5, (float) cent / words.size());

			float em = m[3]; // //////7 tedade emphasize worda ha ////////
			data.setScoresI(6, em);

			// ////////////8 name Entity tedade kalamate name entity taghsim bar
			// kole kalamate jomle///////////////
			float neP = ne.tagSentence(sentence);
			data.setScoresI(7, neP);

			// // /////////////9 numerical data/////////////////
			float nData = m[4];
			data.setScoresI(8, nData);

			// // ///////////10 synonym links ////////////////////
			PartOfSpeech pos = new PartOfSpeech(sentence);
			data.setNounSynset(pos.createSynHash());
			data.setNoun(pos.getNouns());
			data.setSynsets();

			// /////////// 11 co-occurrence link (bigram?!) //////////////

			// ///////////// 12 textRank //////////////////////
			g.addNode(stemWrods);

			datum.add(data);

		}

		// ///////////////12//////////////
		pr = new PageRank(g);
		double[] score12 = pr.getWS();

		// sentences relative length
		for (int k = 0; k < index; k++) {

			Data data = datum.get(k);
			int l = data.getLength();
			float d = (float) l / maxLenght;
			data.setScoresI(1, d);
			HashSet<String> synset1 = data.getSynsets();

			// ///////////////////////10////////////////////
			int numSynset = 0;
			for (Data d2 : datum) {
				HashSet<String> synset2 = d2.getSynsets();
				if (!(d2.equals(data)))
					outerloop: for (String s : synset1) {
						for (String s2 : synset2) {
							if (s.equals(s2)) {
								// System.out.println(s);
								numSynset++;
								break outerloop;
							}
						}
					}
			}
			data.setScoresI(9, (float) numSynset / index);

			// //////////////////////12///////////
			data.setScoresI(11, (float) score12[k]);

		}

	}

	// ///////////////////////1/////////////////////
	private float sentenceLocation(int i) {

		switch (i) {
		case 1:
			return 1;
		case 2:
			return (float) 0.8;
		case 3:
			return (float) 0.6;
		case 4:
			return (float) 0.4;
		case 5:
			return (float) 0.2;
		default:
			return 0;
		}

	}

	// /////////////////////////2///////////////////////
	private int calLength(String str) {
		StringTokenizer st = new StringTokenizer(str, " ");
		return (st.countTokens());

	}

	// /////////////////////////3 AverageTF////////////////////////////////////

	public String cleaning(String builder) {
		String lowerCase = builder.toLowerCase();
		String s = lowerCase.replaceAll("[.,'\")?!]", " ");
		// System.out.println("injaaa  " + s);
		return s;
	}

	private void CreatHash() {

		try {
			BufferedReader stopWords = new BufferedReader(new FileReader("C:/Users/MsB/workSpaces/workspace3/finalProject_v4/stopW1.txt"));
			BufferedReader emphWords = new BufferedReader(new FileReader("C:/Users/MsB/workSpaces/workspace3/finalProject_v4/emphW1.txt"));
			while (stopWords.ready()) {
				stWoHash.add(stopWords.readLine());
			}
			stopWords.close();

			while (emphWords.ready()) {
				emWoHash.add(emphWords.readLine());
			}
			emphWords.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isStopWord(String word) {
		if (stWoHash.contains(word))
			return true;
		return false;
	}

	public String steming(String word) {
		s.add(word.toCharArray(), word.length());
		s.stem();
		return s.toString();
		// return word;
	}

	public void countWords() {

		// Average TF
		CreatHash();
		// String s = text.toLowerCase();
		String s = cleaning(text);
		StringTokenizer st = new StringTokenizer(s, ". \n");

		while (st.hasMoreTokens()) {
			String word = st.nextToken();

			// ////////// fill words
			word = word.toLowerCase();
			if (!isStopWord(word)) {

				int k = 1;
				// String stemWord = steming(word);
				String stemWord = word;

				if (words.containsKey(stemWord)) {
					k = words.get(stemWord);
					k++;
				}
				words.put(stemWord, k);

				if (k > maxTerm)
					maxTerm = k;
			}

		}

	}

	// / averageTF_resToTile_centrality_emphasize
	public float[] sentenceProcess(String sentence) {
		String s = cleaning(sentence);
		// s = sentence.toLowerCase();
		StringTokenizer st = new StringTokenizer(s, " ");
		int sum = 0; // / jam'e kole tekrare kalamehaye in jomle
		int resToTitle = 0;// tedad kalamate moshtarak ba title
		int numOfWordsRepeatedInDoc = 0;
		int numOfEmphas = 0;
		int numWoNS = 0;// tedade kalamate jomle bedune stopwordha
		int numWoS = 0; // tedade kalamate jomle b hamrahe stopwordha
		int numNumD = 0;// tedade kalamate adady

		while (st.hasMoreTokens()) {
			String w = st.nextToken();

			// ///////////////////////9 numerical Data //////////////
			// if (w.matches("(-|\\+)?\\d+(\\.\\d+)?")) {

			// numNumD++;
			// System.out.println("huraaaaaaaaaaa");
			// }

			// w = steming(w);
			if (!isStopWord(w)) {
				// numWoNS++; // tedade kalamate gheire stop word
				// ///////////3 averageTF//////////
				// sum += words.get(w);

				// ////////////5 Resemblance to title///////////
				// if (titleWords.contains(w))
				// resToTitle++;

				// //////////6 centerallity/////////////
				// if (words.get(w) > 1)
				// numOfWordsRepeatedInDoc++;

				// /////////// 12 textrank ///////////
				// String w1 = steming(w);
				// stemWrods.add(w1);

			}
			// /////////////////7 emphasize/////////////////
			if (isEmphWord(w))
				numOfEmphas++;
			numWoS++;

		}
		float s1 = (float) sum / maxTerm;
		s1 = (float) s1 / numWoNS;
		float s2 = (float) numOfEmphas / numWoS;
		float s3 = (float) numNumD / numWoNS;
		// float[] ret = { s1, resToTitle, numOfWordsRepeatedInDoc, s2, s3 };
		float[] ret = { 0, 0, 0, s2, 0 };
		return ret;

	}

	// ////////////////////5////////////////////

	public void clearTitle(String sentence) {

		String s = cleaning(sentence);
		StringTokenizer st = new StringTokenizer(s, " ");
		while (st.hasMoreTokens()) {
			String w = st.nextToken();
			if (!isStopWord(w)) {
				titleWords.add(w);

			}

		}

	}

	public boolean isEmphWord(String word) {
		if (emWoHash.contains(word))
			return true;
		return false;
	}

	public ArrayList<Data> getDatum() {

		return datum;
	}

	// ////////////////12//////////////////////

	/*
	 * public static void main(String[] args) throws Exception { String test =
	 * "hello: hi dear.\n" + "test is a books trial\n" + "I have two book\n" +
	 * "the trial is good\n"; BasicSteps bs = new BasicSteps(test); bs.steps();
	 * 
	 * Data s = new Data(size); int[] mansum = { 1, 2 }; int size = 48;
	 * GeneticAlgorithm ga = new GeneticAlgorithm(size, mansum, datum);
	 * Individual ind = ga.getFinalIndis(); double output[] = { 0.0, 0.0, 0.0,
	 * 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }; for (int j = 0; j < 12;
	 * j++) { for (int i = 0; i < 4; i++) {
	 * 
	 * if (ind.getGene(4*j+i) == 1) output[j] = output[j] + Math.pow(2, i);
	 * 
	 * 
	 * } } // double[] summScore = new double[datum.size()]; double[] summScore
	 * = {0,0,0};
	 * 
	 * for (int i = 0; i < datum.size(); i++) { s = datum.get(i); //
	 * System.out.print(s.getSentence()); for(int
	 * j=0;j<s.getScores().length;j++) // for (double k : s.getScores())
	 * summScore[i] += s.getScores()[j]*output[j]; // System.out.print("  " +
	 * s.getScores()[j]*output[j]); System.out.println(summScore[i]); } }
	 */
}

class Data {

	int size;
	private String sentence;
	private float[] scores;
	private int length;
	private HashMap<String, HashSet<String>> nounsSynsets; // / tamame noun ha
															// ba tamame synset
															// haie an
	private HashSet<String> nouns; // / tamame esmha(nouns)
	private HashSet<String> synsets; // / tamame synset haye tamame esmhaie in
										// jomle

	public HashSet<String> getSynsets() {
		return synsets;
	}

	public void setSynsets() {
		synsets = new HashSet<String>();
		for (String s : nounsSynsets.keySet()) {
			synsets.addAll(nounsSynsets.get(s));
		}
	}

	public HashSet<String> getNoun() {
		return nouns;
	}

	public void setNoun(HashSet<String> nouns) {
		this.nouns = nouns;
	}

	public HashMap<String, HashSet<String>> getNounSynset() {
		return nounsSynsets;
	}

	public void setNounSynset(HashMap<String, HashSet<String>> nouns) {
		this.nounsSynsets = nouns;
	}

	public Data(int size) {
		scores = new float[size];
		this.size = size;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void setScores(float[] scores) {
		this.scores = scores;
	}

	public float[] getScores() {

		return scores;
	}

	public void setScoresI(int i, float d) {

		scores[i] = d;
	}

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

}
