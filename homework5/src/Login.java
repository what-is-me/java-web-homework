import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/login")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var sess = req.getSession();
        sess.setAttribute("name", req.getParameter("name"));
        sess.setAttribute("sex", req.getParameter("sex"));
        var con = req.getServletContext();
        if (con.getAttribute("users") == null) con.setAttribute("users", new HashMap<String, UserInfo>());
        ((HashMap<String, UserInfo>) con.getAttribute("users"))
                .put(req.getParameter("name"),
                        new UserInfo(req.getParameter("name"),
                                req.getParameter("sex"),
                                sess.getLastAccessedTime()));
    }
}
