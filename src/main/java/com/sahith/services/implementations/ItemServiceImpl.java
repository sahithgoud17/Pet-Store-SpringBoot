package com.sahith.services.implementations;

import com.sahith.dao.ItemRepository;
import com.sahith.entity.Item;
import com.sahith.services.interfaces.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public Item findItemById(int id) {
       Optional<Item> result = itemRepository.findById(id);
       Item item = null;
       if(result.isPresent()) {
           item = result.get();
       }
       else {
           throw new RuntimeException("Did not find item id - " + id);
       }
       return item;
    }

    @Override
    public void deleteItem(int id) {
        itemRepository.deleteById(id);
    }

    @Override
    public void save(Item item) {
        itemRepository.save(item);
    }
}