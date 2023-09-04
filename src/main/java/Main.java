import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.config.HibernateConfig;
import model.entities.User_simple;

public class Main {
    public static void main(String[] args)
    {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("hobby");
        try(EntityManager em = emf.createEntityManager())
        {
            User_simple u1 = new User_simple("Kaj", 20, "+4512345678", "1@1.dk", "1234");
            em.getTransaction().begin();
            em.persist(u1);
            em.getTransaction().commit();

        }
        System.out.println("Hello world!");
    }
}