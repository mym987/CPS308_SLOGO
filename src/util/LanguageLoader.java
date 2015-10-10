package util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class LanguageLoader extends PropertyLoader{
	
	private static final String[] LANGUAGES = "Chinese English French German Italian Portuguese Russian Spanish".split(" ");
	
	@Override
	public Properties load(String language) throws IOException{
		return super.load("languages/"+language);
	}
	
	public Map<String,String> getLocalizedSyntax(String language)throws IOException{
		Properties prop = load(language);
		Map<String,String> map = new HashMap<>();
		prop.forEach((key,val)->{
			String[] localized = ((String)val).split("\\|");
			for(String regex:localized){
				map.put(regex, (String)key);		
			}
		});
		return map;
	}
	
	public Map<String,String> getAllLocalizedSyntax()throws IOException{
		Map<String,String> map = new HashMap<>();
		for(String language:LANGUAGES){
			map.putAll(getLocalizedSyntax(language));
		}
		return map;
	}
	
//	public static void main(String[] args) throws IOException{
//		
//		LanguageLoader loader = new LanguageLoader();
//		Map<String,String> map = loader.getLocalizedSyntax("Chinese");
//		map.forEach((k,v)->{
//			System.out.println(k+":"+v);
//		});
//	}
	
}
