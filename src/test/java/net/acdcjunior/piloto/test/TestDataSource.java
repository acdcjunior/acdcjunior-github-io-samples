package net.acdcjunior.piloto.test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Reference;
import javax.naming.StringRefAddr;

import org.h2.jdbcx.JdbcDataSource;
import org.h2.jdbcx.JdbcDataSourceFactory;

public class TestDataSource extends org.h2.jdbcx.JdbcDataSource {
	
	private static final long serialVersionUID = 1L;

	public TestDataSource() {
		super();
		super.setURL(gerarUrl());
		super.setUser("sa");
		super.setPassword("");
	}

	private String gerarUrl() {
		String url = "jdbc:h2:mem:bancoDeTestesEmMemoria;INIT=";
		String scripts = TestProperties.getProperty("TestDataSource.scripts");
		if (scripts != null) {
			String[] scriptsDoBancoDeTeste = scripts.split(",");
			for (String nomeScript : scriptsDoBancoDeTeste) {
				url += "RUNSCRIPT FROM '" + deduzirCaminhoAbsolutoDeArquivo(nomeScript.trim()) + "'\\;";
			}
		}
		return url;
	}
	
	private static String deduzirCaminhoAbsolutoDeArquivo(String relativeFilePath) {
		URI uri = null;
		try {
			uri = TestDataSource.class.getClassLoader().getResource(relativeFilePath).toURI();
		} catch (URISyntaxException ex) {
			Logger.getLogger(TestDataSource.class.getName()).log(Level.SEVERE, null, ex);
		}
		if (uri == null) {
			throw new RuntimeException("Impossivel encontrar o arquivo '" + relativeFilePath + "'!");
		}
		return uri.getPath().substring(1);
	}
	
	
	/**
	 * Apenas sobrescrevendo o metodo da JdbcDataSource, trocando a linha:
	 * 
	 * Reference ref = new Reference(getClass()          .getName(), factoryClassName, null);
	 * para:
	 * Reference ref = new Reference(JdbcDataSource.class.getName(), factoryClassName, null);
	 * 
	 * Se este metodo nao eh sobrescrito, o getClass() retorna TestDataSource, o que, nao me
	 * pergunte o porquê, dah pau no JNDI.
	 */
	@Override
	public Reference getReference() {
        String factoryClassName = JdbcDataSourceFactory.class.getName();
        Reference ref = new Reference(JdbcDataSource.class.getName(), factoryClassName, null);
        ref.add(new StringRefAddr("url", super.getURL()));
        ref.add(new StringRefAddr("user", super.getUser()));
        ref.add(new StringRefAddr("password", super.getPassword()));
        ref.add(new StringRefAddr("loginTimeout", String.valueOf(super.getLoginTimeout())));
        ref.add(new StringRefAddr("description", super.getDescription()));
        return ref;
	}
	
}