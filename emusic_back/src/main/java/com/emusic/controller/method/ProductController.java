package com.emusic.controller.method;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.emusic.controller.dto.ProductListDto;
import com.emusic.controller.util.RestPreconditions;
import com.emusic.model.Product;
import com.emusic.service.ProductService;

@RequestMapping("/product/api")
@Controller
@EnableWebMvc
public class ProductController {
	
	@Autowired
	private ProductService service;
	
 
	@RequestMapping(value="/browse", method = RequestMethod.GET)
	@ResponseBody 
	public ProductListDto getProduct() {
		long[] total = new long[] {0};
		List<Product> productList = service.getProductList(total);
		ProductListDto productListDto = new ProductListDto(total[0], productList);
		return productListDto;
	}
	
	@RequestMapping(value="/browse/page/{page}", method = RequestMethod.GET)
	@ResponseBody 
	public ProductListDto getProduct(@PathVariable int page) {//becasue setRowNumber in hibernate is int...
		long[] total = new long[] {0};
		List<Product> productList = service.getProductList(page, total);
		ProductListDto productListDto = new ProductListDto(total[0], productList);
		return productListDto;
	}
	
	@RequestMapping(value="/browse/orderby/{field}/asc/{asc}/page/{page}", method = RequestMethod.GET)
	@ResponseBody 
	public ProductListDto getProduct(@PathVariable String field, @PathVariable boolean asc, @PathVariable int page) {
		long[] total = new long[] {0};
		List<Product> productList = service.getProductList(page, field, asc, total);
		ProductListDto productListDto = new ProductListDto(total[0], productList);
		return productListDto;
	}
	

	
	@RequestMapping(value="/browse/query/{query}/page/{page}", method = RequestMethod.GET)
	@ResponseBody 
	public ProductListDto getProduct(@PathVariable String query, @PathVariable int page) {
		long[] total = new long[] {0};
		List<Product> productList = service.queryProduct(query, page, total);
		ProductListDto productListDto = new ProductListDto(total[0], productList);
		return productListDto;
	}
	
	@RequestMapping(value="/browse/query/{query}/orderby/{field}/asc/{asc}/page/{page}", method = RequestMethod.GET)
	@ResponseBody 
	public ProductListDto getProduct(@PathVariable String query, @PathVariable String field, @PathVariable boolean asc, @PathVariable int page) {
		long[] total = new long[] {0};
		List<Product> productList = service.queryProduct(query, page, field, asc, total);
		ProductListDto productListDto = new ProductListDto(total[0], productList);
		return productListDto;
	}
 
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Product getProduct(@PathVariable Long id) throws IOException {
		return RestPreconditions.checkFound(service.getProductById(id));
	}
}
