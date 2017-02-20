/**
 * Created by achen on 11/13/2016.
 */
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class portraitdownloader {
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("C:\\Users\\achen\\IdeaProjects\\PADImageDownloader\\src\\monsters.json"));
            JSONArray jsonArray = (JSONArray) obj;
            System.setProperty("http.agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            for (Object o : jsonArray) {
                JSONObject card = (JSONObject) o;
                File file60 = new File("C:\\Users\\achen\\IdeaProjects\\PadImageDownloader\\src\\portraits60\\"+card.get("id")+".png");
                if (!file60.exists()) {
                    URL url = new URL("https://www.padherder.com"+card.get("image60_href"));
                    System.out.println(url);
                    final SSLContext sc = SSLContext.getInstance("SSL");
                    sc.init(null, getTrustingManager(), new java.security.SecureRandom());
                    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//                    URLConnection uc = url.openConnection();
//                    uc.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
//                    uc.connect();
                    InputStream inputStream = url.openStream();
                    //System.out.println(url);
                    BufferedImage image = ImageIO.read(inputStream);
                    ImageIO.write(image, "png", file60);
//                    ByteArrayOutputStream out = new ByteArrayOutputStream();
//                    FileOutputStream outputStream = new FileOutputStream(file60);
//                    int length;
//                    byte[] b = new byte[2048];
//                    while((length = inputStream.read(b)) != -1) {
//                        out.write(b, 0, length);
//                    }
//                    inputStream.close();
//                    out.close();
//                    byte[] response = out.toByteArray();
//                    outputStream.write(response);
//                    outputStream.close();
                }
                File file40 = new File("C:\\Users\\achen\\IdeaProjects\\PadImageDownloader\\src\\portraits40\\"+card.get("id")+".png");
                if (!file40.exists()) {
                    URL url = new URL("https://www.padherder.com"+card.get("image40_href"));
                    final SSLContext sc = SSLContext.getInstance("SSL");
                    sc.init(null, getTrustingManager(), new java.security.SecureRandom());
                    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//                    URLConnection uc = url.openConnection();
//                    uc.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
//                    uc.connect();
                    InputStream inputStream = url.openStream();
                    BufferedImage image = ImageIO.read(inputStream);
                    ImageIO.write(image, "png", file40);
//                    ByteArrayOutputStream out = new ByteArrayOutputStream();
//                    FileOutputStream outputStream = new FileOutputStream(file40);
//                    int length;
//                    byte[] b = new byte[2048];
//                    while((length = inputStream.read(b)) != -1) {
//                        out.write(b, 0, length);
//                    }
//                    inputStream.close();
//                    out.close();
//                    byte[] response = out.toByteArray();
//                    outputStream.write(response);
//                    outputStream.close();
                }
            }
        } catch (ParseException | IOException | NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
    }
    private static TrustManager[] getTrustingManager() {
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        } };
        return trustAllCerts;
    }
}
