package ejb;

import entity.Components;
import entity.Orders;
import entity.Pizza;
import entity.Users;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import util.CompareComponents;
import util.CompareOrders;

/**
 *
 * @author Jarosław Pawłowski
 */

@Stateless
public class Database {
    @PersistenceContext
    private EntityManager em;

    public Users getUserByUsernameLower(String username) {
        try {
            return (Users) em.createNamedQuery("Users.findByUsernameLower").
                    setParameter("username", username).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public Users getUserByUsername(String username) {
        try {
            return (Users) em.createNamedQuery("Users.findByUsername").
                    setParameter("username", username).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
    
    public Users getUserByID(Long id) {
        try {
            return (Users) em.createNamedQuery("Users.findById").
                    setParameter("id", id).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
    
    public void createUser(Users puser) {
        try {
            em.persist(puser);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void updateUser(Users puser) {
        try {
            em.merge(puser);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public void removeUser(Users puser) {
        try {
            em.remove(em.find(Users.class, puser.getId()));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public List<Users> getUsers() {
        try {
            return em.createNamedQuery("Users.findAll").getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public void createPizza(Pizza pizza) {
        try {
            em.persist(pizza);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void updatePizza(Pizza pizza) {
        try {
            em.merge(pizza);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void removePizza(Pizza pizza) {
        try {
            em.remove(em.find(Pizza.class, pizza.getId()));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public Pizza getPizzaByID(Long id) {
        try {
            return (Pizza) em.createNamedQuery("Pizza.findById").
                    setParameter("id", id).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public List<Pizza> getPizzas() {
        try {
            return em.createNamedQuery("Pizza.findAll").getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public void createComponent(Components c) {
        try {
            em.persist(c);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public void updateComponent(Components c) {
        try {
            em.merge(c);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public void removeComponent(Components c) {
        try {
            em.remove(em.find(Components.class, c.getId()));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public List<Components> getComponents() {
        try {
            List<Components> l;
            l = em.createNamedQuery("Components.findAll").getResultList();
            Collections.sort(l, new CompareComponents());
            return l;
        } catch (NoResultException nre) {
            return null;
        }
    }
    
    public Components getComponentsByName(String name) {
        try {
            return (Components) em.createNamedQuery("Components.findByName").
                    setParameter("name", name).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
    
    public void createOrder(Orders order) {
        try {
            em.persist(order);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public List<Orders> getOrders() {
        try {
            List<Orders> l;
            l = em.createNamedQuery("Orders.findAll").getResultList();
            Collections.sort(l, new CompareOrders());
            return l;
        } catch (NoResultException nre) {
            return null;
        }
    }

    public List<Orders> getOrdersMyById(Long id) {
        try {
            List<Orders> l;
            l = em.createNamedQuery("Orders.findMy").
                setParameter("id", id).getResultList();
            Collections.sort(l, new CompareOrders());
            return l;
        } catch (NoResultException nre) {
            return null;
        }
    }

    public void updateOrder(Orders order) {
        try {
            em.merge(order);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void removeOrder(Orders order) {
        try {
            em.remove(em.find(Orders.class, order.getId()));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
}
