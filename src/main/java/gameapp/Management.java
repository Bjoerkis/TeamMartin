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
        addIDtoBank(game);

    }

    private void addIDtoBank(Game game) {
        Game.idBank.add(game.getId());
    }

    public void newDeveloper() {

        EntityManager em = emf.createEntityManager();

        System.out.print("Input Developer Name: ");
        String name = input.nextLine();

        System.out.print("Input Earnings: ");
        String earnings = input.nextLine();
        int developerID = generateId();
        while (true) {
            if (Developer.idBank.contains(developerID)) {
                developerID = generateId();
            } else {
                break;
            }

        }
        Developer.idBank.add(developerID);
        Developer d1 = new Developer(developerID, name, earnings);

        em.getTransaction().begin();
        em.persist(d1);
        em.getTransaction().commit();
        em.close();

        System.out.println("<Developer successfully added to library>");

    }

    private int generateId() {

        double min = Math.ceil(1000);
        double max = Math.floor(9999);

        int developerID = (int) Math.round(Math.floor(Math.random() * (max - min) + min));

        return developerID;

    }

    public void editGame() {
        int id = inputGameId();

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

        } else if (choice == 2) {

            System.out.println("Enter new price: ");
            String price = input.nextLine();
            game.setPrice(price);


        } else {
            return;
        }

        em.getTransaction().begin();
        em.persist(game);
        em.getTransaction().commit();
        em.close();


    }

    public void editDeveloper() {
        int id = inputDevId();
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

    private int inputDevId() {

        if (Developer.idBank.size() == 0) {
            System.out.println("No developers available");
            return 0;
        }

        int id;
        viewDevelopers();
        while (true) {
            System.out.print("\nCompanyID of developer: ");
            id = scanInt();
            if (Developer.idBank.contains(id)) {
                break;
            } else {
                System.out.println("ID does not exist. Please try again\n");
                viewDevelopers();
            }
        }
        return id;
    }

    private int inputGameId() {
        if (Game.idBank.size() == 0) {
            System.out.println("No games available");
            return 0;
        }
        int id;
        viewGames();
        while (true) {
            System.out.print("\nGame ID: ");
            id = scanInt();
            if (Game.idBank.contains(id)) {
                break;
            } else {
                System.out.println("ID does not exist. Please try again\n");
                viewGames();
            }
        }
        return id;
    }


    public void connectDevToGame() {

        EntityManager em = emf.createEntityManager();
        System.out.print("Enter the ID of the Game you'd like to connect: ");
        int gameID = inputGameId();


        System.out.println("Enter the ID of the Developer you'd like to connect: ");
        int devID = inputDevId();

        if (gameID == 0 || devID == 0) {
            System.out.println("Developer or game not found. Returning to main");
            return;
        }
        Game game = em.find(Game.class, gameID);
        Developer dev = em.find(Developer.class, devID);
        em.getTransaction().begin();
        game.getDev().add(dev);
        dev.getGames().add(game);
        em.getTransaction().commit();
        em.close();

    }

    public void deleteGame() {
        EntityManager em = emf.createEntityManager();
        int id = inputGameId();
        Game game = em.find(Game.class, id);
        em.getTransaction().begin();

        Query all = em.createQuery("SELECT d FROM Developer d");
        List<Developer> allDevs = all.getResultList();
        for (Developer dev : allDevs) {

            final Game[] z = new Game[1];
            dev.getGames().stream().filter(x -> x.getId() == id).forEach(x -> z[0] = x);
            dev.getGames().remove(z[0]);

        }

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

    public void removeGameFromDev() {
        EntityManager em = emf.createEntityManager();
        Developer dev = em.find(Developer.class, inputDevId());
        Game game = null;
        if (dev.getGames().size() > 0) {
            while (true) {
                game = em.find(Game.class, inputGameId());
                if (game.getDev().contains(dev)) break;
                else {
                    System.out.println("Game is not made by selected developer. Please try again.");
                }
            }

        } else {
            System.out.println("Developer does not have any registered games. Returning to main menu");
            return;
        }
        game.getDev().remove(dev);
        dev.getGames().remove(game);
        em.getTransaction().begin();
        em.merge(game);
        em.merge(dev);
        em.getTransaction().commit();
        em.close();

    }
}
