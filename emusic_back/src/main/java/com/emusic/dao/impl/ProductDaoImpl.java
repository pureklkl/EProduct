package com.emusic.dao.impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.lucene.search.SortField;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.search.query.dsl.sort.SortFieldContext;
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
				img.transferTo(new File(getProductImagePath(product.getId().toString())));
			} catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Product image upload failed", e);
			}
		}
	}
	
	@Override
	public List<Product> getProductList(int page, int max, long[] total) {
		return getProductList(page, max, null, false, total);
	}
	
	@Override
	public List<Product> getProductList(int page, int max, String orderBy, boolean isAsc, long[] total) {
		Session session = getSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Product> query = builder.createQuery(Product.class);
		Root<Product> root = query.from(Product.class);
		if(orderBy != null) {
			if(isAsc) {
				query.select(root).orderBy(builder.asc(root.get(orderBy)));
			} else {
				query.select(root).orderBy(builder.desc(root.get(orderBy)));
			}
		}
		return getPagination(page, max, Product.class, query, total);
	}
	
	public List<Product> getProductList(int first, int max) {
		Session session = getSession();
		// Use Type Query to avoid warning!
		Query<Product> query = session.createQuery("from Product", Product.class);
		List<Product> products = query.list();
		return products;
	}
	
	Map<String, SortField.Type> PRODUCT_SORT_FIELD_TYPE;
	
	@PostConstruct
	public void init() {
		File imgDir = new File(productImageFolder);
		imgDir.mkdirs();//create path if not exist
		PRODUCT_SORT_FIELD_TYPE = new HashMap<>();
		PRODUCT_SORT_FIELD_TYPE.put("year", SortField.Type.INT);
		PRODUCT_SORT_FIELD_TYPE.put("dirctor", SortField.Type.STRING);
		PRODUCT_SORT_FIELD_TYPE.put("title", SortField.Type.STRING);
		PRODUCT_SORT_FIELD_TYPE.put("price", SortField.Type.INT);
	}
	
	@Override
	public List<Product> queryProduct(String query, int page, int max, long[] total) {
		return queryProduct(query, page, max, null, false, total);
	}
	
	@Override
	public List<Product> queryProduct(String query, int page, int max, String orderBy, boolean isAsc, long[] total) {
		FullTextSession fullTextSession = Search.getFullTextSession(getSession());
		QueryBuilder qb = fullTextSession.getSearchFactory()
			    .buildQueryBuilder().forEntity(Product.class).get();
		
		org.apache.lucene.search.Query luceneQuery = qb
				  .keyword()
				  .fuzzy()
				  .onFields("title", "year", "dirctor")
				  .matching(query)
				  .createQuery();
		
		FullTextQuery ftQuery = fullTextSession.createFullTextQuery(luceneQuery, Product.class);
			
		if(orderBy != null && PRODUCT_SORT_FIELD_TYPE.containsKey(orderBy)) {
			//Sort-able fields are not analyzed, these fields are prefixed with "sort"
			SortFieldContext sortContext = qb.sort().byField("sort" + orderBy);
			if(isAsc) {
				sortContext.asc();
			} else {
				sortContext.desc();
			}
			ftQuery.setSort(sortContext.createSort());
		}
		
		//List<Product> result = ftQuery.list();
		//total[0] = result.size();
		
		ScrollableResults  scrollableResults = ftQuery.scroll();
		
		List<Product> result = getPagination(page, max, Product.class, scrollableResults, total);
		
		scrollableResults.close();//
		
		return result;
	}
	
	
	
	
	@Override
	public Long addProduct(Product product) {
		Session session = getSession();
		session.saveOrUpdate(product);
		session.flush();
		saveImage(product);
		return product.getId();
	}

	@Override
	public void deleteProduct(Long id) {
		Session session = getSession();
		Product product = getProductById(id);
		product.setCartItemList(null);
		product.setOrderItemList(null);
		session.delete(product);
		session.flush();
		try {
			Files.deleteIfExists(Paths.get(getProductImagePath(id.toString())));
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Delete product image failed", e);
		}
	}

	@Override
	public Product getProductById(Long id) {
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

	
}
