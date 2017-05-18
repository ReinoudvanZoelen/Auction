package auction.service;

import auction.Models.Category;
import auction.Models.Item;
import auction.Models.User;
import auction.Repository.ItemJPARepository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class SellerMgr {

    public EntityManager entityManager = Persistence.createEntityManagerFactory("auctionPU").createEntityManager();
    private ItemJPARepository ItemRepository;

    public SellerMgr() {
        this.ItemRepository = new ItemJPARepository(this.entityManager);
    }

    /**
     * @param seller
     * @param cat
     * @param description
     * @return het item aangeboden door seller, behorende tot de categorie cat
     *         en met de beschrijving description
     */
    public Item offerItem(User seller, Category cat, String description) {
        Item item = new Item(seller, cat, description);
        this.ItemRepository.create(item);
        return item;
    }
    
     /**
     * @param item
     * @return true als er nog niet geboden is op het item. Het item word verwijderd.
     *         false als er al geboden was op het item.
     */
    public boolean revokeItem(Item item) {
        if(item.getHighestBid() == null){
            this.ItemRepository.remove(item);
            return true;
        }
        return false;
    }
}
