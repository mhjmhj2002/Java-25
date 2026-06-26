package com.mhj.crud.product;

import com.mhj.crud.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @Mock
    private ProductMapper mapper;

    @InjectMocks
    private ProductService service;

    @Test
    void shouldCreateProduct() {
        ProductRequest request = new ProductRequest(
                "Notebook",
                "Dell Latitude",
                BigDecimal.valueOf(3500)
        );

        Product product = new Product(
                request.name(),
                request.description(),
                request.price()
        );

        Product savedProduct = new Product(
                request.name(),
                request.description(),
                request.price()
        );

        ProductResponse response = new ProductResponse(
                1L,
                "Notebook",
                "Dell Latitude",
                BigDecimal.valueOf(3500),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        when(mapper.toEntity(request)).thenReturn(product);
        when(repository.save(product)).thenReturn(savedProduct);
        when(mapper.toResponse(savedProduct)).thenReturn(response);

        ProductResponse result = service.create(request);

        assertEquals(response, result);

        verify(mapper).toEntity(request);
        verify(repository).save(product);
        verify(mapper).toResponse(savedProduct);
    }

    @Test
    void shouldFindProductById() {
        Long id = 1L;

        Product product = new Product(
                "Notebook",
                "Dell Latitude",
                BigDecimal.valueOf(3500)
        );

        ProductResponse response = new ProductResponse(
                id,
                "Notebook",
                "Dell Latitude",
                BigDecimal.valueOf(3500),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        when(repository.findById(id)).thenReturn(Optional.of(product));
        when(mapper.toResponse(product)).thenReturn(response);

        ProductResponse result = service.findById(id);

        assertEquals(response, result);

        verify(repository).findById(id);
        verify(mapper).toResponse(product);
    }

    @Test
    void shouldThrowExceptionWhenProductNotFoundById() {
        Long id = 99L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> service.findById(id)
        );

        assertEquals("Produto não encontrado: 99", exception.getMessage());

        verify(repository).findById(id);
        verifyNoInteractions(mapper);
    }

    @Test
    void shouldUpdateProduct() {
        Long id = 1L;

        ProductRequest request = new ProductRequest(
                "Notebook Atualizado",
                "Dell Latitude Atualizado",
                BigDecimal.valueOf(4200)
        );

        Product product = new Product(
                "Notebook",
                "Dell Latitude",
                BigDecimal.valueOf(3500)
        );

        Product updatedProduct = product;

        ProductResponse response = new ProductResponse(
                id,
                "Notebook Atualizado",
                "Dell Latitude Atualizado",
                BigDecimal.valueOf(4200),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        when(repository.findById(id)).thenReturn(Optional.of(product));
        when(repository.save(product)).thenReturn(updatedProduct);
        when(mapper.toResponse(updatedProduct)).thenReturn(response);

        ProductResponse result = service.update(id, request);

        assertEquals(response, result);

        verify(repository).findById(id);
        verify(mapper).updateEntityFromRequest(request, product);
        verify(repository).save(product);
        verify(mapper).toResponse(updatedProduct);
    }

    @Test
    void shouldThrowExceptionWhenUpdatingProductNotFound() {
        Long id = 99L;

        ProductRequest request = new ProductRequest(
                "Notebook Atualizado",
                "Dell Latitude Atualizado",
                BigDecimal.valueOf(4200)
        );

        when(repository.findById(id)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> service.update(id, request)
        );

        assertEquals("Produto não encontrado: 99", exception.getMessage());

        verify(repository).findById(id);
        verify(repository, never()).save(any(Product.class));
        verify(mapper, never()).updateEntityFromRequest(any(ProductRequest.class), any(Product.class));
    }

    @Test
    void shouldDeleteProduct() {
        Long id = 1L;

        Product product = new Product(
                "Notebook",
                "Dell Latitude",
                BigDecimal.valueOf(3500)
        );

        when(repository.findById(id)).thenReturn(Optional.of(product));

        service.delete(id);

        verify(repository).findById(id);
        verify(repository).delete(product);
    }

    @Test
    void shouldThrowExceptionWhenDeletingProductNotFound() {
        Long id = 99L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> service.delete(id)
        );

        assertEquals("Produto não encontrado: 99", exception.getMessage());

        verify(repository).findById(id);
        verify(repository, never()).delete(any(Product.class));
    }

    @Test
    void shouldFindAllProductsWithPaginationAndFilter() {
        ProductFilter filter = new ProductFilter(
                "note",
                BigDecimal.valueOf(1000),
                BigDecimal.valueOf(5000)
        );

        Pageable pageable = PageRequest.of(0, 10, Sort.by("name").ascending());

        Product product = new Product(
                "Notebook",
                "Dell Latitude",
                BigDecimal.valueOf(3500)
        );

        ProductResponse response = new ProductResponse(
                1L,
                "Notebook",
                "Dell Latitude",
                BigDecimal.valueOf(3500),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Page<Product> page = new PageImpl<>(List.of(product), pageable, 1);

        when(repository.findAll(any(Specification.class), eq(pageable)))
                .thenReturn(page);

        when(mapper.toResponse(product)).thenReturn(response);

        Page<ProductResponse> result = service.findAll(filter, pageable);

        assertEquals(1, result.getContent().size());
        assertEquals(response, result.getContent().getFirst());
        assertEquals(1, result.getTotalElements());

        verify(repository).findAll(any(Specification.class), eq(pageable));
        verify(mapper).toResponse(product);
    }
}