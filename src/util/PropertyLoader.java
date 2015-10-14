package util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {

	private static final String PATH = "resources/";
	private static final String EXTENSION = ".properties";

	public Properties load(String fileName) throws IOException {
		fileName = PATH + fileName + EXTENSION;
		Properties prop = new Properties();
		InputStream input = getClass().getClassLoader().getResourceAsStream(fileName);
		if (input == null) {
			throw new FileNotFoundException("Cannot find " + fileName);
		}
		prop.load(input);
		input.close();
		return prop;
	}
}
