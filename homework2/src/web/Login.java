package web;

import tools.HTML;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/*
 * 两个登录界面的基
 */
class Login extends HttpServlet {
    String url;

    protected Login(String url) {
        super();
        this.url = url;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("username").equals("admin") && request.getParameter("password").equals("admin")) {
            response.sendRedirect(url);
        } else {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println(HTML.html("登录失败",
                    HTML.div("<h1>登录失败</h1><p>登录失败，点击<a href='index.html'>返回主页<a></p>", "box", "",
                            "width:50%;height:50%;margin:0 auto;margin-top:10%")));
        }
    }
}
