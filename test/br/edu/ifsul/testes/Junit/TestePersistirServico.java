package br.edu.ifsul.testes.Junit;


import br.edu.ifsul.modelo.Funcionario;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.modelo.Servico;
import java.util.Calendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestePersistirServico {

    EntityManagerFactory emf;
    EntityManager em;

    public TestePersistirServico() {

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
            Servico obj = new Servico();
            obj.setData(Calendar.getInstance());
            obj.setNome("Servico de Cursos sobre Java2");
            obj.setDescricao("Servico de cursos sobre desenvolvimento em Java2");
            obj.setPreco(1000.00);
            obj.setPessoa(em.find(Pessoa.class, 4));
            
           Funcionario f = new Funcionario();
            f.setPessoa(em.find(Pessoa.class, 4));
            f.setMatricula(1);
            f.setFuncao("Esteticista");
            obj.adicionarFuncionario(f);           
      
            
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            exception = true;

        }
        // verificando se o resultado é igual ao esperado
        Assert.assertEquals(false, exception);
    }

}
