package finalSystem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.aliasi.util.Arrays;

import summarization.BasicSteps;
import summarization.Test;

public class TranslateSummarize {

	final static int size = 12;
	final static double[] weights = { 13, 1, 5, 1, 12, 4, 14, 7, 5, 11, 10, 11 };
	static Test test;
	static double[] res;
	static int[] index;
	static int num;
	static String[] translation;
	// String[] summary;
	static String summary = new String();
	 String trans = new String();

	public TranslateSummarize(String address, double percentage)
			throws Exception {

		// summarize(address, percentage);
		// translate(address);
		byte[] encoded = Files
				.readAllBytes(Paths
						.get("C:/Users/MsB/workSpaces/workspace3/finalProject_v5/newsDB/"
								+ address + ".txt"));
	summary = new String(encoded, StandardCharsets.UTF_8);
	encoded = Files
			.readAllBytes(Paths
					.get("C:/Users/MsB/workSpaces/workspace3/finalProject_v5/newsDB/translation/"
							+ address + "00.txt"));
trans = new String(encoded, StandardCharsets.UTF_8);
		

	}

	public String getSummary() {
		return summary;

	}
	public String getTrans() {
		return trans;

	}

//	 public static void setSummary(String summary){
//	 this.summary = summary;
//
//	 }

	/*public static void summarize(String address, double percentage)
			throws Exception {
		String eol = System.getProperty("line.separator");
		byte[] encoded = Files
				.readAllBytes(Paths
						.get("C:/Users/MsB/workSpaces/workspace3/finalProject_v5/newsDB/"
								+ address + ".txt"));
		String text = new String(encoded, StandardCharsets.UTF_8);
		BasicSteps bs = new BasicSteps(text);
		bs.steps();

		test = new Test(weights, size, bs.getDatum());
		res = test.getSummScore();
		index = new int[res.length];
		for (int i = 0; i < res.length; i++) {
			index[i] = i + 1;
		}
		mySort(0, res.length - 1, res, index);
		num = (int) ((double) percentage * res.length);
		// summary = new String[num + 1];

		int[] sumIn = new int[num];

		System.arraycopy(index, res.length - num, sumIn, 0, num);
		java.util.Arrays.sort(sumIn);
		StringTokenizer st = new StringTokenizer(text, "\n");
		// summary[0] = st.nextToken();

		// summary += (st.nextToken() + eol);
		summary.append(st.nextToken() + "." + eol);
		int i = 0;
		int j = 1;
		while (st.hasMoreTokens()) {
			i++;
			String s = st.nextToken();
			if (java.util.Arrays.binarySearch(sumIn, i) >= 0) {
				// summary[j] = s;
				// summary += (s + eol);
				summary.append(s + "." + eol);
				j++;
			}

		}
		// setSummary(summary);

	}

*/	public static void mySort(int l, int r, double[] A, int[] B) {
		if (l > r)
			return;
		double x = A[r];
		int small = l;
		for (int i = l; i < r; i++) {
			if (x > A[i]) {
				double k = A[small];
				A[small] = A[i];
				A[i] = k;
				int d = B[small];
				B[small] = B[i];
				B[i] = d;
				small++;
			}
		}
		double k = A[small];
		A[small] = x;
		A[r] = k;
		int d = B[small];
		B[small] = B[r];
		B[r] = d;
		mySort(l, small - 1, A, B);
		mySort(small + 1, r, A, B);
	}

	public static void translate(String address) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader(
				"C:/Users/MsB/workSpaces/workspace3/finalProject_v5/newsDB/translation/"
						+ address + ".txt"));
		String l;
		translation = new String[150];
		int i = 0;
		while ((l = f.readLine()) != null) {
			String subL;
			if (l.startsWith("BEST TRANSLATION:")) {
//				System.out.println(l);
				subL = l.substring(17, l.indexOf("[1"));
				translation[i] = subL;
				i++;
			}
		}
		f.close();
		for (String s: translation){
			System.out.println(s);
		}

	}

	public static void main(String[] args) throws Exception {
translate("summ4");
//		summarize("src/src4", 0.5);
//		System.out.println(summary);
	}
}
