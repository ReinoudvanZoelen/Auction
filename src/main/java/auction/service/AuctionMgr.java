package auction.service;

import auction.Models.Bid;
import auction.Models.Item;
import auction.Models.User;
import auction.Repository.BidJPARepository;
import auction.Repository.ItemJPARepository;
import nl.fontys.util.Money;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class AuctionMgr {

    public EntityManager entityManager = Persistence.createEntityManagerFactory("auctionPU").createEntityManager();

    private ItemJPARepository ItemRepository = new ItemJPARepository(this.entityManager);
    private BidJPARepository BidRepository = new BidJPARepository();

    /**
     * @param id
     * @return het item met deze id; als dit item niet bekend is wordt er null
     * geretourneerd
     */
    public Item getItem(Long id) {
        Item item = this.ItemRepository.find(id);
        if (item != null) {
            return item;
        } else {
            return null;
        }
    }


    /**
     * @param description
     * @return een lijst met items met @desciption. Eventueel lege lijst.
     */
    public List<Item> findItemByDescription(String description) {
        List<Item> items = ItemRepository.findByDescription(description);
        if (items == null) {
            items = new ArrayList<>();
        }
        return items;
    }

    /**
     * @param item
     * @param buyer
     * @param amount
     * @return het nieuwe bod ter hoogte van amount op item door buyer, tenzij
     * amount niet hoger was dan het laatste bod, dan null
     */
    public Bid newBid(Item item, User buyer, Money amount) {
        Bid bid = item.newBid(buyer, amount);

        if (bid != null) {
            item.setHighest(bid);
            return item.getHighestBid();
        }

        return null;
    }
}
