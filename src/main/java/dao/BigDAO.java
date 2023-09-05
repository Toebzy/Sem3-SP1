package dao;

import dto.AllInformationUserDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import model.config.HibernateConfig;
import model.entities.Hobby;
import model.entities.User_simple;

import java.util.HashSet;
import java.util.Set;

public class BigDAO
{
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("hobby");

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
}
