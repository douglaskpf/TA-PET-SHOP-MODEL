package br.edu.ifsul.testes.Junit;

import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.modelo.Telefone;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TestePersistirTelefone {
    
    EntityManagerFactory emf;
    EntityManager em;
    
    public TestePersistirTelefone() {
    }
    
    @Before
     public void setUp() {
        emf = Persistence.createEntityManagerFactory("TA-PET-SHOP-WebPULocal");
        em = emf.createEntityManager();
    }
     
    @After
    public void tearDown() {
        em.close();
        emf.close();
    }
    
    @Test
    public void teste(){
        Boolean exception = false;
        try {
            Pessoa p = em.find(Pessoa.class, 4);
            Telefone t = new Telefone();
            t.setNumero("(54)9987-4564");
            t.setDescricao("Celular");
            p.adicionarTelefone(t);
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        } catch(Exception e){
            exception = true;
            e.printStackTrace();
        }
        Assert.assertEquals(false, exception);
    }
    
}
