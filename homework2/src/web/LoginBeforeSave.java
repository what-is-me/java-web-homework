package web;

import javax.servlet.annotation.WebServlet;

@WebServlet("/login_save")
public class LoginBeforeSave extends Login {
    public LoginBeforeSave() {
        super("save.html");
    }
}
