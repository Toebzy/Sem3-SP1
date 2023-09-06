package dao;

import dto.AllInformationUserDTO;
import dto.HobbiesInterestedDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import model.config.HibernateConfig;
import model.entities.*;

import java.util.*;

public class BigDAO
{
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("hobby");
    private static BigDAO bigDAO;
    public static BigDAO getInstance()
    {
        if(bigDAO == null)
        {
            bigDAO = new BigDAO();
        }
        return bigDAO;
    }

    public AllInformationUserDTO getAllInfoUser(User_simple user_simple)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            TypedQuery<AllInformationUserDTO> q1 = em.createQuery("SELECT NEW dto.AllInformationUserDTO(us.name, us.age, us.phonenumber, us.email, a.street, a.number, a.floor, z.zipcode, z.cityName) FROM User_simple us JOIN us.address a JOIN a.zipcode z JOIN us.hobbies h WHERE us.id = :id", AllInformationUserDTO.class);
            q1.setParameter("id", user_simple.getUserId());

            AllInformationUserDTO userDTO  = q1.getSingleResult();
            TypedQuery<Hobby> q2 = em.createQuery("SELECT h FROM HobbyUser hu JOIN hu.hobby h WHERE hu.userSimple = :user", Hobby.class);
            q2.setParameter("user", user_simple);
            Set<Hobby> hobbies = new HashSet<>(q2.getResultList());

            userDTO.setHobbies(hobbies);

