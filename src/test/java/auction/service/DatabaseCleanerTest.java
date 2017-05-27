package auction.service;

import org.junit.Test;
import util.DatabaseCleaner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;

public class DatabaseCleanerTest {

    final EntityManagerFactory emf = Persistence.createEntityManagerFactory("auctionPU");
    final EntityManager em = emf.createEntityManager();

    public DatabaseCleanerTest() {
    }

    @Test
    public void Clear() throws SQLException {
        new DatabaseCleaner(em).clean();
    }
}
