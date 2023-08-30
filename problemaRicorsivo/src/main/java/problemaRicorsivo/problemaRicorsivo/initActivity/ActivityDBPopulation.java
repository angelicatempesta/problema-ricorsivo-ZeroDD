package problemaRicorsivo.problemaRicorsivo.initActivity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import problemaRicorsivo.problemaRicorsivo.model.Activity;

public class ActivityDBPopulation {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("activity_persistence_unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        if (entityManager.createQuery("SELECT COUNT(a) FROM Activity a", Long.class).getSingleResult() == 0) {
            entityManager.getTransaction().begin();
            Activity attivita1 = new Activity("attivita1", true, null);
            Activity attivita2 = new Activity("attivita2", true, attivita1);
            Activity attivita3 = new Activity("attivita3", true, attivita2);
            Activity attivita4 = new Activity("attivita4", false, attivita3);
            Activity attivita5 = new Activity("attivita5", false, attivita3);
            Activity attivita6 = new Activity("attivita6", false, attivita4);

            entityManager.persist(attivita1);
            entityManager.persist(attivita2);
            entityManager.persist(attivita3);
            entityManager.persist(attivita4);
            entityManager.persist(attivita5);
            entityManager.persist(attivita6);

            entityManager.getTransaction().commit();
        }
        entityManager.close();
        entityManagerFactory.close();
    }
}
