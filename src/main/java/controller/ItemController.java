package controller;

import domain.Item;
import services.ItemService;

import java.util.List;

public class ItemController {


    ItemService itemService;

    public ItemController() {
        this.itemService = new ItemService();
    }

    public List<String> getAllTitleItems(String query) {
        return itemService.getAllTitleItems(query);
    }

    public List<Item> getAllItems(String query, String orderBy, String val, String tag, String priceRange) {

        return itemService.getItemsFilter(query, orderBy, val, tag, priceRange);
    }

}
