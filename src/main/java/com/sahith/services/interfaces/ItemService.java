package com.sahith.services.interfaces;




import com.sahith.entity.Item;

import java.util.List;

public interface ItemService {

    public List<Item> findAll();

    public Item findItemById(int id);

    public void deleteItem(int id);

    public void save(Item item);
}
