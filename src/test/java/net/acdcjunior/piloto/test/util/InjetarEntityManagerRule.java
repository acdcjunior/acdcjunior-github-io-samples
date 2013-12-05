package net.acdcjunior.piloto.test.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.naming.java.javaURLContextFactory;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class InjetarEntityManagerRule implements TestRule {
	
	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	public static @interface InjetarEntityManager { }
	
	private static final String NOME_CAMPO_ENTITYMANAGER = "em";
	
	private Object instanciaDaClasseDeTestes;
	
	private static EntityManagerFactory entityManagerFactory;
	
	private EntityManager em;
	
	public InjetarEntityManagerRule(Object instanciaDaClasseDeTestes) {
		this.instanciaDaClasseDeTestes = instanciaDaClasseDeTestes;
		inicializarEntityManagerFactory();
	}
	
	public EntityManager getEntityManagerInjetado() {
		return em;
	}
	
	@Override
	public Statement apply(final Statement base, Description description) {
		this.em = entityManagerFactory.createEntityManager();
		InjetorDeEntityManager.injetarCamposAnotados(instanciaDaClasseDeTestes, NOME_CAMPO_ENTITYMANAGER, this.em);
		return base;
	}
	
	private void inicializarEntityManagerFactory() {
		try {
			System.out.println("~~~~"+instanciaDaClasseDeTestes.getClass()+" :: EMF --> "+entityManagerFactory);
			if (entityManagerFactory == null) {
				realizarBindJndiDoDataSourceDeTestes();
				
				String nomePersistenceUnit = TestProperties.getProperty("EntityManagerIntegrationTest.nomePersistenceUnit");
				entityManagerFactory = Persistence.createEntityManagerFactory(nomePersistenceUnit);
				
				desfazerBindJndiDoDataSourceDeTestes();
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
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

class InjetorDeEntityManager {
	
	public static void injetarCamposAnotados(Object instanciaDaClasseDeTestes, String nomeCampo, EntityManager valorCampo) {
		Class<?> clazz = instanciaDaClasseDeTestes.getClass();
		for (Field f : clazz.getDeclaredFields()) {
			if (f.isAnnotationPresent(InjetarEntityManagerRule.InjetarEntityManager.class)) {
				injetar(instanciaDaClasseDeTestes, f, nomeCampo, valorCampo);
			}
		}
	}

	private static void injetar(Object instanciaDaClasseDeTestes, Field f, String nomeCampo, EntityManager valorCampo) {
		Object instanciaAInjetar = getFieldValue(f, instanciaDaClasseDeTestes);
		Field fi = null;
		try {
			fi = instanciaAInjetar.getClass().getDeclaredField(nomeCampo);
			setFieldValue(fi, instanciaAInjetar, valorCampo);
		} catch (NoSuchFieldException e) {
			// nao faz nada, campo nao existe aas vezes, mesmo
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		}
	}

	private static void setFieldValue(Field f, Object instancia, Object valor) {
		try {
			boolean acessibilidadeAnterior = f.isAccessible();
			f.setAccessible(true);
			f.set(instancia, valor);
			f.setAccessible(acessibilidadeAnterior);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	private static Object getFieldValue(Field f, Object instancia) {
		Object fieldValue = null;
		try {
			boolean acessibilidadeAnterior = f.isAccessible();
			f.setAccessible(true);
			fieldValue = f.get(instancia);
			f.setAccessible(acessibilidadeAnterior);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return fieldValue;
	}
	
}