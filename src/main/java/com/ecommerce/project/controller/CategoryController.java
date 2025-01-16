package com.ecommerce.project.controller;


import com.ecommerce.project.Service.CategoryService;
import com.ecommerce.project.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    private CategoryService categoryService;

    @GetMapping("/public/categories")
  //  @RequestMapping(value = "/public/categories",method = RequestMethod.GET)
    public ResponseEntity<List<Category>>getAllcategories(){
      List<Category>categories=  categoryService.getAllCategories();
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }






    //@RequestMapping(value = "/public/categories",method = RequestMethod.POST)

    @PostMapping ("/public/categories")
    public  String createCategory(@RequestBody Category category){
        categoryService.CreateCategory(category);
        String status="categories Added Sucessfully ";


        return status;
        // return  new ResponseEntity<>(status, HttpStatus.OK);
        // return "categories Added Sucessfully ";
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public  ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){



        try {


            String status=categoryService.deleteCategory(categoryId);
            return  new ResponseEntity<>(status, HttpStatus.OK);
        }catch (ResponseStatusException e){


            return new ResponseEntity<>(e.getReason(),e.getStatusCode());
            //return e.getMessage();
        }



    }



    @PutMapping ("/public/categories/{categoryId}")
    public  ResponseEntity<String >updateCategory(@RequestBody Category category,
                                                  @PathVariable Long categoryId){


            try {
                     Category savedCategory =categoryService.updateCategory(category,categoryId);


                     return  new ResponseEntity<>("Category with category id " +category.getCategoryId() +"  "+category.getCategoryName(),HttpStatus.OK);

            }catch (ResponseStatusException e){

                  return  new ResponseEntity<>(e.getReason(),e.getStatusCode());
            }


    }



}
