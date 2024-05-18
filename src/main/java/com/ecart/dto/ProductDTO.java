package com.ecart.dto;

import com.ecart.entity.Category;
import com.ecart.entity.Rating;
import lombok.Data;

@Data
public class ProductDTO {
    private String title;
    private Double price;
    private String description;
    private CategoryDTO category;
    private String image;
    private RatingDTO rating;

}
