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
            String orderBy = request.queryParams("orderBy"); // Price or ListingType
            String val = request.queryParams("val"); // ASC or DES
            String tag = request.queryParams("tag"); // "good_quality_thumbnail"
            String priceRange = request.queryParams("priceRange"); // 1500-2000

            if (query != null) {
                if ((orderBy == null) && (val == null) && (tag == null) && (priceRange == null)) {

                    List<String> items = itemController.getAllTitleItems(query);
                    response.type("application/json");
                    response.status(200);

                    jsonObject = new Gson().toJsonTree(items);

                } else {

                    List<Item> items = itemController.getAllItems(query, orderBy, val, tag, priceRange);
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
