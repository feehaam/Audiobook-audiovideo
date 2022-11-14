package audio;

import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.darkprograms.speech.synthesiser.BaseSynthsiser;
import com.darkprograms.speech.translator.GoogleTranslate;

public class SynthesiserV2 extends BaseSynthsiser {

	private static final String GOOGLE_SYNTHESISER_URL = "https://www.google.com/speech-api/v2/synthesize?enc=mpeg&client=chromium";
	
	private final String API_KEY = "AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw";
	private String languageCode;
	private double pitch = .8;
	private double speed = .8;
	
	@Override
	public InputStream getMP3Data(String synthText) throws IOException{
		String languageCode = this.languageCode;
		if(languageCode == null || languageCode.equals("") || languageCode.equalsIgnoreCase("auto")){
			try{
				languageCode = detectLanguage(synthText);
				if(languageCode == null){
					languageCode = "en-us";
				}
			}
			catch(Exception ex){
				ex.printStackTrace();
				languageCode = "en-us";
			}
		}
		String encoded = URLEncoder.encode(synthText, "UTF-8"); 
		StringBuilder sb = new StringBuilder(GOOGLE_SYNTHESISER_URL);
		sb.append("&key=" + API_KEY);
		sb.append("&text=" + encoded);
		sb.append("&lang=" + languageCode);

		if(speed>=0 && speed<=2.0){
			sb.append("&speed=" + speed/2.0);
		}
		
		if(pitch>=0 && pitch<=2.0){
			sb.append("&pitch=" + pitch/2.0);
		}
		
		URL url = new URL(sb.toString()); 

		// Open New URL connection channel.
		URLConnection urlConn = url.openConnection(); 

		urlConn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:2.0) Gecko/20100101 Firefox/4.0"); //Adding header for user agent is required
		
		return urlConn.getInputStream();
	}
}