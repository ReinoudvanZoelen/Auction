package auction.Models;

import javax.persistence.*;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = "User.count",
                query = "select count(u) from User as u"),
        @NamedQuery(name = "User.FindAll",
                query = "select u from User as u"),
        @NamedQuery(name = "User.FindByEmail",
                query = "select u from User as u where u.email = :inputemail"),})
@Entity
public class User {

    @OneToMany(targetEntity = Item.class)
    Set<Item> offeredItems;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String email;

    public User() {
    }

    public User(String email) {
        this.email = email;
    }

    public void addItem(Item item) {
        if (item != null) {
            offeredItems.add(item);
        }
    }

    // Getters and Setters
    public Set<Item> getOfferedItems() {
        return offeredItems;
    }

    public int numberOfOfferedItems() {
        return offeredItems.size();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
