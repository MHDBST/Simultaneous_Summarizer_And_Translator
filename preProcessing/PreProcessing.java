package preProcessing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class PreProcessing {

	public static void main(String[] args) throws IOException {

		// for (int k = 0; k <= 13; k++)
		{
			// BufferedReader f = new BufferedReader(new FileReader("input" + k
			// + ".in"));
			// PrintWriter out = new PrintWriter((new FileWriter("inputRe" + k +
			// ".in")));
			BufferedReader f = new BufferedReader(
					new FileReader("allEngRe.txt"));
			tokenize(f);
			BufferedReader ft = new BufferedReader(new FileReader(
					"allEngReTok.txt"));
			trim(ft);

		}
	}

	public static void trim(BufferedReader f) throws IOException {
		String line;
		PrintWriter out = new PrintWriter((new FileWriter("allEngReTrim.txt")));
		while ((line = f.readLine()) != null) {
			line = line.trim();
			if (!line.equals("")) // don't write out blank lines
			{
				out.println(line);
			}

		}
		out.close();
	}

	public static void tokenize(BufferedReader f) throws IOException {
		String line;
		PrintWriter out = new PrintWriter((new FileWriter("allEngReTok.txt")));
		while ((line = f.readLine()) != null) {

			StringTokenizer st = new StringTokenizer(line, ".");
			while (st.hasMoreTokens()) {
				String k = st.nextToken();
				String l;
				if (st.hasMoreTokens()) {
					l = st.nextToken();
					if (k.endsWith("[A..Z]") && (l.startsWith("[A..Z]"))) {
						out.print(k + "." + l);
						continue;
					}
					out.println(k);
					out.println(l);
				} else
					out.println(k);

			}

		}
		out.close();
		f.close();

	}
}
