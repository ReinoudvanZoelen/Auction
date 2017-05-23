package auction.Models;

import com.sun.istack.internal.NotNull;
import javafx.beans.DefaultProperty;
import nl.fontys.util.FontysTime;
import nl.fontys.util.Money;

import javax.persistence.*;

@Entity
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne(targetEntity = FontysTime.class)
    private FontysTime time;
    @ManyToOne(targetEntity = User.class)
    private User buyer;
    @OneToOne(targetEntity = Money.class)
    private Money amount;
    @OneToOne(targetEntity = Item.class)
    @NotNull
    private Item item;

    public Bid() {
    }

    public Bid(User buyer, Money amount) {
        this.buyer = buyer;
        this.amount = amount;
    }

    public FontysTime getTime() {
        return time;
    }

    public void setTime(FontysTime time) {
        this.time = time;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public Item getItem() { return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
