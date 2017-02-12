package util;

import entity.Components;
import java.util.Comparator;

/**
 *
 * @author Jarosław Pawłowski
 */

public class CompareComponents implements Comparator<Components> {
    int cmp;

    @Override
    public int compare(Components o1, Components o2) {
        cmp = Integer.compare(o1.getPriority(), o2.getPriority());
        if (cmp == 0) {
            return o1.getName().compareTo(o2.getName());
        }
        return cmp;
    }
}
