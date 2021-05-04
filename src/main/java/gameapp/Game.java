package gameapp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Game {

    static ArrayList<Integer> idBank = new ArrayList<>();
    @Id
    @GeneratedValue
    private int Id;
    private String name;
    private String price;


    public List<Developer> getDev() {
        return dev;
    }

    public void setDev(List<Developer> dev) {
        this.dev = dev;
    }

    @ManyToMany(cascade = CascadeType.PERSIST,mappedBy = "games")
    private List<Developer> dev = new ArrayList<>();



    public Game() {
    }

    public Game(String name, String price) {
        this.name = name;
        this.price = price;
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
        String end = "Developer: ";
        if(dev.size()!=0){
            for(int x=0; x<dev.size(); x++){
                Developer devs = dev.get(x);
                end+=devs.getDeveloperName() + ", ";
            }
        }
        return "\nID: " + Id +
                "\nName: " + name +
                "\nPrice: " + price +
                "\n" + end;
    }
}
