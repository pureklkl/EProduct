package com.emusic.controller.method;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emusic.controller.util.RestPreconditions;
import com.emusic.model.CustomerOrder;
import com.emusic.model.OrderStatus;
import com.emusic.service.CustomerOrderService;
import com.emusic.service.OrderStatusService;

@RequestMapping("/order")
@Controller
public class OrderController {

    @Autowired
    private CustomerOrderService customerOrderService;
    

    
    @RequestMapping("/cartid/{cartId}")
    public String createOrder(@PathVariable("cartId") Long cartId) {

        return "redirect:/checkout?cartId="+cartId;
    }
    
    @RequestMapping(value = "/api/user", method = RequestMethod.GET)
    @ResponseBody
    public List<CustomerOrder> getOrderListByUser(@AuthenticationPrincipal User user) {
        return customerOrderService.getOrdersByUserEmail(user.getUsername());
    }
    
    @RequestMapping(value = "/api/orderid/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CustomerOrder getOrderById(@PathVariable Long id) {
    	return RestPreconditions.checkFound(customerOrderService.getOrderById(id));
    }
    


 
    
}
