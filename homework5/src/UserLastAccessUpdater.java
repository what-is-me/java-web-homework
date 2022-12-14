import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;

public class UserLastAccessUpdater {
    public static void updateLastTime(HttpServletRequest req) throws IOException {
        req.setCharacterEncoding("utf-8");
        var sess = req.getSession();
        var name = req.getParameter("name");
        var sex = req.getParameter("sex");
        var con = req.getServletContext();
        if (con.getAttribute("users") == null) con.setAttribute("users", new HashMap<String, UserInfo>());
        if (name != null) ((HashMap<String, UserInfo>) con.getAttribute("users"))
                .put(name, new UserInfo(name, sex, sess.getLastAccessedTime()));
    }
}
