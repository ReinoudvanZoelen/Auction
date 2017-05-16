package auction.Repository;

import auction.Models.Bid;
import auction.Models.Item;

import javax.persistence.EntityManager;
import java.util.List;

public class BidJPARepository implements BidRepository {

    private EntityManager entityManager;

    @Override
    public int count() {
        return 0;
    }

    @Override
    public void create(Bid bid) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(bid);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public void edit(Bid bid) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(bid);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public Bid find(Long id) {
        return null;
    }

    @Override
    public List<Bid> findAll() {
        return null;
    }

    @Override
    public void remove(Bid bid) {
        this.entityManager.getTransaction().begin();
        this.entityManager.remove(this.entityManager.merge(bid));
        this.entityManager.getTransaction().commit();
    }
}
