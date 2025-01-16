package com.ecommerce.project.Service;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryServiceImpl  implements  CategoryService {



    Long categoryId=1L;

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    @Transactional
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional
    public void CreateCategory(Category category) {
       // category.setCategoryId(categories.size()+1);
//category.setCategoryId(categoryId++);
  //      categories.add(category);

        System.out.println("  is it ok");
             categoryRepository.save(category);

    }

    @Override
    @Transactional
    public String deleteCategory(Long categoryId) {

          /* Category category=categories.stream()
                   .filter(c -> c.getCategoryId().equals(categoryId))*/


        Category category=categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource not found"));

       /* List<Category> categories=categoryRepository.findAll();

        Category category = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst()
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource Not Found"));

        if (category==null){
            return   "Category  with category id Not Found";
        }*/

        categoryRepository.delete( category);

       // categories.remove(category);


        return "Category with category "+  categoryId+ "  id deleted succesfully";
    }

    @Override
    @Transactional
    public Category updateCategory(Category category, Long categoryId) {

     Optional<Category>savedcategoryOptional =categoryRepository.findById(categoryId);


     Category savedcategory=savedcategoryOptional
             .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource not found"));


         category.setCategoryId(categoryId);

         savedcategory=categoryRepository.save(category);
         return savedcategory;


      /*  Optional<Category> optionalCategory = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst();


        if (optionalCategory.isPresent()){
            Category  exisCategory=optionalCategory.get();
            exisCategory.setCategoryName(category.getCategoryName());

            Category savedCategory =categoryRepository.save(exisCategory);
            return exisCategory;
        }else {

            throw  new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource Not Found");

        }



*/
    }

}
