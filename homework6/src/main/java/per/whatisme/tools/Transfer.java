package per.whatisme.tools;

import com.google.gson.Gson;

public class Transfer {
    static Gson gson = new Gson();

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T toObj(String json, Class<T> cls) {
        return gson.fromJson(json, cls);
    }
}
