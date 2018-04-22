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
	
	@Override
	public List<Product> getProductList() {
		return dao.getProductList();
	}

	@Override
	public String addProduct(Product product) {
		return dao.addProduct(product);
	}

	@Override
	public void deleteProduct(String id) {
		dao.deleteProduct(id);
	}

	@Override
	public Product getProductById(String id) {
		return dao.getProductById(id);
	}

	@Override
	public void editProduct(Product product) {
		dao.editProduct(product);
	}

}
