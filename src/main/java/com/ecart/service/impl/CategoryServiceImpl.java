package com.ecart.service.impl;

import com.ecart.dto.CategoryDTO;
import com.ecart.entity.Category;
import com.ecart.repository.CategoryRepository;
import com.ecart.service.CategoryService;
import jakarta.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.mapper = new ModelMapper();
    }

    public List<CategoryDTO> getAllCategories(){
        List<Category> categories = categoryRepository.findAll().stream().toList();
        return categories.stream().map(category -> mapper.map(category, CategoryDTO.class)).toList();
    }

    public CategoryDTO addCategory(CategoryDTO categoryDTO){
        categoryRepository.save(mapper.map(categoryDTO, Category.class));
        return categoryDTO;
    }

    public String updateCategory(@NotNull Long id, @NotNull CategoryDTO categoryDTO){
        Category category = categoryRepository.findById(id).orElseThrow(()-> new RuntimeException("Category Not Found"));
        if (categoryDTO.getName() != null){
            category.setName(categoryDTO.getName());
            categoryRepository.save(category);
        }
        return "Updated Successfully";
    }

//    @Scheduled(fixedRate = 1000 * 60 * 30)
//    public void scheduled(){
//        System.out.println("Scheduled Action" + new Date(System.currentTimeMillis()));
//    }


}
