package com.emusic.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.emusic.model.Product;


public interface ProductDao {
	
	List<Product> getProductList();
	
	void addProduct(Product product);
	
	void deleteProduct(String id);
	
	Product getProductById(String id);
}
