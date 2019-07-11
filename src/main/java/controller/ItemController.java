package controller;

import domain.Item;
import services.ItemMeliService;

public class ItemController {

    ItemMeliService iItemMeliService;

    public ItemController() {
        this.iItemMeliService = new ItemMeliService();
    }

    public Item[] getAll(String query){

        return iItemMeliService.getAllItem(query);
    }

}
