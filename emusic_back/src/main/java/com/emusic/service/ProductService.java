package com.emusic.service;

import java.util.List;

import com.emusic.model.Product;

public interface ProductService {
	List<Product> getProductList();
	
	String addProduct(Product product);
	
	void deleteProduct(String id);
	
	Product getProductById(String id);
	
	void editProduct(Product product);
}
