package summarization;

import java.io.IOException;
import java.util.StringTokenizer;

import edu.stanford.nlp.classify.Classifier;
import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;

public class NameEntity {

	private String serializedClassifier;
	AbstractSequenceClassifier<CoreLabel> classifier;

	public NameEntity() {

		serializedClassifier = "C:/Users/MsB/Desktop/proje/stanford-ner-2014-08-27/classifiers/english.all.3class.distsim.crf.ser.gz";
		try {
			classifier = CRFClassifier.getClassifier(serializedClassifier);
		} catch (ClassCastException | ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated constructor stub
	}

	public float tagSentence(String s) {

		classifier.classifyWithInlineXML(s);
		StringTokenizer st = new StringTokenizer(s, " ");
		int count = 0; // tedade kalamate name entity
		int size=st.countTokens(); // size jomle
		
		while (st.hasMoreTokens()) {
			String next = st.nextToken();
			if (next.startsWith("<PERSON>"))
				count++;
			else if (next.startsWith("<ORGANIZATION>"))
				count++;
			else if (next.startsWith("<LOCATION>"))
				count++;
		}
		return (float) count/size;


	}

}
