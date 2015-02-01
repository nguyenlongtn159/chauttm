import com.avaje.ebean.Ebean;
import play.*;
import models.*;
import play.libs.Yaml;
import java.util.List;
import java.util.Map;

public class Global extends GlobalSettings {

    public void onStart(Application app) {
        Logger.info("Application has started");
        InitialData.insert(app);
    }

    static class InitialData {
        public static void insert(Application app) {
            Map<String, List<Object>> all =
                    (Map<String, List <Object>>) Yaml.load("initial-data.yml");
            if (Ebean.find(Tag.class).findRowCount() == 0) {
                Ebean.save(all.get("tags"));
            }

            if (Ebean.find(Product.class).findRowCount() == 0) {
                Ebean.save(all.get("products"));
            }
        }
    }

    public void onStop(Application app) {
        Logger.info("Application shutdown...");
    }
}