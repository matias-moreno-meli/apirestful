package services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import domain.Item;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class ItemMeliService {

    public Item[] getAllItem(String query) {

        URL url = null;
        Item[] items = null;

        try {
            url = new URL("https://api.mercadolibre.com/sites/MLA/search?q=" + query);
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection connection = null;

            if (urlConnection instanceof HttpURLConnection) {
                connection = (HttpURLConnection) urlConnection;
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "application/json");

            } else {

                //TODO logss
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String urlString = "";
            String current = null;

            while ((current = in.readLine()) != null) {
                urlString += current;
            }

            JsonObject jsonObject = new JsonParser().parse(urlString).getAsJsonObject();
            Gson gson = new Gson();
            items = gson.fromJson(jsonObject.getAsJsonArray("results"), Item[].class);

//            System.out.println(urlString.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return items;
    }


}
