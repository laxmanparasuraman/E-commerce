package com.ecommerce.project.Service;

import com.ecommerce.project.model.Category;
import org.springframework.stereotype.Component;

import java.util.List;





public interface CategoryService {

    List<Category>getAllCategories();
    void  CreateCategory(Category category);


    String deleteCategory(Long categoryId);

    Category updateCategory(Category category, Long categoryId);
}
