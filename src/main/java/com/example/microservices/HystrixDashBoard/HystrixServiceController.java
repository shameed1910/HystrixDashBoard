package com.example.microservices.HystrixDashBoard;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class HystrixServiceController {

	@Autowired
	private ProductServiceProxy proxy;

	List<Cart> mycart = new ArrayList<Cart>();

	@GetMapping("/getMycart")
	public String getMycart() {
		return "Below are the your product details";
	}

	@HystrixCommand(fallbackMethod = "addMycart_Fallback")
	@GetMapping("/addMycart/{productId}")
	public List<Cart> addMycart(@PathVariable("productId") String productId) {
		List<Product> l = proxy.showProductDetails();
		Cart cart = new Cart();
		


	l.forEach(product -> {
		if (productId.equals(product.getProductId())) {
			System.out.println("productId"+product.getProductId());
			cart.setDescription(product.getDescription());
			cart.setId(product.getId());
			cart.setPrice(product.getPrice());
			cart.setProductId(product.getProductId());
			cart.setPort(product.getPortNumber());
			cart.setQuantity(1);
			mycart.add(cart);
			System.out.println(product.getDescription());
		}
		
	});

		return mycart;

	}

	public List<Cart> addMycart_Fallback(String productId) {
		System.out
				.println("Product Service is down!!! fallback route enabled...");
		List<Cart> mycart = new ArrayList<Cart>();
		Cart cart = new Cart(1, "12", "APPle", 48000,1,"port");
		mycart.add(cart);
		return mycart;

		// return
		// "CIRCUIT BREAKER ENABLED!!! No Response From Product Service at this moment. "
		// +
		// " Service will be back shortly - " + new Date();
	}
}
