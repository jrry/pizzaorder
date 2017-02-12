package pl.bugle.pizzaorder.ejb;

import pl.bugle.pizzaorder.entity.Components;
import pl.bugle.pizzaorder.entity.Users;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author Jarosław Pawłowski
 */

@Singleton
@Startup
public class Config {
    @EJB
    private Database db;

    private Users puser;
    private Components c;
    
    @PostConstruct
    public void CreateData() {
        if (db.getUsers().isEmpty()) {
            puser = new Users(1L, "ADMIN", "Admin", "Admin", "jaroslaw@pawlowski.pl", "500000000", "ul. Admina 1");
            db.createUser(puser);
            puser = new Users(2L, "USER", "User", "User", "jaroslaw@pawlowski.pl", "600000000", "ul. Usera 2");
            db.createUser(puser);
        }
        if (db.getComponents().isEmpty()) {
            c = new Components(1L, "ser", 1);
            db.createComponent(c);
            c = new Components(2L, "szynka", 2);
            db.createComponent(c);
            c = new Components(3L, "salami", 2);
            db.createComponent(c);
            c = new Components(4L, "boczek", 2);
            db.createComponent(c);
            c = new Components(5L, "kurczak", 2);
            db.createComponent(c);
            c = new Components(6L, "kabanos", 2);
            db.createComponent(c);
            c = new Components(7L, "peperoni", 3);
            db.createComponent(c);
            c = new Components(8L, "pieczarki", 3);
            db.createComponent(c);
            c = new Components(9L, "pomidor", 3);
            db.createComponent(c);
            c = new Components(10L, "oliwki", 3);
            db.createComponent(c);
            c = new Components(11L, "kukurydza", 3);
            db.createComponent(c);
            c = new Components(12L, "ananas", 3);
            db.createComponent(c);
            c = new Components(13L, "papryka", 3);
            db.createComponent(c);
            c = new Components(14L, "groszek", 3);
            db.createComponent(c);
        }
    }
    
    @PreDestroy
    public void deleteData() {
        
    }
}
