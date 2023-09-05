package dao;

import jakarta.persistence.EntityManagerFactory;
import model.config.HibernateConfig;

public class BigDAO
{
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("hobby");
}
