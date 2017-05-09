package auction.service;

import java.util.*;
import auction.Models.User;
import auction.Repository.UserJPARepository;
import auction.Repository.UserMemoryRepository;
import auction.Repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RegistrationMgr {

    private UserRepository userRepository;

    public EntityManager entityManager = Persistence.createEntityManagerFactory("auctionPU").createEntityManager();

    public RegistrationMgr() {
        userRepository = new UserJPARepository(this.entityManager);
    }

    /**
     * Registreert een gebruiker met het als parameter gegeven e-mailadres, mits
     * zo'n gebruiker nog niet bestaat.
     * @param email
     * @return Een Userobject dat geïdentificeerd wordt door het gegeven
     * e-mailadres (nieuw aangemaakt of reeds bestaand). Als het e-mailadres
     * onjuist is ( het bevat geen '@'-teken) wordt null teruggegeven.
     */
    public User registerUser(String email) {
        if (!email.contains("@")) {
            return null;
        }
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return user;
        }
        user = new User(email);
        userRepository.create(user);
        return user;
    }

    /**
     *
     * @param email een e-mailadres
     * @return Het Userobject dat geïdentificeerd wordt door het gegeven
     * e-mailadres of null als zo'n User niet bestaat.
     */
    public User getUser(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * @return Een iterator over alle geregistreerde gebruikers
     */
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
