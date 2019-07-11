import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import controller.ItemController;
import domain.Item;

import static spark.Spark.get;

public class Main {

    public static void main(String[] args) {

        ItemController itemController = new ItemController();

        get("items", (request, response) -> {

            String q = request.queryParams("query");

            Item[] items = itemController.getAll(q);

            response.type("application/json");
            response.status(200);

            JsonElement jsonObject = new Gson().toJsonTree(items);
            response.body(jsonObject.getAsString());

            return response;
        });


    }
}
