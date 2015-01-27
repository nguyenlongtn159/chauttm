package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dse on 1/26/15.
 */
@Entity
public class Warehouse extends Model {
    @Id
    public Long id;

    public String name;

    @OneToMany(mappedBy = "warehouse")
    public List<StockItem> stock = new ArrayList();

    public String toString() {
        return name;
    }
}
