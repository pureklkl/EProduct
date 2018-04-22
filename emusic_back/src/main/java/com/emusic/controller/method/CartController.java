package com.emusic.controller.method;

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

import com.emusic.controller.dto.BuyItemDto;
import com.emusic.controller.dto.Response;
import com.emusic.controller.util.RestPreconditions;
import com.emusic.model.Cart;
import com.emusic.service.CartService;
import com.emusic.service.CustomerService;

@RequestMapping("/cart")
@Controller
public class CartController {
	
	@Autowired
	CartService cartService;
	
	@Autowired
	CustomerService customerService;
	
	@RequestMapping(value = "/api", method = RequestMethod.GET)
	@ResponseBody	
	public Cart getCartByUser(@AuthenticationPrincipal User user) {
		return RestPreconditions.checkFound(customerService.getCustomerByEmail(user.getUsername()).getCart());
	}
	
	@RequestMapping(value = "/api/{cartId}", method = RequestMethod.GET)
	@ResponseBody	
	public Cart getCartById(@PathVariable Long cartId) {
		return RestPreconditions.checkFound(cartService.getCartById(cartId));
	}
	
	@RequestMapping(value = "/api", method = RequestMethod.POST)
	@ResponseBody
	public Response addCartItem(@Valid BuyItemDto cartItemDto, @AuthenticationPrincipal User user) {
		Cart cart = getCartByUser(user);
		Long id = cartService.addItem(cart, cartItemDto.getProductId(), cartItemDto.getQuantity().intValue()); 
		return new Response(id.toString());
	}
	
	@RequestMapping(value = "/api/cartitem/{itemId}/quantity/{quantity}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void updateCartItem(@PathVariable Long itemId, @PathVariable int quantity) {
		cartService.putItem(itemId, quantity); 
	}
	
	@RequestMapping(value = "/api/cartitem/{itemId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteCartItem(@PathVariable Long itemId) {
		cartService.removeItem(itemId); 
	}
	
}
