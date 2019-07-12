package services;

import domain.Item;

public interface IExternalApiItem {

    public Item[] getAllItem(String query);
}
