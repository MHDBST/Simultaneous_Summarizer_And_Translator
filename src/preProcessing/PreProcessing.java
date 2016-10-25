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

	public PreProcessing(String input, String output) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader(input + ".txt"));
		tokenize(f);
		BufferedReader ft = new BufferedReader(new FileReader("temp.txt"));
		trim(ft, output);

	}

	public static void main(String[] args) throws IOException {

		// new PreProcessing(input, output);
	}

	public static void trim(BufferedReader f, String output) throws IOException {
		String line;
		PrintWriter out = new PrintWriter((new FileWriter(output + ".txt")));
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
		PrintWriter out = new PrintWriter((new FileWriter("temp.txt")));
		while ((line = f.readLine()) != null) {

			StringTokenizer st = new StringTokenizer(line, ".");
			while (st.hasMoreTokens()) {
				String k = st.nextToken();
				if (!k.startsWith(" "))
					out.print(k + ".");
				else {
					out.println();
					out.print(k);
				}

			}

		}
		out.close();
		f.close();

	}
}
