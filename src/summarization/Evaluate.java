package summarization;

import java.util.ArrayList;
import java.util.Arrays;

public class Evaluate {

	ArrayList<Data> datum; // araye'E az documentha k har doc araye'E
										// az jomalat ast
	int[] weightD;
	double percentage;
	float totalScores[];
	int[] B;// serfan jahate index :D
	int[] manSumm; // negahdarie indexe mojud dar manually
									// summarized farz konim
	// index jomalat ro bar asase ahamiat negah dashte
	// private Date datum;

	public Evaluate(int[] weightB, double percentage,
			int[] manSumm, ArrayList<Data> datum) {

		weightD = changeBToD(weightB);
		// datum = BasicSteps.datum;
		this.percentage = percentage;
		this.manSumm = manSumm;
		this.datum = datum;
		// System.out.println("size is "+datum.size());
	}

	private int[] changeBToD(int[] weightB) {
		int[] w = new int[weightB.length / 4];
		for (int i = 0; i < weightB.length - 3; i++) {
			int[] seq = { weightB[i], weightB[i + 1], weightB[i + 2],
					weightB[i + 3] };
			w[i / 4] = 0;
			if (Arrays.equals(new int[] { 1, 1, 1, 1 }, seq))
				w[i / 4] = 15;

			else if (Arrays.equals(new int[] { 1, 1, 1, 0 }, seq))
				w[i / 4] = 14;

			else if (Arrays.equals(new int[] { 1, 1, 0, 1 }, seq))
				w[i / 4] = 13;

			else if (Arrays.equals(new int[] { 1, 1, 0, 0 }, seq))
				w[i / 4] = 12;

			else if (Arrays.equals(new int[] { 1, 0, 1, 1 }, seq))
				w[i / 4] = 11;

			else if (Arrays.equals(new int[] { 1, 0, 1, 0 }, seq))
				w[i / 4] = 10;

			else if (Arrays.equals(new int[] { 1, 0, 0, 1 }, seq))
				w[i / 4] = 9;

			else if (Arrays.equals(new int[] { 1, 0, 0, 0 }, seq))
				w[i / 4] = 8;

			else if (Arrays.equals(new int[] { 0, 1, 1, 1 }, seq))
				w[i / 4] = 7;

			else if (Arrays.equals(new int[] { 0, 1, 1, 0 }, seq))
				w[i / 4] = 6;

			else if (Arrays.equals(new int[] { 0, 1, 0, 1 }, seq))
				w[i / 4] = 5;

			else if (Arrays.equals(new int[] { 0, 1, 0, 0 }, seq))
				w[i / 4] = 4;

			else if (Arrays.equals(new int[] { 0, 0, 1, 1 }, seq))
				w[i / 4] = 3;

			else if (Arrays.equals(new int[] { 0, 0, 1, 0 }, seq))
				w[i / 4] = 2;

			else if (Arrays.equals(new int[] { 0, 0, 0, 1 }, seq))
				w[i / 4] = 1;

		}
		return w;
	}

	public int[] getWeightD() {
		return weightD;
	}

	public float calculate() {
		// for har maghale
//		int countDocs = 10; // tedade maghalat
		float sumP = 0; // jame precision ha baraye kole document ha
//		for (int d = 0; d < countDocs; d++)
		{
//			ArrayList<Data> datum = datum.get(d);
//			int[] manSumm = manSumm.get(d);

			totalScores = new float[datum.size()]; // / jame emtiaz har sentence
													// baraye in doc
			B = new int[datum.size()]; // just to keep totalscores index:D
			for (int k = 0; k < datum.size(); k++) { // == (Data d : datum) // baraye har jomle
				B[k] = k;

				Data data = datum.get(k);
				float[] scores = data.getScores();

				// System.out.println();

				for (int i = 0; i < scores.length; i++) {
					// scores[i] = weightD[i] * scores[i];
					// totalScores[k] += scores[i];
					totalScores[k] += weightD[i] * scores[i];
				}

			}

			// int count = (int) (percentage * datum.size());
			int count = manSumm.length;
			// moratab sazi az kuchik b bozrog, b khatere hamin ma count taie
			// akharesho mikhaim
			mySort(0, datum.size() - 1, totalScores, B);
			int t = 0; // /tedad jomalate moshtarake manually va data set
			for (int i = 0; i < count; i++) {

				for (int j = 0; j < count; j++) {

					// chek kone bebine count ta jomle avale totalscores chand
					// tash ba manually summarized yekie
					if (manSumm[j] == B[datum.size() - i - 1])
						t++;

				}

			}
			sumP += (float) t / count; // precisione in maghale
		}

//		return (float) sumP / countDocs;
		return sumP;

	}

	// / sort A with respect to B
	public static void mySort(int l, int r, float[] A, int[] B) {
		if (l > r)
			return;
		float x = A[r];
		int small = l;
		for (int i = l; i < r; i++) {
			if (x > A[i]) {
				float k = A[small];
				A[small] = A[i];
				A[i] = k;
				int d = B[small];
				B[small] = B[i];
				B[i] = d;
				small++;
			}
		}
		float k = A[small];
		A[small] = x;
		A[r] = k;
		int d = B[small];
		B[small] = B[r];
		B[r] = d;
		mySort(l, small - 1, A, B);
		mySort(small + 1, r, A, B);
	}

	public static void main(String[] args) {
		// Evaluate e = new Evaluate(new int[] { 1, 1, 0, 1, 0, 0, 1, 0 }, 0.7);
		// for (int t : e.getWeightD())
		// System.out.println(t);
		// float[] t = { (float) 2.5, (float) 1.4, (float) 3.7, (float) 2.1,
		// (float) 3.3 };
		// int[] r = { 1, 2, 3, 4, 5 };
		// mySort(0, 4, t, r);
		// for (int f : r)
		// System.out.println(f);
	}

}
