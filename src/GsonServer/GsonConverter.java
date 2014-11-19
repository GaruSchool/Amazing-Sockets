package GsonServer;


import com.google.gson.Gson;

/**
 * Created by t.garuglieri on 19/11/2014.
 */
public class GsonConverter {

    public static String convertObject(Object o) {
        return new Gson().toJson(o);
    }

    public static Object getObject(String jsonString, Class objClass) {
        return new Gson().fromJson(jsonString, objClass);
    }

}
