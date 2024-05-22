package com.ecart.service.impl;

import com.ecart.dto.ProductDTO;
import com.ecart.entity.Category;
import com.ecart.entity.Product;
import com.ecart.entity.Rating;
import com.ecart.repository.CategoryRepository;
import com.ecart.repository.ProductRepository;
import com.ecart.repository.RatingRepository;
import com.ecart.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final RatingRepository ratingRepository;
    private final ModelMapper mapper;
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, RatingRepository ratingRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.ratingRepository = ratingRepository;
        this.mapper = new ModelMapper();
    }

    public List<ProductDTO> getAllProducts(){
        List<ProductDTO> productDTOS = new ArrayList<>();
        productRepository.findAll().forEach(product -> {
            productDTOS.add(mapper.map(product, ProductDTO.class));
        });
        return productDTOS;
    }

    public ProductDTO getProductDetails(Long id){
        return mapper.map(productRepository.findById(id), ProductDTO.class);
    }

    public ProductDTO addProduct(ProductDTO productDTO){
        Product product = new Product();
        Category category = categoryRepository.findByName(productDTO.getCategory().getName());
        Rating rating = new Rating();
        rating.setCount(productDTO.getRating().getCount());
        rating.setRate(productDTO.getRating().getRate());
        Rating newRating = ratingRepository.save(rating);

        product.setTitle(productDTO.getTitle());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);
        product.setImage(productDTO.getImage());
        product.setRating(newRating);
        productRepository.save(product);
        return productDTO;
    }

    public List<ProductDTO> findBySubTitle(String subtitle){
        List<Product> products = productRepository.findBySubTitle(subtitle).stream().toList();
        List<ProductDTO> productDTOS = new ArrayList<>();
        for(Product product: products){
            productDTOS.add(mapper.map(product, ProductDTO.class));
        }
        return productDTOS;
    }
}
