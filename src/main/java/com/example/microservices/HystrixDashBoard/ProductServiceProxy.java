package com.example.microservices.HystrixDashBoard;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("ProductService")
public interface ProductServiceProxy {
	@GetMapping("/products")
	public List<Product> showProductDetails();
}
