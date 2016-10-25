/////////hamun Main dar Small Scrapper/////////

/*package news;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
//http://news.yahoo.com/rss/entertainment
//http://www.cbc.ca/cmlink/rss-sports
//http://feeds.feedburner.com/cbc/yrdo filter
//http://feeds.theguardian.com/theguardian/uk/sport/rss
//http://rss.nytimes.com/services/xml/rss/nyt/Sports.xml

public class URLConnection {
	public URLConnection() {
		// TODO Auto-generated constructor stub
		byte buf[] = new byte[4096];
		URL url;
		try {

			url = new URL(
					"http://feeds.theguardian.com/theguardian/uk/sport/rss");
			BufferedInputStream bis = new BufferedInputStream(url.openStream());
			File file = new File("input.xml");
			OutputStream fos = new FileOutputStream(file);

			int bytesRead = 0;

			while ((bytesRead = bis.read(buf)) != -1) {
				fos.write(buf, 0, bytesRead);
			}

			fos.flush();
			fos.close();
			bis.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}*/