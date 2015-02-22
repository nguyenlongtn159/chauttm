package controllers;

import models.UserAccount;
import play.data.Form;
import play.mvc.*;

import views.html.*;

import static play.data.Form.form;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }
    public static Result login() {
        return ok(login.render(form(Login.class)));
    }
    public static Result authenticate() {
        Form<Login> loginForm = form(Login.class).bindFromRequest();
        String email = loginForm.get().email;
        String password = loginForm.get().password;
        if (UserAccount.authenticate(email, password) == null) {
            return  forbidden("invalid password");
        }
        session().clear();
        session("email", email);

        return redirect(routes.Products.list(0));
    }

    public static class Login {
        public String email;
        public String password;
    }
}
