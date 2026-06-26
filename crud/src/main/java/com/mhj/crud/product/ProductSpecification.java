package com.mhj.crud.product;

import org.springframework.data.jpa.domain.Specification;

public final class ProductSpecification {

    private ProductSpecification() {
    }

    public static Specification<Product> byFilter(ProductFilter filter) {

        return Specification
                .where(nameContains(filter.name()))
                .and(priceGreaterThan(filter.minPrice()))
                .and(priceLessThan(filter.maxPrice()));
    }

    private static Specification<Product> nameContains(String name) {

        return (root, query, cb) -> {

            if (name == null || name.isBlank()) {
                return null;
            }

            return cb.like(
                    cb.lower(root.get("name")),
                    "%" + name.toLowerCase() + "%"
            );
        };
    }

    private static Specification<Product> priceGreaterThan(java.math.BigDecimal price) {

        return (root, query, cb) -> {

            if (price == null) {
                return null;
            }

            return cb.greaterThanOrEqualTo(root.get("price"), price);
        };
    }

    private static Specification<Product> priceLessThan(java.math.BigDecimal price) {

        return (root, query, cb) -> {

            if (price == null) {
                return null;
            }

            return cb.lessThanOrEqualTo(root.get("price"), price);
        };
    }

}