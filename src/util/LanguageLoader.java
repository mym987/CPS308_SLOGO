package util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class LanguageLoader extends PropertyLoader {

	private static final String[] LANGUAGES = "Chinese English French German Italian Portuguese Russian Spanish"
			.split(" ");

	public Map<String, String> getLocalizedSyntax(String language) throws IOException {
		Properties prop = load("languages/" + language);
		Map<String, String> map = new HashMap<>();
		prop.forEach((func, regex) -> {
			map.put(regex.toString(), func.toString());
		});
		return map;
	}

	public Map<String, String> getAllLocalizedSyntax() throws IOException {
		Map<String, String> map = new HashMap<>();
		for (String language : LANGUAGES) {
			map.putAll(getLocalizedSyntax(language));
		}
		return map;
	}
	
//	public static void main(String[] args) throws IOException{
//		
//		LanguageLoader loader = new LanguageLoader();
//		Map<String,String> map = loader.getLocalizedSyntax("Chinese");
//		map.forEach((k,v)->{
//			System.out.println(k+" : "+v);
//		});
//	}
	
}
