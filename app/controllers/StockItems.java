package controllers;

import models.Product;
import models.StockItem;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.products.list;

import java.util.List;


/**
 * Created by dse on 2/1/15.
 */
public class StockItems extends Controller {
    public static Result index() {
        List<StockItem> items = StockItem.find
                .where()
                .ge("quantity", 300)
                .orderBy("quantity desc")
                .setMaxRows(5)
                .findList();
        return ok(items.toString());
    }
}
