package com.ecommerce.service;

import org.springframework.stereotype.Service;

import com.ecommerce.entity.Category;
import com.ecommerce.entity.Product;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommmerce.dto.ProductDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;
	
	private final CategoryRepository categoryRepository;
	
	public Product createProduct(ProductDto dto) {
		
		Category category =  categoryRepository.findById(dto.getCategoryid())
				.orElseThrow(()->new RuntimeException("Category not found"));
		
		Product product = new Product();
		product.setName(dto.getName());
		product.setDescription(dto.getDescription());
		product.setPrice(dto.getPrice());
		product.setStock(dto.getStock());
		return productRepository.save(product);
		
	}
	
//	public Product getByAllproduct(ProductDto dto) {
//		return productRepository.findAll(Product);
//		
//	}
}
