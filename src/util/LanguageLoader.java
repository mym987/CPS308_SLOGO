package util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
/**
 * A util class used to load languages
 * 
 * @author Mike Ma (ym67)
 *
 */
public class LanguageLoader extends PropertyLoader {

	private static final String[] LANGUAGES = "Chinese English French German Italian Portuguese Russian Spanish"
			.split(" ");
	/**
	 * Get a specific set of language syntax
	 * @param language
	 * @return a map from regex to delocalized command
	 * @throws IOException
	 */
	public Map<String, String> getLocalizedSyntax(String language) throws IOException {
		Properties prop = load("languages/" + language);
		Map<String, String> map = new HashMap<>();
		prop.forEach((func, regex) -> {
			map.put(regex.toString(), func.toString());
		});
		return map;
	}
	/**
	 * Get all languages
	 * @return	a map from regex to delocalized command
	 * @throws IOException
	 */
	public Map<String, String> getAllLocalizedSyntax() throws IOException {
		Map<String, String> map = new HashMap<>();
		for (String language : LANGUAGES) {
			map.putAll(getLocalizedSyntax(language));
		}
		return map;
	}
}
