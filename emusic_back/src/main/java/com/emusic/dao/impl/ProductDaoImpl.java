package com.emusic.dao.impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import javax.persistence.criteria.Order;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.emusic.dao.ProductDao;
import com.emusic.model.Product;

@Repository
@Transactional
public class ProductDaoImpl extends DaoImpl implements ProductDao {

	@Value("${product.productImageLocation}")
	private String productImageFolder; 	
	
	private String getProductImagePath(String id) {
		return productImageFolder + id + ".png";
	}
	
	private void saveImage(Product product) {
		MultipartFile img = product.getImage();
		if (img != null && !img.isEmpty()) {
			System.out.println("image");
			try {
				img.transferTo(new File(getProductImagePath(product.getId())));
			} catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Product image upload failed", e);
			}
		}
	}
	
	@Override
	public List<Product> getProductList() {
		Session session = getSession();
		// Use Type Query to avoid warning!
		Query<Product> query = session.createQuery("from Product", Product.class);
		List<Product> products = query.list();
		return products;
	}

	@Override
	public String addProduct(Product product) {
		Session session = getSession();
		session.saveOrUpdate(product);
		session.flush();
		saveImage(product);
		return product.getId();
	}

	@Override
	public void deleteProduct(String id) {
		Session session = getSession();
		session.delete(getProductById(id));
		session.flush();
		try {
			Files.deleteIfExists(Paths.get(getProductImagePath(id)));
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Delete product image failed", e);
		}
	}

	@Override
	public Product getProductById(String id) {
		Session session = getSession();
		Product product = (Product) session.get(Product.class, id);
		return product;
	}
	
	@Override
	public void editProduct(Product product) {
		Session session = getSession();
		session.saveOrUpdate(product);
		session.flush();
		saveImage(product);
	}

	@Override
	public List<Product> getProductList(int start, int max, List<Order> order, Product restriction, int[] total) {
		HashMap<String, Object> restrictionMap = new HashMap<>();
		if(restriction.getCategory() != null) {
			restrictionMap.put("category", restriction.getCategory());
		}
		if(restriction.getCondition_() != null) {
			restrictionMap.put("condition_", restriction.getCondition_());
		}
		if(restriction.getManufactory() != null) {
			restrictionMap.put("manufactory", restriction.getManufactory());
		}
		if(restriction.getStatus() != null) {
			restrictionMap.put("status", restriction.getStatus());
		}
		return getPagination(start, max, Product.class, order, restrictionMap, total);
	}
}
