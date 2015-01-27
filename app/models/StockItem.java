package models;

/**
 * Created by dse on 1/26/15.
 */
public class StockItem {
    public Warehouse warehouse;
    public Product product;
    public Long quantity;

    public String toString() {
        return String.format("$d %s", quantity, product);
    }
}
