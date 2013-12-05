package net.acdcjunior.piloto.test.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestProperties {
	
	private static final String ARQUIVO_PROPERTIES = "test.properties";
	
	private static Properties p;
	
	public static String getProperty(String key) {
		if (p == null) {
			inicializarProperties();
		}
		return p.getProperty(key);
	}
	
	private static void inicializarProperties() {
		InputStream inputStream = TestProperties.class.getClassLoader().getResourceAsStream(ARQUIVO_PROPERTIES);
		p = new Properties();
		try {
			p.load(inputStream);
		} catch (IOException e) {
			throw new RuntimeException("Erro ao carregar propriedades do TestDataSource!", e);
		}
	}

}