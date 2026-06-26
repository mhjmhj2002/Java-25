package com.mhj.crud.product;

import com.mhj.crud.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public ProductService(ProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ProductResponse create(ProductRequest request) {
        Product product = mapper.toEntity(request);
        Product savedProduct = repository.save(product);

        return mapper.toResponse(savedProduct);
    }

    public Page<ProductResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toResponse);
    }

    public ProductResponse findById(Long id) {
        Product product = findEntityById(id);
        return mapper.toResponse(product);
    }

    public ProductResponse update(Long id, ProductRequest request) {
        Product product = findEntityById(id);

        mapper.updateEntityFromRequest(request, product);

        Product updatedProduct = repository.save(product);

        return mapper.toResponse(updatedProduct);
    }

    public void delete(Long id) {
        Product product = findEntityById(id);
        repository.delete(product);
    }

    private Product findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado: " + id));
    }
}