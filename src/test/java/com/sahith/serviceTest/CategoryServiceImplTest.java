package com.sahith.serviceTest;

import com.sahith.dao.CategoryRepository;
import com.sahith.entity.Category;
import com.sahith.services.implementations.CategoryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CategoryServiceImplTest
{
    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    void returnAllCategoriesWhenHasCategoriesTest() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1,"Dog"));
        categories.add(new Category(2,"Cat"));
        when(categoryRepository.findAll()).thenReturn(categories);
        assertEquals(categories, categoryService.findAll());
    }

    @Test
    void returnNullWhenCategoryDoesNotExistTest()
    {
        List<Category> categories = null;
        when(categoryRepository.findAll()).thenReturn(categories);
        assertEquals(categories, categoryService.findAll());
    }

    @Test
    void returnCategoryWhenGivenIdExistsTest() {
        Category category = new Category(1,"Rabbit");
        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
        assertEquals(category, categoryService.findCategoryById(1));
    }

    @Test
    void throwExceptionWhenGivenIdDoesNotExistTest() throws RuntimeException{
        Optional<Category> category = Optional.empty();
        when(categoryRepository.findById(2)).thenReturn(category);
        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            categoryService.findCategoryById(2);
        });
        assertEquals("Did not find Category Id - " + 2, thrown.getMessage());
    }

    @Test
    void saveGivenCategoryTest() {
        Category category = new Category(2, "Cat");
        categoryService.save(category);
        verify(categoryRepository,times(1)).save(category);
    }

    @Test
    void deleteGivenCategoryTest() {
        categoryService.deleteCategory(2);
        verify(categoryRepository,times(1)).deleteById(2);
    }




}
