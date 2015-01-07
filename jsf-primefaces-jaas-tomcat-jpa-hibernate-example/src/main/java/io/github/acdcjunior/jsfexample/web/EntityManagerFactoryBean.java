package io.github.acdcjunior.jsfexample.web;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ManagedBean
@ApplicationScoped
public class EntityManagerFactoryBean {

    private EntityManagerFactory entityManagerFactory;

    @PostConstruct
    public void init() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("examplePersistenceUnit");
    }

    /**
     * <p>
     *     Create a new <code>EntityManager</code>. You are responsible for its management, meaning you have to begin
     * and end transactions as well as close it. The <code>isOpen</code> method will return true on the returned instance.
     * </p>
     * <p>This method returns a new code>EntityManager</code> instance each time it is invoked.</p>
     *
     * @return entity manager instance
     */
    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

}