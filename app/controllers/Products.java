package controllers;

import models.Product;
import models.StockItem;
import models.Tag;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.products.details;
import views.html.products.list;

import java.util.ArrayList;
import java.util.List;


public class Products extends Controller {

    private static final Form<Product> productForm = Form.form(Product.class);

    public static Result list() {
        List<Product> products = Product.findAll();
        return ok(list.render(products));
    }
    public static Result newProduct() {
        return ok(details.render(productForm));
    }
    public static Result details(Product product) {
        if (product == null) {
            return notFound(String.format("Product %s does not exist.", product.ean));
        }
        Form<Product> filledForm = productForm.fill(product);
        return ok(details.render(filledForm));
    }

    public static Result save() {
        Form<Product> boundForm = productForm.bindFromRequest();
        if (boundForm.hasErrors()) {
            flash("error", "Please correct the form below.");
            return badRequest(details.render(boundForm));
        }
        Product product = boundForm.get();
        List<Tag> tags = new ArrayList<Tag>();
        for (Tag tag : product.tags) {
            if (tag.id != null) {
                tags.add(Tag.findById(tag.id));
            }
        }
        product.tags = tags;
        product.save();

        StockItem stockItem = new StockItem();
        stockItem.product = product;
        stockItem.quantity = 0L;
        stockItem.save();

        flash("success", String.format("Successfully added product %s", product));
        return redirect(routes.Products.list());
    }

    public static Result delete(String ean) {
        final Product product = Product.findByEan(ean);
        if(product == null) {
            return notFound(String.format("Product %s does not exists.", ean));
        }
        product.delete();
        return redirect(routes.Products.list());
    }
}