package com.ecart.service.impl;

import com.ecart.entity.Category;
import com.ecart.repository.CategoryRepository;
import com.ecart.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll().stream().toList();
    }

    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }
}
