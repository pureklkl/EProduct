package com.emusic.controller.method;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.emusic.controller.dto.Response;
import com.emusic.controller.util.RestPreconditions;
import com.emusic.model.CustomerOrder;
import com.emusic.model.OrderStatus;
import com.emusic.model.Product;
import com.emusic.service.CustomerOrderService;
import com.emusic.service.OrderStatusService;
import com.emusic.service.ProductService;

@RequestMapping("/admin")
@Controller
public class AdminController {
	
	@Autowired
	ProductService productService;
	
    @Autowired
    private CustomerOrderService customerOrderService;	
    
    @Autowired
    private OrderStatusService ordserStatusService;
	
	@RequestMapping({"", "/", "/adminBrowse", "/addProduct", "/editProduct/{id}"})
	public String admin() {
		System.out.println("admin");
		return "admin.html";
	}
	
	@RequestMapping(value="/api/product/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
	}
	
	@RequestMapping(value="/api/product", method = RequestMethod.POST, consumes="multipart/form-data")
	@ResponseBody
	public Response addProduct(@Valid Product product) {
		return  new Response(productService.addProduct(product).toString());
	}
	
	//for multi-part form we can only use post...
	@RequestMapping(value="/api/editproduct", method = RequestMethod.POST, consumes="multipart/form-data")
	@ResponseStatus(HttpStatus.OK)
	public void patchProduct(@Valid Product product) {
		productService.editProduct(product);
	}
	
    @RequestMapping(value = "/api/orderlist", method = RequestMethod.GET)
    @ResponseBody
    public List<CustomerOrder> getOrders(@AuthenticationPrincipal User user) {
        return customerOrderService.getOrders();
    }
    
    @RequestMapping(value = "/api/orderid/{id}/status/{status}", method = RequestMethod.PATCH)
    @ResponseBody
    public void updateStatus(@PathVariable Long id, @PathVariable String status) {
    	CustomerOrder order = RestPreconditions.checkFound(customerOrderService.getOrderById(id));
    	OrderStatus orderStatus = RestPreconditions.checkFound(ordserStatusService.findByName(status));
    	order.setStatus(orderStatus);
    	customerOrderService.updateOrder(order);
    }
	
}
