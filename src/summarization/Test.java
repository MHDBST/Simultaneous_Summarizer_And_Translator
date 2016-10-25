package summarization;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Test {
	
	double[] summScore;

	public Test(double[] output, int size, ArrayList<Data> testdatum/*,
			int[] testmanSum*/) {
//		ArrayList<ArrayList<Data>> docsdatum = new ArrayList<ArrayList<Data>>();
//		docsdatum.add(testdatum);
//		ArrayList<int[]> docsmanSum = new ArrayList<int[]>();
//		docsmanSum.add(testmanSum);
//		GeneticAlgorithm ga = new GeneticAlgorithm(size * 4, docsmanSum,
//				docsdatum);
//		Individual ind = ga.getFinalIndis();
		summScore = new double[testdatum.size()];
		for (int i = 0; i < testdatum.size(); i++) {
			summScore[i] = 0;
		}

		System.out.println("test size is " + testdatum.size());
		//
		for (int i = 0; i < testdatum.size(); i++) {
			Data s = testdatum.get(i);
			for (int j = 0; j < s.getScores().length; j++)
				summScore[i] += 
				s.getScores()[j] 
						* output[j];
			System.out.println(summScore[i] + "  ");
		}
	}

	public double[] getSummScore() {
		return summScore;
	}

	public void setSummScore(double[] summScore) {
		this.summScore = summScore;
	}

	public static void main(String[] args) throws Exception {
		byte[] encoded = Files.readAllBytes(Paths.get("inputRe9.in"));
		String test = new String(encoded, StandardCharsets.UTF_8);
		BasicSteps bs = new BasicSteps(test);
		bs.steps();

		ArrayList<Data> datum = bs.getDatum();

//		int[] mansum = { 1, 7, 11, 15, 25, 28, 33, 38, 41, 43, 48, 54, 59 };

		double[] output = { 13, 7, 4, 3, 6, 15, 4, 0, 11, 2, 12, 12 };
		
		
		new Test(output, 12, datum/*, mansum*/);
	}

}
