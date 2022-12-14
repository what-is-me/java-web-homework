import java.util.Date;

public class Message {
    public String u, v;
    public String usex;
    public String msg;
    public long time;

    public Message(String sender, String usex, String receiver, String msg, long time) {
        u = sender == null ? "null" : sender;
        this.usex = usex == null ? "保密" : usex;
        v = receiver == null ? "null" : receiver;
        this.msg = msg == null ? "" : msg.replace("\\","\\\\").replace("\n","\\n");
        this.time = time;
    }

    public String toJson() {
        String name;
        if ("all".equals(u)) name = u;
        else name = String.format("%s(%s)", u, usex);
        return String.format("{\"sender\":\"%s\",\"receiver\":\"%s\",\"msg\":\"%s\",\"time\":\"%s\"}", name, v, msg, new Date(time));
    }

    @Override
    public String toString() {
        return toJson();
    }
}
