package gameapp;

import javax.persistence.*;
import java.util.ArrayList;


@Entity
public class Game {

    static ArrayList<Integer> idBank = new ArrayList<>();
    @Id
    @GeneratedValue
    private int Id;
    private String name;
    private String price;

    @ManyToOne(cascade = CascadeType.PERSIST)
    Developer dev;


    public Game() {
    }

    public Game(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public Developer getDev() {
        return dev;
    }

    public void setDev(Developer developer) {
        this.dev = developer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setId(int id) {
        this.Id = id;

    }

    public int getId() {
        return Id;
    }


    @Override
    public String toString() {
        String end = "";
        if(dev!=null) end = "Developer: " + dev.getDeveloperName();
        return "\nID: " + Id +
                "\nName: " + name +
                "\nPrice: " + price +
                "\nDeveloper: " + end;
    }
}
