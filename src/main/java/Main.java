import com.google.gson.Gson;
import com.google.gson.JsonElement;
import controller.ItemController;
import domain.Item;

import java.util.List;

import static spark.Spark.get;

public class Main {

    public static void main(String[] args) {

        ItemController itemController = new ItemController();

        get("items", (request, response) -> {

            JsonElement jsonObject = null;

            String query = request.queryParams("query");
            String price = request.queryParams("price");
            String listingType = request.queryParams("listingType");
            String tag = request.queryParams("tag");
            String priceRange = request.queryParams("priceRange");

            if (query != null) {
                if ((price == null) && (listingType == null) && (tag == null) && (priceRange == null)) {

                    List<String> items = itemController.getAllTitleItems(query);
                    response.type("application/json");
                    response.status(200);

                    jsonObject = new Gson().toJsonTree(items);

                } else {

                    List<Item> items = itemController.getAllItems(query, price, listingType, tag, priceRange);
                    response.type("application/json");
                    response.status(200);

                    jsonObject = new Gson().toJsonTree(items);
                }


            } else {
                response.status(400);
            }

            return jsonObject;


        });

    }
}
