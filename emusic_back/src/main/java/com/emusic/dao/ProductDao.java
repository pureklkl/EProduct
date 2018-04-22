package com.emusic.dao;

import java.util.List;

import javax.persistence.criteria.Order;

import com.emusic.model.Product;


public interface ProductDao {
	
	List<Product> getProductList();
	
	List<Product> getProductList(int start, int max, List<Order> order, Product restriction, int[] total);
	
	String addProduct(Product product);
	
	void deleteProduct(String id);
	
	Product getProductById(String id);
	
	void editProduct(Product product);
}
