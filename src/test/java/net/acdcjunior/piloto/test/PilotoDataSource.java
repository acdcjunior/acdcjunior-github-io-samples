package net.acdcjunior.piloto.test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.h2.jdbcx.JdbcDataSource;

public class PilotoDataSource extends JdbcDataSource {
	
	private static final String[] scriptsDoBancoDeTeste = new String[]{"esquema.sql", "dadosDeTeste.sql"};

	private static final long serialVersionUID = 1L;

	public PilotoDataSource() {
		super();
		String url = "jdbc:h2:mem:bancoDeTestesEmMemoria;INIT=";
		 for (String nomeScript : scriptsDoBancoDeTeste) {
		 url += "RUNSCRIPT FROM '"+deduzirCaminhoAbsolutoDeArquivo(nomeScript)+"'\\;";
		 }
		setURL(url);
		setUser("sa");
		setPassword("");
	}
	
	private static String deduzirCaminhoAbsolutoDeArquivo(String relativeFilePath) {
		try {
			URI uri = EntityManagerIntegrationTest.class.getClassLoader().getResource(relativeFilePath).toURI();
			return uri.getPath().substring(1);
		} catch (URISyntaxException ex) {
			Logger.getLogger(EntityManagerIntegrationTest.class.getName()).log(Level.SEVERE, null, ex);
			throw new RuntimeException("Impossivel encontrar o arquivo '" + relativeFilePath + "'!", ex);
		}
	}
	
}