package auction.Repository;

import auction.Models.Item;
import auction.Models.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ItemJPARepository implements ItemRepository {

    private EntityManager entityManager;

    public ItemJPARepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public int count() {
        Query q = entityManager.createNamedQuery("Item.count", Item.class);
        int result = (int) q.getSingleResult();
        return result;
    }

    @Override
    public void create(Item item) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(item);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public void edit(Item item) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(item);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public void remove(Item item) {
        this.entityManager.getTransaction().begin();
        this.entityManager.remove(this.entityManager.merge(item));
        this.entityManager.getTransaction().commit();
    }

    @Override
    public Item find(Long id) {
        Query q = entityManager.createNamedQuery("Item.Find", User.class).setParameter("inputid", id);

        try {
            return (Item) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("A result has not been found for id " + id);
            return null;
        }
    }

    @Override
    public List<Item> findAll() {
        Query q = entityManager.createNamedQuery("Item.FindAll", Item.class);

        List<Item> result = q.getResultList();

        return result;
    }

    @Override
    public List<Item> findByDescription(String description) {
        Query q = entityManager.createNamedQuery("Item.FindByDescription", Item.class).setParameter("inputdescription", description);

        try {
            return q.getResultList();
        } catch (Exception ex) {
            System.out.println("A result has not been found for description " + description);
            return null;
        }
    }


}
