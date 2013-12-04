package net.acdcjunior.piloto.test;

import java.util.Properties;

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
	 * Inicializa a factory de EntityManagers, caso nao tenha sido inicializada, e
	 * retorna um EntityManager criado a partir dela.
	 */
	protected EntityManager getEntityManagerDeTestes() {
		if (entityManagerFactory == null) {
			criarEntityManagerFactoryDeTestes();
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
		
		ic.bind("java:/comp/env/jdbc/pilotoDataSource", new TestDataSource());
	}

	private static void desfazerBindJndiDoDataSourceDeTestes() throws NamingException {
		InitialContext ic = new InitialContext();
		ic.unbind("java:/comp/env/jdbc/pilotoDataSource");
	}
	
	private void criarEntityManagerFactoryDeTestes() {
		// sobrescreve propriedades do persistence-unit para os testes
		Properties p = new Properties();
        p.put("javax.persistence.transactionType", "RESOURCE_LOCAL");
		p.put("hibernate.show_sql", "true");
		p.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		entityManagerFactory = Persistence.createEntityManagerFactory("pilotoPersistenceUnit", p);
	}

}