package summarization;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Training {
	final int size1 = 14;

	public Training(int size) throws Exception {

		ArrayList<ArrayList<Data>> docsdatum = new ArrayList<ArrayList<Data>>();
		ArrayList<int[]> docsmanSum = new ArrayList<int[]>();
		ArrayList<Data> testdatum = new ArrayList<Data>();
		int[] testmanSum = null;

		// String test = "hello: hi dear.\n" + "test is a books trial\n"
		// + "I have two book\n" + "the trial is good\n";
		
		for (int t = 0; t <= size1; t++) {
			BufferedReader f = new BufferedReader(new FileReader("indexes.in"));
			System.out.println("testIndex  " + t);
			for (int k = 0; k <= size1; k++) {
				String l = f.readLine();
				StringTokenizer st = new StringTokenizer(l, ", ");
				int[] mansum = new int[st.countTokens() - 1];
				for (int d = 0; d < st.countTokens() - 1; d++) {
					mansum[d] = Integer.parseInt(st.nextToken());

				}
				byte[] encoded = Files.readAllBytes(Paths.get("inputRe" + k
						+ ".in"));
				String test = new String(encoded, StandardCharsets.UTF_8);
				BasicSteps bs = new BasicSteps(test);
				bs.steps();

				// Data s = new Data(size);
				ArrayList<Data> datum = bs.getDatum();
				if (k != t) {
					docsdatum.add(datum);
					docsmanSum.add(mansum);
				} else {
					testdatum = datum;
					testmanSum = mansum;
				}
			}

			{

				// GeneticAlgorithm ga = new GeneticAlgorithm(size * 4, mansum,
				// datum);
				GeneticAlgorithm ga = new GeneticAlgorithm(size * 4,
						docsmanSum, docsdatum);
				Individual ind = ga.getFinalIndis();
				double output[] = { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
						0.0, 0.0, 0.0, 0.0 };
				for (int j = 0; j < 12; j++) {
					for (int i = 0; i < 4; i++) {

						if (ind.getGene(4 * j + i) == 1)
							output[j] = output[j] + Math.pow(2, i);

					}
					System.out.print(output[j] + " ");
				}
				System.out.println();

				// ////////////////////////hesab kardane emtiaze jomalat
				// ////////////////////
				for (int k = 0; k < size1; k++) 
				{
					ArrayList<Data> datum = docsdatum.get(k);
					double[] summScore = new double[datum.size()];
					for (int i = 0; i < datum.size(); i++) {
						summScore[i] = 0;
					}

					System.out.println("size is " + datum.size());
					//
					for (int i = 0; i < datum.size(); i++) {
						Data s = datum.get(i);
						for (int j = 0; j < s.getScores().length; j++)
							summScore[i] += s.getScores()[j] * output[j];
//						System.out.println(summScore[i] + "  ");
					}
				}
				new Test(output,size, testdatum/*, testmanSum*/);

			}
			f.close();
		}

	}

	public static void main(String[] args) throws Exception {

		new Training(12);

	}

}
