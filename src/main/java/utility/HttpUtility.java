package utility;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpUtility {

    public static String makeHttpGetRequest(String urlStr) throws IOException {
        //pmst POSTMAN asi, et tagastaks mulle JSON APIlt
        
        HttpURLConnection httpUrlConnection = makeHttpUrlConnection(urlStr);
        BufferedReader br = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));
        
        StringBuilder sb = new StringBuilder();
               
        while(br.ready()){
            sb.append((char)br.read());
        }
        return sb.toString();
    }

    public static HttpURLConnection makeHttpUrlConnection(String urlString) throws IOException{
        
        //URL String -> URL - > a real HTTP connection that the API can use
        return (HttpURLConnection) new URL(urlString).openConnection();
    }

}