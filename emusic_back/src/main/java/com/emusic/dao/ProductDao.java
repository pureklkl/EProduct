package com.emusic.dao;

import java.util.List;

import javax.persistence.criteria.Order;

import com.emusic.model.Product;


public interface ProductDao extends Dao {
	
	Long addProduct(Product product);
	
	void deleteProduct(Long id);
	
	Product getProductById(Long id);
	
	void editProduct(Product product);

	List<Product> getProductList(int page, int max, long[] total);

	List<Product> getProductList(int page, int max, String orderBy, boolean isAsc, long[] total);
	
	List<Product> queryProduct(String query, int page, int max, long[] total);
	
	List<Product> queryProduct(String query, int page, int max, String orderBy, boolean isAsc, long[] total);
}
