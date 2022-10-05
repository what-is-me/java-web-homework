package web;

import javax.servlet.annotation.WebServlet;

@WebServlet("/login_grade")
public class LoginBeforeTable extends Login {
    public LoginBeforeTable() {
        super("grade_table");
    }
}
