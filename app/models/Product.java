package models;

import play.mvc.PathBindable;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import scala.util.Either;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dse on 1/20/15.
 */
@Entity
public class Product extends Model implements PathBindable<Product>
{
    @Id
    public Long id;

    @Constraints.Required
    public String ean;

    @Constraints.Required
    public String name;

    public String description;

    @OneToMany(mappedBy="product")
    public List<StockItem> stockItems;

    public byte[] picture;

    @ManyToMany
    public List<Tag> tags;

    public static Finder<Long,Product> find = new Finder<Long,Product>(
            Long.class, Product.class
    );

    public Product() {}
    public Product(String ean, String name, String description) {
        this.ean = ean;
        this.name = name;
        this.description = description;
    }
    public String toString() {
        return String.format("%s - %s", ean, name);
    }

    public static List<Product> findAll() {
        return find.all();
    }

    public static Product findByEan(String ean) {
        return find.where().eq("ean", ean).findUnique();
    }

    public static List<Product> findByName(String term) {
        return find.where().eq("name", term).findList();
    }

    private static List<Product> products;
    static {
        products = new ArrayList<Product>();
        products.add(new Product("1111111111111", "Paperclips 1",
                "Paperclips description 1"));
        products.add(new Product("2222222222222", "Paperclips 2",
                "Paperclips description 2"));
        products.add(new Product("3333333333333", "Paperclips 3",
                "Paperclips description 3"));
        products.add(new Product("4444444444444", "Paperclips 4",
                "Paperclips description 4"));
        products.add(new Product("5555555555555", "Paperclips 5",
                "Paperclips description 5"));
    }

    @Override
    public Product bind(String key, String value) {
        return findByEan(value);
    }

    @Override
    public String unbind(String key) {
        return ean;
    }

    @Override
    public String javascriptUnbind() {
        return ean;
    }
}
