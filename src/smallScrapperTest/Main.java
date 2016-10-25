package smallScrapperTest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import org.xml.sax.SAXException;

import de.l3s.boilerpipe.BoilerpipeProcessingException;

public class Main {

	static Parser p;

	// url : adrese siti k scrapp mishavad. bad az scrapp kardan b joz matne
	// asli tamame hashie ha az bein miravad ama hamchenan tag haie html baghi
	// mimanad
	// fOs : scrapp b do shive zakhire bar ruye file va zakhire bar ruye string
	// mitavanad b parser dade shavad
	// parser file matni html ra b hamrahe tamame tag ha b file khales matn
	// tabdil mikonad. hata \n ha ham hazf mishavad. in kheili bade :(
	public Main(URL url, int fOs) throws IOException,
			BoilerpipeProcessingException, SAXException {

		new Scrapper(url, fOs);
		File in = new File("testMan.xml");
		p = new Parser(in);
		PrintWriter pw = new PrintWriter(new FileWriter("testMan.txt"));
		pw.println(p.getOut());
		pw.close();
		

	}



}
