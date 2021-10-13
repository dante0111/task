package com.kapitalbank.task.service.impl;

import com.kapitalbank.task.entity.Category;
import com.kapitalbank.task.repository.CategoryRepository;
import com.kapitalbank.task.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ResponseEntity<List<Category>> getAllCategories() {
        try {
            List<Category> categories = categoryRepository.findAll();

            return new ResponseEntity<>(categories, HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Category> getCategoryById(int id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent())
        {
            return new ResponseEntity<>(category.get(), HttpStatus.OK);
        }else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Category> createCategory(Category category) {
        try {
            Category category1 = categoryRepository
                    .save(new Category(category.getName(), category.getProducts()));
            return new ResponseEntity<>(category1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Category> updateCategory(int id, Category category) {
        Optional<Category> category1 = categoryRepository.findById(id);

        if (category1.isPresent()) {
            Category category2 = category1.get();
            category2.setName(category.getName());
            category2.setProducts(category.getProducts());
            return new ResponseEntity<>(categoryRepository.save(category2), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> deleteCategory(int id) {
        try {
            categoryRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
