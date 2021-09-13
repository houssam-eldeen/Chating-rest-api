package tesing;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Connection
{

    public static void main(String[] args) throws Exception {
        
     // Sending get request
        URL url = new URL("http://10.2.100.125:8081/api/v1/data/");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestProperty("Authorization","Bearer "+"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlbmcuaG91c3NhbUBob3RtYWlsLmNvbSIsImNyZWF0ZWQiOjE2MjM2NjYxNDk3OTEsImV4cCI6MTYyNDI3MDk0OX0.utybXkDh-Zn-Q6W-UJT53nreN0EJ69Xy-q32dbN9E90");
        //e.g. bearer token= eyJhbGciOiXXXzUxMiJ9.eyJzdWIiOiPyc2hhcm1hQHBsdW1zbGljZS5jb206OjE6OjkwIiwiZXhwIjoxNTM3MzQyNTIxLCJpYXQiOjE1MzY3Mzc3MjF9.O33zP2l_0eDNfcqSQz29jUGJC-_THYsXllrmkFnk85dNRbAw66dyEKBP5dVcFUuNTA8zhA83kk3Y41_qZYx43T

        conn.setRequestProperty("Content-Type","application/json");
        conn.setRequestMethod("GET");


        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String output;

        StringBuffer response = new StringBuffer();
        while ((output = in.readLine()) != null) {
            response.append(output);
        }

        in.close();
        // printing result from response
        System.out.println("Response:-" + response.toString());

    }
}
