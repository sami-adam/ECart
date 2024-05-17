package com.ecart.controller;

import com.ecart.dto.ProductDemoData;
import com.ecart.entity.Category;
import com.ecart.entity.Product;
import com.ecart.entity.Rating;
import com.ecart.repository.CategoryRepository;
import com.ecart.repository.ProductRepository;
import com.ecart.repository.RatingRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class DataFetch {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final RatingRepository ratingRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(DataFetch.class);
    public DataFetch(CategoryRepository categoryRepository, ProductRepository  productRepository, RatingRepository ratingRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.ratingRepository = ratingRepository;
    }

    @GetMapping("/fetch")
    public ResponseEntity<String> fetchData() throws JsonProcessingException {
        String url = "https://fakestoreapi.com/products";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        ObjectMapper mapper = new ObjectMapper();
        List<ProductDemoData> productDemoData = mapper.readValue(response, new TypeReference<List<ProductDemoData>>() {});
        productDemoData.forEach(product -> {
            String categoryName = product.getCategory();
            Category category = categoryRepository.findByName(categoryName);
            if(category != null){
                LOGGER.info("Found Skipping" + categoryName);
            } else {
                LOGGER.info("Not Found Creating" + categoryName);
                Category newCategory = new Category();
                newCategory.setName(categoryName);
                categoryRepository.save(newCategory);
            }
        });
        productDemoData.forEach(demoProduct -> {
            Category category = categoryRepository.findByName(demoProduct.getCategory());
            String title = demoProduct.getTitle();
            Product product = productRepository.findByTitle(title);
            if(product != null){
                LOGGER.info("Skipping Product Found");
            } else {
                LOGGER.info("Not found Creating");
                Rating rating = new Rating();
                rating.setRate(demoProduct.getRating().get("rate"));
                rating.setCount(demoProduct.getRating().get("count").intValue());
                Rating newRating = ratingRepository.save(rating);
                Product newProduct = new Product();
                newProduct.setTitle(title);
                newProduct.setDescription(demoProduct.getDescription());
                newProduct.setPrice(demoProduct.getPrice());
                newProduct.setImage(demoProduct.getImage());
                newProduct.setCategory(category);
                newProduct.setRating(newRating);
                productRepository.save(newProduct);
            }
        });
        return ResponseEntity.ok("Fetched");
    }
}
