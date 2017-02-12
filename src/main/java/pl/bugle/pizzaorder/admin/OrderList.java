package pl.bugle.pizzaorder.admin;

import pl.bugle.pizzaorder.ejb.Database;
import pl.bugle.pizzaorder.entity.Orders;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Jarosław Pawłowski
 */

@Named
@RequestScoped
public class OrderList {
    @EJB
    private Database db;
    private List<Orders> orders;

    public OrderList() {
        orders = new ArrayList<>();
    }

    public List<Orders> getOrders() {
        if (orders.isEmpty()) {
            orders = db.getOrders();
        }
        return orders;
    }

    public void update(Orders order) {
        order.setDate(new Date());
        db.updateOrder(order);
        orders = db.getOrders();
    }

    public void delete(Orders order) {
        db.removeOrder(order);
        orders = db.getOrders();
    }
}
