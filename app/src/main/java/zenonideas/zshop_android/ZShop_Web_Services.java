package zenonideas.zshop_android;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ZShop_Web_Services {

    public static String url1 = "http://192.168.8.101:8084/ZShop/";

    public static Object getConnection(String servletName, Object object) {

        try {
            URL MyServletUrl = new URL(url1 + servletName);
            HttpURLConnection connection = (HttpURLConnection) MyServletUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.connect();

            ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());
            out.writeObject(object);
            out.close();
            InputStream in1 = connection.getInputStream();
            if (in1 != null) {
                ObjectInputStream in = new ObjectInputStream(in1);
                return in.readObject();
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

}
