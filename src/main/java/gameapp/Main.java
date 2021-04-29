package gameapp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        Query what = em.createQuery("SELECT d.companyID FROM Developer d");
        Query who = em.createQuery("SELECT g.Id FROM Game g");
        List<Integer> alsoIds = who.getResultList();
        List<Integer> ids = what.getResultList();
        em.close();
        Developer.idBank.addAll(ids);
        Game.idBank.addAll(alsoIds);
        Menu mainMenu = new Menu();

        while (true) {

            mainMenu.MainMenu();

        }


    }

}
