package smallScrapperTest;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

public class Parser {
	
	String out;
	
	public Parser(File in) throws IOException {
		
		out = html2text(in);
		
		
		
	}

	
	public String getOut() {
		return out;
	}


	public static String html2text(File in) throws IOException {
		 Document document = Jsoup.parse(in,"UTF-8");
		 
	        
	        document.select("br").append("\\n");
	        document.select("p").prepend("\\n\\n");
	        String s = document.html().replaceAll("\\\\n", "\n");
	        s = s.replace("&nbsp;", " ");
	        s = s.replace("&quot;", " "); 
	        s = s.replaceAll(" +", " ");
	        
	        	
	        return Jsoup.clean(s, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));
	}

}
