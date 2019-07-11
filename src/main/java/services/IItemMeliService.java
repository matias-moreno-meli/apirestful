package services;

import domain.Item;

import java.util.List;

public interface IItemMeliService {

    public List<Item> getAllItem(String query);
}
