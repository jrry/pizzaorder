package user;

import ejb.Database;
import entity.Orders;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import messages.Message;

/**
 *
 * @author Jarosław Pawłowski
 */

@Named
@RequestScoped
public class MyOrders {
    @EJB
    private Database db;

    private List<Orders> orders;
    private Message msg;

    public MyOrders() {
        msg = new Message();
        orders = new ArrayList<>();
    }

    public List<Orders> getOrders(Long id) {
        if (orders.isEmpty()) {
            orders = db.getOrdersMyById(id);
        }
        return orders;
    }

    public String status(Integer id) {
        if (id == 1) {
            return msg.getMessage("Waiting");
        }
        return msg.getMessage("Realized");
    }
}
