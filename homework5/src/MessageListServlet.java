import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet("/msgs")
public class MessageListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        UserLastAccessUpdater.updateLastTime(req);
        var con = req.getServletContext();
        var name = req.getSession().getAttribute("name");
        if (con.getAttribute("msgs") == null) con.setAttribute("msgs", new ArrayList<Message>());
        ArrayList<Message> msgs = new ArrayList<>();
        ((ArrayList<Message>) con.getAttribute("msgs")).forEach((msg) -> {
            if (msg.v.equals(name) || "all".equals(msg.v) || msg.u.equals(name)) msgs.add(msg);
        });
        resp.getWriter().println(msgs);
        //System.out.println(msgs);
    }
}
