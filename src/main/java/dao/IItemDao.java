package dao;

import domain.Item;

public interface IItemDao {

    public Item[] getItemsMap(String query);

    public void saveItemsMap(String query, Item[] items);

}
