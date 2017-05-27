package auction.Models;

import javax.persistence.*;
import java.util.Iterator;
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
    public Item getOfferedItem(int id) {
        for (Item item : this.offeredItems) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    public Iterator<Item> getOfferedItems() {
        return offeredItems.iterator();
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

    public int numberOfOfferdItems() {
        return this.offeredItems.size();
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
