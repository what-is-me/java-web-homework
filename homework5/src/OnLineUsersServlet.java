import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@WebServlet("/all-users")
public class OnLineUsersServlet extends HttpServlet {
    public static final long TIMEOUT=30000;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        var out=resp.getWriter();
        var con=req.getServletContext();
        var users=(HashMap<String, UserInfo>) con.getAttribute("users");
        long now=new Date().getTime();
        List<String>tmp=new ArrayList<>();
        users.forEach((name,user)->{if(now-TIMEOUT<user.time){tmp.add(user.toJson());}});
        out.println(tmp);
    }
}
