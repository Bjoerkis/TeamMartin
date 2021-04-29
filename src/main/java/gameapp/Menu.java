package gameapp;


import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    Scanner input = new Scanner(System.in);
    Management Manager = new Management();

    public void MainMenu() {

        System.out.println("\n=================================");
        System.out.println("              Menu               ");
        System.out.println("=================================");
        System.out.println("1. Show data");
        System.out.println("2. Add data");
        System.out.println("3. Edit data");
        System.out.println("4. Delete data");
        System.out.println("\n0.Exit");
        System.out.println("=================================");

        int choice = scanInt();

        switch (choice) {

            case 1:
                showData();
                break;
            case 2:
                addData();
                break;
            case 3:
                editData();
                break;
            case 4:
                deleteData();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("Incorrect input");

        }

    }
    private void deleteData(){
        System.out.println("\n=================================");
        System.out.println("              Delete               ");
        System.out.println("=================================");
        System.out.println("1. Delete game");
        System.out.println("2. Remove game from developer");
        System.out.println("\n0. Return to main menu");
        System.out.println("=================================");
        int choice  = scanInt();
        switch(choice){
            case 1:
                Manager.deleteGame();
                break;
            case 2:
               Manager.removeGameFromDev();
                break;
            case 0:
                return;
            default:
                System.out.println("Incorrect input");

        }


    }

    private void editData() {
        System.out.println("\n=================================");
        System.out.println("              Edit               ");
        System.out.println("=================================");
        System.out.println("1. Edit game");
        System.out.println("2. Edit developer");
        System.out.println("3. Connect game to developer");
        System.out.println("\n0. Return to main menu");
        System.out.println("=================================");
        int choice = scanInt();
        switch (choice) {
            case 1:
                Manager.editGame();
                break;
            case 2:
                Manager.editDeveloper();
                break;
            case 3:
                Manager.connectDevToGame();
                break;
            case 0:
                return;
            default:
                System.out.println("Incorrect input");
        }

    }

    private void addData() {
        System.out.println("\n=================================");
        System.out.println("              Add data               ");
        System.out.println("=================================");
        System.out.println("1. Add new game");
        System.out.println("2. Add new developer");
        System.out.println("\n0. Return to main menu");
        System.out.println("=================================");
        int choice = scanInt();
        switch (choice) {
            case 1:
                Manager.newGame();
                break;
            case 2:
                Manager.newDeveloper();
                break;
            case 0:
                return;
            default:
                System.out.println("Incorrect input");
        }
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

    private void showData() {

        System.out.println("\n=================================");
        System.out.println("            View Data               ");
        System.out.println("=================================");
        System.out.println("1. View all");
        System.out.println("2. View games");
        System.out.println("3. View developer");
        System.out.println("\n0.Return to Main menu");
        System.out.println("=================================");

        int choice = scanInt();

        switch (choice) {

            case 1:
                Manager.viewAll();
                break;
            case 2:
                Manager.viewGames();
                break;
            case 3:
                Manager.viewDevelopers();
                break;
            case 0:
                return;
            default:
                System.out.println("Incorrect input");

        }

    }


}
