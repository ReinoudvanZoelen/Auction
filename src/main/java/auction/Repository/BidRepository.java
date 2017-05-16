package auction.Repository;

import auction.Models.Bid;
import auction.Models.Item;

import java.util.List;

public interface BidRepository {

    /**
     *
     * @return number of item instances
     */
    int count();

    /**
     * The item is persisted. If a item with the same id allready exists an EntityExistsException is thrown
     * @param bid
     */
    void create(Bid bid);

    /**
     * Merge the state of the given item into persistant context. If the item did not exist an IllegalArgumentException is thrown
     * @param bid
     */
    void edit(Bid bid);

    /**
     *
     * @param id
     * @return the found entity instance or null if the entity does not exist
     */
    Bid find(Long id);

    /**
     *
     * @return list of item instances
     */
    List<Bid> findAll();

    /**
     * Remove the entity instance
     * @param bid - entity instance
     */
    void remove(Bid bid);
}