            return userDTO;
        }
    }


    public AllInformationUserDTO getUserInfoFromPhonenumber(String phonenumber){

        try(EntityManager em = emf.createEntityManager())
        {
            TypedQuery<AllInformationUserDTO> q1 = em.createQuery("SELECT NEW dto.AllInformationUserDTO(us.name, us.age, us.phonenumber, us.email, a.street, a.number, a.floor, z.zipcode, z.cityName) FROM User_simple us JOIN us.address a JOIN a.zipcode z JOIN us.hobbies h WHERE us.phonenumber = :id", AllInformationUserDTO.class);
            q1.setParameter("id", phonenumber);
            AllInformationUserDTO userDTO  = q1.getSingleResult();
            TypedQuery<Hobby> q2 = em.createQuery("SELECT h FROM HobbyUser hu JOIN hu.hobby h WHERE hu.userSimple.phonenumber = :phonenumber", Hobby.class);
            q2.setParameter("phonenumber", phonenumber);
            Set<Hobby> hobbies = new HashSet<>(q2.getResultList());

            userDTO.setHobbies(hobbies);
            return userDTO;
        }
    }

    public String getAllPhonenumbersFromUser(User_simple user_simple)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            TypedQuery<String> q1 = em.createQuery("SELECT us.phonenumber FROM User_simple us WHERE us.id = :id", String.class);
            q1.setParameter("id", user_simple.getUserId());
            return q1.getSingleResult();
        }
    }
    public List getAllPersonsFromHobby(Hobby hobby)
    {
        try (EntityManager em = emf.createEntityManager())
        {
            TypedQuery<List> q1 = em.createQuery("SELECT us FROM HobbyUser hu JOIN hu.hobby h JOIN hu.userSimple us WHERE h.id = :id", List.class);
            q1.setParameter("id", hobby.getHobbyId());
            return q1.getResultList();
        }
    }
    public int getNumberOfPeopleWithGivenHobby(Hobby hobby)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            TypedQuery<Integer> q1 = em.createQuery("SELECT SIZE(h.users) FROM Hobby h WHERE h.id = :id", Integer.class);
            q1.setParameter("id", hobby.getHobbyId());
            return q1.getSingleResult();
        }
    }
    public HobbiesInterestedDTO getHobbiesInterestedCount() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Object[]> query = em.createQuery("SELECT h, COUNT(hu) FROM Hobby h LEFT JOIN h.users hu GROUP BY h", Object[].class);

            // Execute the query and get the results as a list of arrays
            List<Object[]> resultList = query.getResultList();

            // Create a map to store the results
            LinkedHashMap<Hobby, Integer> hobbyAssignmentCounts = new LinkedHashMap<>();

            // Iterate through the results and populate the map
            for (Object[] result : resultList) {
                Hobby hobby = (Hobby) result[0];
                Long count = (Long) result[1];
                hobbyAssignmentCounts.put(hobby, count.intValue());
            }
            return new HobbiesInterestedDTO(hobbyAssignmentCounts);
        }
    }
    public List getAllPersonsFromCity(Zipcode zipcode) {
        try(EntityManager em = emf.createEntityManager()) {
            TypedQuery<List> q1 = em.createQuery("SELECT us FROM Zipcode zc JOIN zc.addresses a JOIN a.userSimples us WHERE zc.zipcode = :zipcode", List.class);
            q1.setParameter("zipcode", zipcode.getZipcode());
            return q1.getResultList();
        }
    }
    public List<Zipcode> getAllZipcodes()
    {
        try(EntityManager em = emf.createEntityManager())
        {
            TypedQuery<Zipcode> q1 = em.createQuery("SELECT zc FROM Zipcode zc", Zipcode.class);
            return q1.getResultList();
        }
    }

    public User_simple saveUser(User_simple user_simple)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            em.persist(user_simple);
            em.getTransaction().commit();
            return user_simple;
        }
    }
    public User_simple findById(int id)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            User_simple foundUser = em.find(User_simple.class, id);
            return foundUser;
        }
    }
    public User_simple updateUser(User_simple user_simple)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            User_simple updatedUser = em.merge(user_simple);
            em.getTransaction().commit();
            return updatedUser;
        }
    }
    public void deleteUser(User_simple user_simple)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            em.remove(user_simple);
            em.getTransaction().commit();
        }
    }

    public Address saveAddress(Address address)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            em.persist(address);
            em.getTransaction().commit();
            return address;
        }
    }
    public Address updateAddress(Address address)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            Address updatedAddress = em.merge(address);
            em.getTransaction().commit();
            return updatedAddress;
        }
    }
    public void deleteAddress(Address address)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            em.remove(address);
            em.getTransaction().commit();
        }
    }
    public Address findAddressById(int id)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            Address foundAddress = em.find(Address.class, id);
            return foundAddress;
        }
    }
    public Hobby saveHobby(Hobby hobby)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            em.persist(hobby);
            em.getTransaction().commit();
            return hobby;
        }
    }
    public Hobby findHobbyById(int id)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            Hobby foundHobby = em.find(Hobby.class, id);
            return foundHobby;
        }
    }
    public Hobby updateHobby(Hobby hobby)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            Hobby updatedHobby = em.merge(hobby);
            em.getTransaction().commit();
            return updatedHobby;
        }
    }
    public void deleteHobby(Hobby hobby)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM HobbyUser hu WHERE hu.hobby = :hobby")
                    .setParameter("hobby", hobby)
                    .executeUpdate();
            em.remove(hobby);
            em.getTransaction().commit();
        }
    }

    public void deleteHobbyClub(HobbyClub hobbyClub)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            em.remove(hobbyClub);
            em.getTransaction().commit();
        }
    }
    public HobbyClub updateHobbyClub(HobbyClub hobbyClub)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            HobbyClub updatedHobbyClub = em.merge(hobbyClub);
            em.getTransaction().commit();
            return updatedHobbyClub;
        }
    }
    public HobbyClub saveHobbyClub(HobbyClub hobbyClub)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            em.persist(hobbyClub);
            em.getTransaction().commit();
            return hobbyClub;
        }
    }
    public HobbyClub findHobbyClubById(int id)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            HobbyClub foundHobbyClubUser = em.find(HobbyClub.class, id);
            return foundHobbyClubUser;
        }
    }
}