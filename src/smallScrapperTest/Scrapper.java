package smallScrapperTest;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import org.xml.sax.SAXException;

import de.l3s.boilerpipe.BoilerpipeExtractor;
import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.extractors.CommonExtractors;
import de.l3s.boilerpipe.sax.HTMLHighlighter;

public class Scrapper {

	/* 1 */final BoilerpipeExtractor extractor = CommonExtractors.ARTICLE_EXTRACTOR;

	/* b */final HTMLHighlighter hh = HTMLHighlighter.newExtractingInstance();

	public Scrapper(URL url, int fOs) throws IOException,
			BoilerpipeProcessingException, SAXException {

		if (fOs == 0) {
			fileWrite(url);
		} else

			stringWrite(url);

	}

	public void fileWrite(URL url) throws IOException,
			BoilerpipeProcessingException, SAXException {
		/* A */PrintWriter out = new PrintWriter("testMan.xml", "UTF-8");
		out.println("<?xml version=\'1.0\' encoding=\'UTF-8\' ?>");
		out.println("<?xml-stylesheet type=\'text/xsl\' href=\'" + url
				+ "\' ?>");

		out.println(hh.process(url, extractor));
		out.close();

	}

	public StringBuilder stringWrite(URL url) throws IOException,
			BoilerpipeProcessingException, SAXException {

		StringBuilder screpped = new StringBuilder("");
		screpped.append("<?xml version=\'1.0\' encoding=\'UTF-8\' ?>");
		screpped.append("<?xml-stylesheet type=\'text/xsl\' href=\'" + url
				+ "\' ?>");
		System.out.println(url);
		screpped.append(hh.process(url, extractor));
		return screpped;

	}

}
