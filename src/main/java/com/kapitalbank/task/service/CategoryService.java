package com.kapitalbank.task.service;

import com.kapitalbank.task.entity.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {

    ResponseEntity<List<Category>> getAllCategories();

    ResponseEntity<Category> getCategoryById(int id);

    ResponseEntity<Category> createCategory(Category category);

    ResponseEntity<Category> updateCategory(int id, Category category);

    ResponseEntity<HttpStatus> deleteCategory(int id);
}
