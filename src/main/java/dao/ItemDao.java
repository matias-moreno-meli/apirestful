package dao;

import domain.Item;

import java.util.HashMap;

public class ItemDao implements IItemDao {

    private HashMap<String, Item[]> itemHashMap;

    public ItemDao() {
        this.itemHashMap = new HashMap<>();
    }

    public Item[] getItemsMap(String query) {
        return this.itemHashMap.get(query);
    }

    public void saveItemsMap(String query, Item[] items) {

        if (!this.itemHashMap.containsKey(query)) {
            this.itemHashMap.put(query, items);
        }

    }
}
