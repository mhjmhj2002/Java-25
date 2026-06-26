package com.mhj.crud.product;

import java.math.BigDecimal;

public record ProductFilter(

        String name,

        BigDecimal minPrice,

        BigDecimal maxPrice

) {
}