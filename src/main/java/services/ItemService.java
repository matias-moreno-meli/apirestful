package services;

import domain.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ItemService {

    private HashMap<String, Item[]> itemHashMap;
    private ApiMeliService apiMeliService;
    private List<Item> items;

    public ItemService() {
        this.itemHashMap = new HashMap<>();
        this.apiMeliService = new ApiMeliService();
        this.items = new ArrayList<>();
    }

    public List<String> getAllTitleItems(String query) {

        this.items = Arrays.asList(getAllItems(query));
        List<String> resuts = new ArrayList<>();

        for (Item i : items) {
            resuts.add(i.getTitle());
        }

        return resuts;
    }

    public List<Item> getItemsFilter(String query, String price, String listingType, String tag, String priceRange) {
        this.items = Arrays.asList(getAllItems(query));

        return  null;

    }

    private Item[] getAllItems(String query) {

        Item[] items = getItemsMap(query);

        if (items == null) {
            items = apiMeliService.getAllItem(query);
            saveItemsMap(query, items);

        }

        return items;

    }


    private void saveItemsMap(String query, Item[] items) {

        if (!this.itemHashMap.containsKey(query)) {
            this.itemHashMap.put(query, items);
        }

    }

    private Item[] getItemsMap(String query) {
        return this.itemHashMap.get(query);
    }


}
