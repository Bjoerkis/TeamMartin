package gameapp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Developer {

    static ArrayList<Integer> idBank = new ArrayList<>();
    @Id
    private int companyID;
    private String developerName;
    private String earnings;

    @OneToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<Game> games = new ArrayList<>();

    public Developer() {

    }


    public Developer(int companyID, String developerName, String earnings) {
        this.companyID = companyID;
        this.developerName = developerName;
        this.earnings = earnings;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }


    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public String getDeveloperName() {
        return developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    public String getEarnings() {
        return earnings;
    }

    public void setEarnings(String earnings) {
        this.earnings = earnings;
    }

    @Override
    public String toString() {
        String end = "";
        for (Game game : games) {
            end += game.getName() + " ";
        }
        return "\nCompanyID: " + companyID +
                "\nDeveloperName: " + developerName +
                "\nEarnings: " + earnings +
                "\nGames: " + end;
    }
}
