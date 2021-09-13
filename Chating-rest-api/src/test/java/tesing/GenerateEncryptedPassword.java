package tesing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GenerateEncryptedPassword
{

    public static void main(String[] args)
    {

        Gson gson = new Gson();
        String json = null;
        try {
            json = readUrl(
               "http://41.65.131.17:9090/api/Feed/GetHuobiChartData?period=6&SYMBOL_CODE=EGS60121C018&Nth=-1&interval=3" );
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println( json );

        Map< String, Object > decoded =
           gson.fromJson(
              json,
              new TypeToken< Map< String, Object>>() {}.getType());
        System.out.println( decoded );

//        Answer answer = gson.fromJson( json, Answer.class );
//        System.out.println( answer.response.version );
//        System.out.println( answer.response.termsofService );
//        System.out.println( answer.response.features.get( "conditions" ));
//        System.out.println( answer.current_observation.image );
//        System.out.println( answer.current_observation.display_location );
     }

     static class Response
     {
        String                version;
        URL                   termsofService;
        Map< String, String > features;
     }

     static class Observation
     {
        Map< String, String > image;
        Map< String, String > display_location;
        // TODO: complete me
     }

     static class Location
     {
        // TODO: complete me
     }

     static class Answer
     {
        Response    response;
        Observation current_observation;
        Location    display_location;
        // TODO: complete me
     }
     
     private static String readUrl(String urlString) throws Exception {
         BufferedReader reader = null;
         try {
             URL url = new URL(urlString);
             reader = new BufferedReader(new InputStreamReader(url.openStream()));
             StringBuffer buffer = new StringBuffer();
             int read;
             char[] chars = new char[1024];
             while ((read = reader.read(chars)) != -1)
                 buffer.append(chars, 0, read); 

             return buffer.toString();
         } finally {
             if (reader != null)
                 reader.close();
         }

     }

     static class Page {
         String title;
         String link;
         String description;
         String language;
         List<Item> items;
     }

     static class Item {
         String title;
         String link;
         String description;
     }

}
