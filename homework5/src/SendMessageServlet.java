import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/send")
public class SendMessageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        UserLastAccessUpdater.updateLastTime(req);
        var sess = req.getSession();
        var con = req.getServletContext();
        if (con.getAttribute("msgs") == null) con.setAttribute("msgs", new ArrayList<Message>());
        var msgs = ((ArrayList<Message>) con.getAttribute("msgs"));
        var msg=new Message((String) sess.getAttribute("name"),(String) sess.getAttribute("sex"),req.getParameter("to"),req.getParameter("msg"),sess.getLastAccessedTime());
        msgs.add(msg);
        con.setAttribute("msgs", msgs);
        //System.out.println(req.getParameter("msg"));
        resp.sendRedirect("index.html");
    }
}
