package services;

import dao.IItemDao;
import dao.ItemDao;
import domain.Item;

import java.util.*;
import java.util.stream.Collectors;

public class ItemService {

    private IExternalApiItem apiMeliService;
    private IItemDao dao;

    private List<Item> items;

    public ItemService() {
        this.apiMeliService = new ApiMeliService();
        this.items = new ArrayList<>();
        this.dao = new ItemDao();
    }

    public List<String> getAllTitleItems(String query) {

        this.items = Arrays.asList(getAllItems(query));
        List<String> resuts = new ArrayList<>();

        for (Item i : items) {
            resuts.add(i.getTitle());
        }

        return resuts;
    }

    public List<Item> getItemsFilter(String query, String orderBy, String val, String tag, String priceRange) {
        this.items = Arrays.asList(getAllItems(query));

        if (priceRange != null) {
            filterPriceRange(priceRange);
        }

        if (tag != null) {
            filterTag(tag);
        }

        if (orderBy != null && val != null) {
            orderBy(orderBy, val);
        }

        return this.items;

    }

    private void filterPriceRange(String priceRange) {

        String[] parts = priceRange.split("-");
        long min = Long.parseLong(parts[0]);
        long max = Long.parseLong(parts[1]);

        List<Item> filteredList = this.items.stream()
                .filter(item -> (item.getPrice() >= min) && (item.getPrice() <= max))
                .collect(Collectors.toList());

        this.items = filteredList;

    }

    private void filterTag(String tag) {

        List<Item> filteredList = this.items.stream()
                .filter(item -> Arrays.asList(item.getTags()).stream()
                        .anyMatch(val ->
                                val.equals(tag)))
                .collect(Collectors.toList());

        this.items = filteredList;
    }

    private void orderBy(String atrr, String val) {
        switch (atrr.toUpperCase()) {
            case "PRICE":
                if (val.equalsIgnoreCase("ASC")) {
                    this.items.sort(Comparator.comparing(Item::getPrice));
                } else if (val.equalsIgnoreCase("DESC")) {
                    this.items.sort(Comparator.comparing(Item::getPrice).reversed());
                }
                break;
            case "LISTINGTYPE":
                if (val.equalsIgnoreCase("ASC")) {
                    this.items.sort(Comparator.comparing(Item::getListingTypeId));
                } else if (val.equalsIgnoreCase("DESC")) {
                    this.items.sort(Comparator.comparing(Item::getListingTypeId).reversed());
                }
                break;
            default:
                break;
        }
    }

    private Item[] getAllItems(String query) {

        Item[] items = dao.getItemsMap(query);

        if (items == null) {
            items = apiMeliService.getAllItem(query);
            dao.saveItemsMap(query, items);
        }
        return items;
    }

}
