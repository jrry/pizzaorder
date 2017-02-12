package pl.bugle.pizzaorder.util;

import pl.bugle.pizzaorder.entity.Orders;
import java.util.Comparator;

/**
 *
 * @author Jarosław Pawłowski
 */

public class CompareOrders implements Comparator<Orders> {
    int cmp;

    @Override
    public int compare(Orders o1, Orders o2) {
        cmp = Integer.compare(o1.getStatus(), o2.getStatus());
        if (cmp == 0) {
            return o1.getDate().compareTo(o2.getDate());
        }
        return cmp;
    }
}
