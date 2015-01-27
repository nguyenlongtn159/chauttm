package models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dse on 1/26/15.
 */
public class Warehouse {
    public String name;

    public List<StockItem> stock = new ArrayList();

    public String toString() {
        return name;
    }
}
