package controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import domain.Item;
import services.ItemService;
import spark.Request;
import spark.Response;

import java.util.List;

public class ItemController {

    ItemService itemService;

    public ItemController() {
        this.itemService = new ItemService();
    }

    public JsonElement prueba(Request request, Response response) {
        JsonElement jsonObject = null;
        String query = request.queryParams("query");
        String orderBy = request.queryParams("orderBy"); // Price or ListingType
        String val = request.queryParams("val"); // ASC or DES
        String tag = request.queryParams("tag"); // "good_quality_thumbnail"
        String priceRange = request.queryParams("priceRange"); // 1500-2000
        if (query != null) {
            if ((orderBy == null) && (val == null) && (tag == null) && (priceRange == null)) {
                List<String> items = itemService.getAllTitleItems(query);
                response.type("application/json");
                response.status(200);
                jsonObject = new Gson().toJsonTree(items);
            } else {
                List<Item> items = itemService.getItemsFilter(query, orderBy, val, tag, priceRange);
                response.type("application/json");
                response.status(200);
                jsonObject = new Gson().toJsonTree(items);
            }
        } else {
            response.status(400);
        }
        return jsonObject;
    }
}
