package dao;

import dto.AllInformationUserDTO;
import dto.HobbiesInterestedDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import model.config.HibernateConfig;
import model.entities.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BigDAOTest {
    static BigDAO bigDAO = BigDAO.getInstance();
    static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("hobby");

    @BeforeAll
       static void startUp() {
        User_simple u1 = new User_simple("Kaj", 20, "+4512345678", "1@1.dk", "1234");
        Address a1 = new Address("hejvej", "4554b", "300");
        Hobby hobby1 = new Hobby("fodbold", "man sparker til en bold");
        HobbyClub hc1 = new HobbyClub("Brøndbys unge", "Børn sparker til en bold", 32000, "cph@cph.dk");
        Zipcode z1 = new Zipcode(4200, "Slagelse");
        HobbyUser hu1 = new HobbyUser();
        bigDAO.saveUser(u1);
        bigDAO.saveHobby(hobby1);
        bigDAO.saveAddress(a1);
        bigDAO.saveHobbyClub(hc1);

        try(EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            User_simple userfound = em.find(User_simple.class, u1.getUserId());
            Address addressfound = em.find(Address.class, a1.getAddressId());
            em.persist(hu1);
            em.persist(z1);
            z1.addAddress(addressfound);
            hobby1.addHobbyClub(hc1);
            userfound.addHobbyUser(hu1);
            hobby1.addHobbyUser(hu1);
            addressfound.addUser(userfound);
            z1.addHobbyClub(hc1);
            em.getTransaction().commit();
        }
    }

    @Test
    @Order(1)
    void getAllInfoUser() {
        User_simple userfound = bigDAO.findById(1);
        AllInformationUserDTO result = bigDAO.getAllInfoUser(userfound);
        AllInformationUserDTO expected = new AllInformationUserDTO("Kaj", 20, "+4512345678", "1@1.dk", "hejvej", "4554b", "300", 4200, "Slagelse");
        Hobby hobby = new Hobby("fodbold", "man sparker til en bold");
        Set<Hobby> hobbies = new HashSet<>();
        hobbies.add(hobby);
        expected.setHobbies(hobbies);
        assertEquals(expected.toString(), result.toString());
    }

    @Test
    @Order(2)
    void getUserInfoFromPhonenumber() {
        User_simple userfound = bigDAO.findById(1);
        AllInformationUserDTO result = bigDAO.getUserInfoFromPhonenumber(userfound.getPhonenumber());
        AllInformationUserDTO expected = new AllInformationUserDTO("Kaj", 20, "+4512345678", "1@1.dk", "hejvej", "4554b", "300", 4200, "Slagelse");
        Hobby hobby = new Hobby("fodbold", "man sparker til en bold");
        Set<Hobby> hobbies = new HashSet<>();
        hobbies.add(hobby);
        expected.setHobbies(hobbies);
        assertEquals(expected.toString(), result.toString());
    }

    @Test
    @Order(3)
    void getAllPhonenumbersFromUser() {
        User_simple userfound = bigDAO.findById(1);
        String actual = bigDAO.getAllPhonenumbersFromUser(userfound);
        assertEquals("+4512345678",actual);
    }

    @Test
    @Order(4)
    void getAllPersonsFromHobby() {
        Hobby hobbyfound = bigDAO.findHobbyById(1);
        List actual = bigDAO.getAllPersonsFromHobby(hobbyfound);
        List<String> expected = new ArrayList<>();
        expected.add("Kaj");
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    @Order(5)
    void getNumberOfPeopleWithGivenHobby()
    {
        Hobby found = bigDAO.findHobbyById(1);
        int amount = bigDAO.getNumberOfPeopleWithGivenHobby(found);
        assertEquals(1, amount);
    }

    @Test
    @Order(6)
    void getHobbiesInterestedCount()
    {
        HobbiesInterestedDTO result = bigDAO.getHobbiesInterestedCount();
        Hobby hobby = new Hobby("fodbold", "man sparker til en bold");
        LinkedHashMap<Hobby, Integer> map = new LinkedHashMap<>();
        map.put(hobby, 1);
        HobbiesInterestedDTO expected = new HobbiesInterestedDTO(map);
        assertEquals(expected.toString(), result.toString());
    }

    @Test
    @Order(7)
    void getAllPersonsFromCity() {
        Zipcode zc = new Zipcode(4200, "Slagelse");
        List result = bigDAO.getAllPersonsFromCity(zc);
        List expected = new ArrayList<>();
        expected.add("Kaj");
        assertEquals(expected.toString(), result.toString());
    }

    @Test
    @Order(8)
    void getAllZipcodes() {
        List<Zipcode> list = bigDAO.getAllZipcodes();
        List<Zipcode> expected = new ArrayList<>();
        Zipcode zc = new Zipcode(4200, "Slagelse");
        expected.add(zc);
        assertEquals(expected.toString(), list.toString());
    }

    @Test
    @Order(9)
    void saveUser() {
        User_simple expected = bigDAO.saveUser(new User_simple("Tony", 22, "+4512435678", "2@2.dk", "12354"));
        User_simple actual = bigDAO.findById(2);
        assertEquals(expected.toString(),actual.toString());
    }

    @Test
    @Order(10)
    void findById() {
        User_simple expected = new User_simple("Kaj", 20, "+4512345678", "1@1.dk", "1234");
        expected.setUserId(1);
        User_simple actual = bigDAO.findById(1);
        assertEquals(expected.toString(),actual.toString());
    }

    @Test
    @Order(11)
    void updateUser() {
        bigDAO.saveUser(new User_simple("Kaj", 20, "+4512345678", "1@1.dk", "1234"));
        User_simple actual = bigDAO.updateUser(new User_simple("Kaj", 20, "+4512345678", "1@1.dk", "1234"));
        User_simple expected = bigDAO.findById(3);
        assertEquals(expected.toString(),actual.toString());
    }


    @Test
    @Order(12)
    void saveAddress() {
        Address expected = bigDAO.saveAddress(new Address("TonyStreet", "420", "69"));
        Address actual = bigDAO.findAddressById(2);
        assertEquals(expected.toString(),actual.toString());
    }

    @Test
    @Order(13)
    void updateAddress() {
        bigDAO.saveAddress(new Address("hejvej", "4554b", "300"));
        Address actual = bigDAO.updateAddress(new Address("hejvej", "4554b", "300"));
        Address expected = bigDAO.findAddressById(3);
        assertEquals(expected.toString(),actual.toString());
    }

    @Test
    @Order(14)
    void findAddressById() {
        Address expected = new Address("hejvej", "4554b", "300");
        expected.setAddressId(1);
        Address actual = bigDAO.findAddressById(1);
        assertEquals(expected.toString(),actual.toString());
    }

    @Test
    @Order(15)
    void saveHobby() {
        Hobby expected =  bigDAO.saveHobby(new Hobby("håndbold", "man kaster med en bold"));
        Hobby actual = bigDAO.findHobbyById(2);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    @Order(16)
    void findHobbyById() {
        Hobby expected = new Hobby("fodbold", "man sparker til en bold");
        expected.setHobbyId(1);
        Hobby actual = bigDAO.findHobbyById(1);
        assertEquals(expected.toString(),actual.toString());
    }

    @Test
    @Order(17)
    void updateHobby() {
        bigDAO.saveHobby(new Hobby("fodbold", "man sparker til en bold"));
        Hobby actual = bigDAO.updateHobby(new Hobby("fodbold", "man sparker til en bold"));
        Hobby expected = bigDAO.findHobbyById(1);
        assertEquals(expected.toString(),actual.toString());
    }

    @Test
    @Order(18)
    void updateHobbyClub() {
        bigDAO.saveHobbyClub(new HobbyClub("Brøndbys unge","Børn sparker til en bold",32000,"cph@cph.dk"));
        HobbyClub actual = bigDAO.updateHobbyClub(new HobbyClub("Brøndbys voksne","Voksne sparker til en bold",62000,"cph@cph.dk"));
        HobbyClub expected = bigDAO.findHobbyClubById(3);
        assertEquals(expected.toString(),actual.toString());
    }

    @Test
    @Order(19)
    void saveHobbyClub() {
        HobbyClub expected = bigDAO.saveHobbyClub(new HobbyClub("Brøndbys unge","Børn sparker til en bold",32000,"cph@cph.dk"));
        HobbyClub actual = bigDAO.findHobbyClubById(4);
        assertEquals(expected.toString(),actual.toString());
    }

    @Test
    @Order(20)
    void findHobbyClubById() {
        HobbyClub expected = new HobbyClub("Brøndbys unge","Børn sparker til en bold",32000,"cph@cph.dk");
        expected.setHobbyClubId(1);
        HobbyClub actual = bigDAO.findHobbyClubById(1);
        assertEquals(expected.toString(),actual.toString());
    }
    @Test
    @Order(21)
    void deleteHobbyClub() {
        bigDAO.deleteHobbyClub(bigDAO.findHobbyClubById(1));
        assertNull(bigDAO.findHobbyClubById(1));
    }
    @Test
    @Order(22)
    void deleteHobby() {
        bigDAO.deleteHobby(bigDAO.findHobbyById(1));
        assertNull(bigDAO.findHobbyById(1));
    }
    @Test
    @Order(23)
    void deleteUser() {
        bigDAO.deleteUser(bigDAO.findById(1));
        assertNull(bigDAO.findById(1));
    }
    @Test
    @Order(24)
    void deleteAddress() {
        bigDAO.deleteAddress(bigDAO.findAddressById(1));
        assertNull(bigDAO.findAddressById(1));
    }
}