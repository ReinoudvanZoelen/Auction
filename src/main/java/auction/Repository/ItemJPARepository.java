package auction.Repository;

import auction.Models.Item;
import auction.Models.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ItemJPARepository implements ItemRepository {

    private EntityManager entityManager;

    @Override
    public int count() {
        return 0;
    }

    @Override
    public void create(Item item) {

    }

    @Override
    public void edit(Item item) {

    }

    @Override
    public Item find(Long id) {
        return null;
    }

    @Override
    public List<Item> findAll() {
        return null;
    }

    @Override
    public List<Item> findByDescription(String description) {
        return null;
    }

    @Override
    public void remove(Item item) {

    }
}
