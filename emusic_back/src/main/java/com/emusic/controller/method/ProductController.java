package com.emusic.controller.method;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.emusic.controller.util.RestPreconditions;
import com.emusic.dao.ProductDao;
import com.emusic.model.Product;

@RequestMapping("/product/api")
@Controller
@EnableWebMvc
public class ProductController {
	
	@Autowired
	private ProductDao dao;
	
	@CrossOrigin
	@RequestMapping(value="/browse", method = RequestMethod.GET)
	@ResponseBody 
	public List<Product> getProduct() {
		return dao.getProductList();
	}
	
	@CrossOrigin
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Product getProduct(@PathVariable String id) throws IOException {
		return RestPreconditions.checkFound(dao.getProductById(id));
	}
}
