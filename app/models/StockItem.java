package models;

import play.db.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by dse on 1/26/15.
 */
@Entity
public class StockItem extends Model {
    @Id
    public Long id;

    public Warehouse warehouse;

    @ManyToOne
    public Product product;

    public Long quantity;

    public String toString() {
        return String.format("$d %s", quantity, product);
    }
}
