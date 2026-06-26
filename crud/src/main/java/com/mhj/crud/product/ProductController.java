package com.mhj.crud.product;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	private final ProductService service;

	public ProductController(ProductService service) {
		this.service = service;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProductResponse create(@RequestBody @Valid ProductRequest request) {
		return service.create(request);
	}

	@GetMapping
	public Page<ProductResponse> findAll(ProductFilter filter, Pageable pageable) {
		return service.findAll(filter, pageable);
	}

	@GetMapping("/{id}")
	public ProductResponse findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@PutMapping("/{id}")
	public ProductResponse update(@PathVariable Long id, @RequestBody @Valid ProductRequest request) {
		return service.update(id, request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
}