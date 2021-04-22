package gameapp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Management {
    Scanner input = new Scanner(System.in);
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    public void viewAll() {

        viewGames();
        viewDevelopers();

    }

    public void viewGames() {

        EntityManager em = emf.createEntityManager();
        Query what = em.createQuery("SELECT g FROM Game g");
        List<Game> content = what.getResultList();
        System.out.println("<Displaying all games>");
        for (Game g : content) {
            System.out.println(g);
        }
        System.out.println("<End of list>\n");

    }

    public void viewDevelopers() {

        EntityManager em = emf.createEntityManager();
        Query what = em.createQuery("SELECT d FROM Developer d");
        List<Developer> content = what.getResultList();
        System.out.println("<Displaying all developers>");
        for (Developer d : content) {
            System.out.println(d);
        }
        System.out.println("<End of list>\n");


    }

    public void newGame() {

        System.out.println("Title: ");
        String name = input.nextLine();
        System.out.println("Price: ");
        String price = input.nextLine();
        Game game = new Game(name, price);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(game);
        em.getTransaction().commit();
        em.close();

    }

    public void newDeveloper() {

        EntityManager em = emf.createEntityManager();

        double min = Math.ceil(1000);
        double max = Math.floor(9999);

        int developerID = (int) Math.round(Math.floor(Math.random() * (max - min) + min));

        System.out.print("Input Developer Name: ");
        String name = input.nextLine();

        System.out.print("Input Earnings: ");
        String earnings = input.nextLine();

        Developer d1 = new Developer(developerID, name, earnings);

        em.getTransaction().begin();
        em.persist(d1);
        em.getTransaction().commit();
        em.close();

        System.out.println("<Developer successfully added to library>");

    }

    public void editGame() {
        viewGames();
        System.out.println();
        System.out.println("Game ID: ");
        int id = scanInt();
        EntityManager em = emf.createEntityManager();
        Game game = em.find(Game.class, id);
        System.out.println(game);
        System.out.println("\nWhat would you like to edit?");
        System.out.println("1. Name");
        System.out.println("2. Price");
        System.out.println("0. Return to main menu");
        int choice = scanInt();
        if (choice == 1) {

            System.out.println("\nEnter new name: ");
            String name = input.nextLine();
            game.setName(name);
            em.getTransaction().begin();
            em.persist(game);
            em.getTransaction().commit();
            em.close();

        } else if (choice == 2) {

            System.out.println("Enter new price: ");
            String price = input.nextLine();
            game.setPrice(price);
            em.getTransaction().begin();
            em.persist(game);
            em.getTransaction().commit();
            em.close();

        } else {
            return;
        }


    }

    public void editDeveloper() {

        viewDevelopers();
        System.out.println();
        System.out.println("Company Id: ");
        int id = scanInt();
        EntityManager em = emf.createEntityManager();
        Developer dev = em.find(Developer.class, id);
        System.out.println(dev);
        System.out.println("\nWhat would you like to edit?");
        System.out.println("1. Company name");
        System.out.println("2. Earnings");
        System.out.println("0. Return to main menu");
        int choice = scanInt();
        if (choice == 1) {
            System.out.println("New name: ");
            String name = input.nextLine();
            dev.setDeveloperName(name);

        } else if (choice == 2) {
            System.out.println("new earnings: ");
            String earnings = input.nextLine();
            dev.setEarnings(earnings);

        } else {
            return;
        }

        em.getTransaction().begin();
        em.persist(dev);
        em.getTransaction().commit();
        em.close();


    }

    public void connectDevToGame() {

        EntityManager em = emf.createEntityManager();
        viewAll();
        System.out.print("Enter the ID of the Game you'd like to connect: ");
        int gameID = scanInt();

        Game game = em.find(Game.class, gameID);

        System.out.println("Enter the ID of the Developer you'd like to connect: ");
        int devID = scanInt();

        Developer dev = em.find(Developer.class, devID);
        game.setDev(dev);

        em.getTransaction().begin();
        em.persist(game);
        em.getTransaction().commit();
        em.close();

    }

    public void deleteGame(){

        EntityManager em = emf.createEntityManager();
        viewGames();
        System.out.println("\n Enter id of game: ");
        int id = scanInt();
        Game game = em.find(Game.class,id);
        em.getTransaction().begin();
        em.remove(game);
        em.getTransaction().commit();
        em.close();



    }


    private int scanInt() {

        int scanned;

        while (true) {
            try {
                scanned = input.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Please input numerical data");
                input.nextLine();
            }
        }
        input.nextLine();
        return scanned;
    }
}
