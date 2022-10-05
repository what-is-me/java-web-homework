package web;

import tools.SaveAndLoad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/*
 * 保存
 */
@WebServlet("/save")
public class SaveServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        var ans = request.getParameterMap();
        PrintWriter out = response.getWriter();
        out.println("<body>");
        out.println((SaveAndLoad.save(ans) ? "保存成功" : "保存失败") + "<br>3秒后跳转回主页");
        out.println("<script>\nfunction jump(){window.location.href='index.html';}\nsetTimeout(jump,3000);\n</script>");
        out.println("</body>");
    }
}
