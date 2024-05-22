package com.ecart.controller;

import com.ecart.dto.ProductDTO;
import com.ecart.service.impl.ProductServiceImpl;
import org.hibernate.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductDetails(@PathVariable Long id){
        ProductDTO productDTO = productService.getProductDetails(id);
        if(productDTO != null){
            return ResponseEntity.ok(productDTO);
        } else {
            return ResponseEntity.status(204).body(null);
        }

    }

    @PostMapping("/add")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO){
        return ResponseEntity.ok(productService.addProduct(productDTO));
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProductDTO>> search(@PathVariable String name){
        return ResponseEntity.ok(productService.search(name));
    }

}
