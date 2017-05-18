package auction.Models;

import nl.fontys.util.Money;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = "Item.count",
                query = "select count(i) from Item as i"),
        @NamedQuery(name = "Item.Find",
                query = "select i from Item as i where i.id = :inputid"),
        @NamedQuery(name = "Item.FindAll",
                query = "select i from Item as i"),
        @NamedQuery(name = "Item.FindByDescription",
                query = "select i from Item as i where i.description = :inputdescription"),})
@Entity
public class Item implements Comparable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(targetEntity = User.class)
    private User seller;
    @ManyToOne(targetEntity = Category.class, cascade = CascadeType.PERSIST)
    private Category category;
    private String description;
    @OneToOne(targetEntity = Bid.class)
    private Bid highest;

    public Item(User seller, Category category, String description) {
        this.seller = seller;
        this.category = category;
        this.description = description;
    }

    public Item() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bid getHighestBid() {
        return highest;
    }

    public Bid newBid(User buyer, Money amount) {
        if(highest == null){
            highest = new Bid(buyer, amount);
            buyer.addItem(this);
            return highest;
        }
        if (highest.getAmount().compareTo(amount) >= 0) {
            return null;
        }
        highest = new Bid(buyer, amount);
        buyer.addItem(this);
        return highest;
    }

    public int compareTo(Object arg0) {
        //TODO
        return -1;
    }

    public boolean equals(Object o) {
        //TODO
        return false;
    }

    public int hashCode() {
        //TODO
        return 0;
    }

    public Bid getHighest() {
        return highest;
    }

    public void setHighest(Bid highest) {
        this.highest = highest;
    }
}
