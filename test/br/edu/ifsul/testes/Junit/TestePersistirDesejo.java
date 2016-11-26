package br.edu.ifsul.testes.Junit;


import br.edu.ifsul.modelo.Produto;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class TestePersistirDesejo {
    
  EntityManagerFactory emf;
    EntityManager em;
    

    public TestePersistirDesejo() {

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
    public void testePersistirPais() {
        boolean exception = false;
        try {
            Produto p1 = new Produto();
            p1.setNome("Shampoo");
            p1.setDescricao("Anti-Pulga");
                        
            Produto p2 = new Produto();
            p2.setNome("Coleira");
            p2.setDescricao("Cachorro Médio");
          
            em.getTransaction().begin();
            em.persist(p1);
            em.persist(p2);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            exception = true;
            
        }
        // verificando se o resultado é igual ao esperado
        Assert.assertEquals(false, exception);
    }
    
}
