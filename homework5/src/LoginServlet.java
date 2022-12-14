import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

@WebServlet(name = "Login", urlPatterns = {"/login", "/user-info"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        var sess = req.getSession();
        var name = req.getParameter("name");
        var sex = req.getParameter("sex");
        sess.setAttribute("name", name);
        sess.setAttribute("sex", sex);
        var con = req.getServletContext();
        var users = (HashMap<String, UserInfo>) con.getAttribute("users");
        if ("".equals(name) || (users != null && (users.containsKey(name) && new Date().getTime() - OnLineUsersServlet.TIMEOUT < users.get(name).time))) {
            resp.sendRedirect("login.html");
            return;
        }
        UserLastAccessUpdater.updateLastTime(req);
        resp.sendRedirect("index.html");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var sess = req.getSession();
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        var name = (String) sess.getAttribute("name");
        var sex = (String) sess.getAttribute("sex");
        UserLastAccessUpdater.updateLastTime(req);
        resp.getWriter().printf("{\"name\":\"%s\",\"sex\":\"%s\"}", name, sex);
    }
}
