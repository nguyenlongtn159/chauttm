import com.avaje.ebean.Page;
import models.Product;
import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.libs.F.*;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

import static org.fluentlenium.core.filter.FilterConstructor.*;

public class IntegrationTest {

    /**
     * add your integration test here
     * in this example we just check if the welcome page is being shown
     */
    @Test
    public void test() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                browser.goTo("http://localhost:3333");
                assertThat(browser.pageSource()).contains("Your new application is ready.");
            }
        });
    }

    @Test
    public void findByEan() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Product product = Product.findByEan("1");
                assertThat(product.name).isEqualTo("11");
            }
        });}

    @Test
    public void pagination() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Page<Product> products = Product.find(0);
                assertThat(products.getTotalRowCount()).isEqualTo(6);
                assertThat(products.getList().size()).isEqualTo(5);
            }
        });
    }
}
