import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

@WebServlet("/picture")
public class Picture extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getOutputStream().write(Objects.requireNonNull(PictureBean.getPic("E:/web/src/" + req.getParameter("val") + "/" + new Random().nextInt(10) + ".jpg")));
        //假设上理工建筑和非上理工建筑各有10张图片，存放在两个文件夹中，以0.jpg~9.jpg命名
    }
}

class PictureBean {
    public static byte[] getPic(String imgPath) {
        var img_stream = new ByteArrayOutputStream();
        try {
            ImageIO.write(ImageIO.read(new File(imgPath)), "jpg", img_stream);
            byte[] bytes = img_stream.toByteArray();
            img_stream.close();
            return bytes;
        } catch (IOException e) {
            e.getStackTrace();
        }
        return null;
    }
}