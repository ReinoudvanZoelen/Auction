package auction.service;

import static org.junit.Assert.*;

import auction.Repository.ItemJPARepository;
import nl.fontys.util.Money;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



import auction.Models.Category;
import auction.Models.Item;
import auction.Models.User;
import util.DatabaseCleaner;

import javax.persistence.Persistence;

public class SellerMgrTest {

    private AuctionMgr auctionMgr;
    private RegistrationMgr registrationMgr;
    private SellerMgr sellerMgr;

    @Before
    public void setUp() throws Exception {
        registrationMgr = new RegistrationMgr();
        auctionMgr = new AuctionMgr();
        sellerMgr = new SellerMgr();
    }

    @After
    public void CleanDatabase() {
        try {
            new DatabaseCleaner(sellerMgr.entityManager).clean();
        } catch (Exception exc) {
            System.out.println("The database was not cleaned. Error: " + exc);
        }
    }

    /**
     * Test of offerItem method, of class SellerMgr.
     */
    @Test
    public void testOfferItem() {
        String omsch = "omsch";

        User user1 = registrationMgr.registerUser("xx@nl");
        Category cat = new Category("cat1");
        Item item1 = sellerMgr.offerItem(user1, cat, omsch);
        assertEquals(omsch, item1.getDescription());
        assertNotNull(item1.getId());
    }

    @Test
    public void createtest(){
        ItemJPARepository jpa = new ItemJPARepository(Persistence.createEntityManagerFactory("auctionPU").createEntityManager());
        User user = this.registrationMgr.registerUser("test@sad.com");
        jpa.create(new Item(user, new Category("categorie"), "omschrijving"));
    }

    /**
     * Test of revokeItem method, of class SellerMgr.
     */
    @Test
    public void testRevokeItem() {
        String omsch = "omsch";
        String omsch2 = "omsch2";
        
    
        User seller = registrationMgr.registerUser("sel@nl");
        User buyer = registrationMgr.registerUser("buy@nl");
        Category cat = new Category("cat1");
        
            // revoke before bidding
        Item item1 = sellerMgr.offerItem(seller, cat, omsch);
        boolean res = sellerMgr.revokeItem(item1);
        assertTrue(res);
        int count = auctionMgr.findItemByDescription(omsch).size();
        assertEquals(0, count);
        
            // revoke after bid has been made
        Item item2 = sellerMgr.offerItem(seller, cat, omsch2);
        auctionMgr.newBid(item2, buyer, new Money(100, "Euro"));
        boolean res2 = sellerMgr.revokeItem(item2);
        assertFalse(res2);
        int count2 = auctionMgr.findItemByDescription(omsch2).size();
        assertEquals(1, count2);
        
        
        
        
    }

}
