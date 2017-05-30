package auction.service;

import auction.Models.Bid;
import auction.Models.Category;
import auction.Models.Item;
import auction.Models.User;
import nl.fontys.util.Money;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.DatabaseCleaner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class BidirectionalTest {

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
            new DatabaseCleaner(auctionMgr.entityManager).clean();
        } catch (Exception exc) {
            System.out.println("The database was not cleaned. Error: " + exc);
        }
    }


    @Test
    public void getItem() {
        String email = "ss2@nl";
        String emailb = "bb@nl";
        String emailb2 = "bb2@nl";
        String omsch = "omsch_bb";

        User seller = registrationMgr.registerUser(email);
        User buyer = registrationMgr.registerUser(emailb);

        // eerste bod
        Category cat = new Category("cat9");
        Item item1 = sellerMgr.offerItem(seller, cat, omsch);
        Money money1 = new Money(10, "eur");
        Bid new1 = auctionMgr.newBid(item1, buyer, money1);

        assertNotNull(item1.getHighest());
        assertNotNull(new1.getItem());
    }

}
