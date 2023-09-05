import dao.BigDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.config.HibernateConfig;
import model.entities.*;

public class Main {
    public static void main(String[] args)
    {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("hobby");
        BigDAO bigDAO = new BigDAO();
        User_simple u1 = new User_simple("Kaj", 20, "+4512345678", "1@1.dk", "1234");
        Address a1 = new Address("hejvej", "4554b", "300");
        Hobby hobby1 = new Hobby("fodbold", "man sparker til en bold");
        HobbyClub hc1 = new HobbyClub("Brøndbys unge", "Børn sparker til en bold", 32000, "TobiasErPedofil@cph.dk");
        Zipcode z1 = new Zipcode(4200, "Slagelse");
        HobbyUser hu1 = new HobbyUser();
        try(EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            em.persist(hu1);
            em.persist(u1);
            em.persist(a1);
            em.persist(hobby1);
            em.persist(hc1);
            em.persist(z1);
            z1.addAddress(a1);
            hobby1.addHobbyClub(hc1);
            u1.addHobbyUser(hu1);
            hobby1.addHobbyUser(hu1);
            a1.addUser(u1);
            z1.addHobbyClub(hc1);
            em.getTransaction().commit();

        }
        System.out.println(bigDAO.getAllInfoUser(u1));
        System.out.println("Hello world!");
    }
}