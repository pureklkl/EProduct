package com.emusic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emusic.dao.ProductDao;
import com.emusic.model.Product;
import com.emusic.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao dao;
	
	final private int NUM_PER_PAGE = 20;
	
	@Override
	public List<Product> getProductList(long[] total) {
		return dao.getProductList(0, NUM_PER_PAGE, total);
	}
	@Override
	public List<Product> getProductList(int page, long[] total) {
		return dao.getProductList(page, NUM_PER_PAGE, total);
	}
	
	@Override
	public List<Product> getProductList(int page, String field, boolean isAsc, long[] total) {
		return dao.getProductList(page, NUM_PER_PAGE, field, isAsc, total);
	}
	
	public List<Product> queryProduct(String query, int page, long[] total) {
		return dao.queryProduct(query, page, NUM_PER_PAGE, total);
	}
	
	public List<Product> queryProduct(String query, int page, String orderBy, boolean isAsc, long[] total) {
		return dao.queryProduct(query, page, NUM_PER_PAGE, orderBy, isAsc, total);
	}
	
	@Override
	public Long addProduct(Product product) {
		return dao.addProduct(product);
	}

	@Override
	public void deleteProduct(Long id) {
		dao.deleteProduct(id);
	}

	@Override
	public Product getProductById(Long id) {
		return dao.getProductById(id);
	}

	@Override
	public void editProduct(Product product) {
		dao.editProduct(product);
	}

}
