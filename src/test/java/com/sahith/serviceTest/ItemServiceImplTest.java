package com.sahith.serviceTest;

import com.sahith.dao.ItemRepository;
import com.sahith.entity.Category;
import com.sahith.entity.Item;
import com.sahith.services.implementations.ItemServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ItemServiceImplTest
{
    @InjectMocks
    private ItemServiceImpl itemService;
    @Mock
    private ItemRepository itemRepository;

    @Test
    void returnAllItemsIfExistsTest()
    {
        List<Item> itemsList =  new ArrayList<>();
        Category category = new Category(1 , "Dog");
        itemsList.add(new Item(22 , "lucy" , "pug" , 1 , 5 , "brown" , 1 ,  25000 , "xyz" , category ));
        when(itemRepository.findAll()).thenReturn(itemsList);
        assertEquals(itemsList, itemService.findAll());
    }

    @Test
    void returnNullIfItemDoesNotExists()
    {
        List<Item> itemList = null;
        when(itemRepository.findAll()).thenReturn(itemList);
        assertEquals(itemList,itemService.findAll());
    }

    @Test
    void returnItemByIdIfItExist()
    {
        Category category = new Category(1 , "dog");
        Item item = new Item(22 , "lucy" , "pug" , 1 , 5 , "brown" , 1 ,  25000 , "xyz" , category);
        when(itemRepository.findById(22)).thenReturn(Optional.of(item));
        assertEquals(item , itemService.findItemById(22));
    }

    @Test
    void throwExceptionWhenGivenNonExistingIdTest() throws RuntimeException{
        Optional<Item> item = Optional.empty();
        when(itemRepository.findById(2)).thenReturn(item);
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            itemService.findItemById(2);
        });
        assertEquals("Did not find item id - " + 2, thrown.getMessage());
    }

    @Test
    void saveGivenCategoryTest() {
        Category category = new Category(1, "Dog");
        Item item = new Item(22 , "lucy" , "pug" , 1 , 5 , "brown" , 1 ,  25000 , "xyz" , category);
        itemService.save(item);
        verify(itemRepository,times(1)).save(item);
    }

    @Test
    void deleteGivenCategoryTest() {
        itemService.deleteItem(22);
        verify(itemRepository,times(1)).deleteById(22);
    }
}
