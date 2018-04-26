package com.emusic.service;

import java.util.List;

import com.emusic.model.Product;

public interface ProductService {
	List<Product> getProductList(long[] total);
	public List<Product> getProductList(int page, long[] total);
	public List<Product> getProductList(int page, String field, boolean isAsc, long[] total);
	
	Long addProduct(Product product);
	
	void deleteProduct(Long id);
	
	Product getProductById(Long id);
	
	void editProduct(Product product);
	
	List<Product> queryProduct(String query, int page, long[] total);
	
	List<Product> queryProduct(String query, int page, String orderBy, boolean isAsc, long[] total);
}
