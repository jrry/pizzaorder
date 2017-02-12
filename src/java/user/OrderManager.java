package user;

import ejb.Database;
import entity.Orders;
import entity.Pizza;
import util.PizzaOrder;
import entity.Users;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import messages.Message;

/**
 *
 * @author Jarosław Pawłowski
 */

@Named
@SessionScoped
public class OrderManager implements Serializable {
    @EJB
    private Database db;

    private Double price;
    private Integer quantity;
    private List<PizzaOrder> selectedPizzas;
    private Iterator<PizzaOrder> it;
    private PizzaOrder po;
    private Orders order;
    private Message msg;
    private boolean f;

    public OrderManager() {
        if (selectedPizzas == null) {
            selectedPizzas = new ArrayList<>();
            msg = new Message();
            quantity = 0;
            price = 0.0;
        }
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<PizzaOrder> getSelectedPizzas() {
        return selectedPizzas;
    }

    public void setSelectedPizzas(List<PizzaOrder> selectedPizzas) {
        this.selectedPizzas = selectedPizzas;
    }

    public String bigsmall(PizzaOrder p) {
        Pizza tmp = db.getPizzaByID(p.getId());
        if (Double.compare(tmp.getBprice(), p.getPrice()) == 0) {
            return msg.getMessage("Big");
        }
        return msg.getMessage("Small");
    }

    public String order(Users u) {
        order = new Orders();
        order.setUser(u);
        order.setStatus(1);
        order.setPizzas(selectedPizzas);
        order.setPrice(price);
        order.setDate(new Date());
        db.createOrder(order);
        selectedPizzas.clear();
        quantity = 0;
        price = 0.0;
        return "my";
    }
    
    public void update(Pizza p, Double selp) {
        po = new PizzaOrder();
        po.setId(p.getId());
        po.setName(p.getPname());
        po.setPrice(selp);
        po.setQuantity(1);
        f = false;
        it = selectedPizzas.iterator();
        PizzaOrder tmp;
        while(it.hasNext()) {
            tmp = it.next();
            if (Long.compare(tmp.getId(), p.getId()) == 0) {
                if (Double.compare(tmp.getPrice(), selp) == 0) {
                    tmp.setQuantity(tmp.getQuantity()+1);
                    f = true;
                    break;
                }
            }
        }
        if (!f) {
            selectedPizzas.add(po);
        }
        quantity += 1;
        price += selp;
        price = Math.round(price * 100) / 100.0;
    }
    
    public void delete(PizzaOrder p, Double selp) {
        it = selectedPizzas.iterator();
        PizzaOrder tmp;
        while(it.hasNext()) {
            tmp = it.next();
            if (Long.compare(tmp.getId(), p.getId()) == 0) {
                if (Double.compare(tmp.getPrice(), selp) == 0) {
                    if (tmp.getQuantity() == 1) {
                        it.remove();
                    } else {
                        tmp.setQuantity(tmp.getQuantity()-1);
                    }
                    break;
                }
            }
        }
        quantity -= 1;
        price -= p.getPrice();
        price = Math.round(price * 100) / 100.0;
    }
}
