package gameapp;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Developer {

    @Id
    private int  companyID;
    private String developerName;
    private String earnings;

    public Developer(){

    }

    public Developer(int companyID, String developerName, String earnings) {
        this.companyID = companyID;
        this.developerName = developerName;
        this.earnings = earnings;
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
        return  "\nCompanyID: " + companyID +
                "\nDeveloperName: " + developerName +
                "\nEarnings: " + earnings;
    }
}
