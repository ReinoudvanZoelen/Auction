package auction.Models;

import javax.persistence.*;

@NamedQueries({
    @NamedQuery(name = "User.count",
                query = "select count(u) from User as u"),
    @NamedQuery(name = "User.FindAll",
                query = "select u from User as u"),
    @NamedQuery(name = "User.FindByEmail",
                query = "select u from User as u where u.email = :inputemail"),})
@Entity
public class User {

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String email;

    public User() {
    }

    public User(String email) {
        this.email = email;

    }


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
