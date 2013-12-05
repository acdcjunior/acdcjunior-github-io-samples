package net.acdcjunior.piloto.test;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.naming.java.javaURLContextFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public abstract class EntityManagerIntegrationTest {
	
	private static EntityManagerFactory entityManagerFactory;
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		realizarBindJndiDoDataSourceDeTestes();
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		desfazerBindJndiDoDataSourceDeTestes();
	}
	
	/**
	 * Inicializa a EntityManagerFactory, caso nao tenha sido inicializada, e
	 * retorna um EntityManager criado a partir dela.
	 */
	protected EntityManager getEntityManagerDeTestes() {
		if (entityManagerFactory == null) {
			String nomePersistenceUnit = TestProperties.getProperty("EntityManagerIntegrationTest.nomePersistenceUnit");
			entityManagerFactory = Persistence.createEntityManagerFactory(nomePersistenceUnit);
		}
		return entityManagerFactory.createEntityManager();
	}

	private static void realizarBindJndiDoDataSourceDeTestes() throws NamingException {
		System.setProperty(Context.INITIAL_CONTEXT_FACTORY, javaURLContextFactory.class.getName());
		System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");
		
		InitialContext ic = new InitialContext();
		ic.createSubcontext("java:");
		ic.createSubcontext("java:/comp");
		ic.createSubcontext("java:/comp/env");
		ic.createSubcontext("java:/comp/env/jdbc");
		
		String nomeDataSource = TestProperties.getProperty("EntityManagerIntegrationTest.nomeDataSource");
		ic.bind("java:/comp/env/jdbc/"+nomeDataSource, new TestDataSource());
	}

	private static void desfazerBindJndiDoDataSourceDeTestes() throws NamingException {
		InitialContext ic = new InitialContext();
		String nomeDataSource = TestProperties.getProperty("EntityManagerIntegrationTest.nomeDataSource");
		ic.unbind("java:/comp/env/jdbc/"+nomeDataSource);
	}
	
}