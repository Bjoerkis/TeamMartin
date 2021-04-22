package gameapp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Game {

    @Id
    @GeneratedValue
    private int Id;
    String name;
    String price;

    @OneToOne
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
        return "\nID: " + Id +
                "\nName: " + name +
                "\nPrice: " + price;
    }
}
