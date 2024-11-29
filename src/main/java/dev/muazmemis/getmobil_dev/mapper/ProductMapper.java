package dev.muazmemis.getmobil_dev.mapper;

import dev.muazmemis.getmobil_dev.dto.ProductResponseDto;
import dev.muazmemis.getmobil_dev.dto.ProductSaveDto;
import dev.muazmemis.getmobil_dev.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {

    public static final ProductMapper INSTANCE = new ProductMapper();

    public ProductResponseDto map(Product product) {
        return new ProductResponseDto(product.getName(), product.getPrice(),
                product.getStock());
    }

    public List<ProductResponseDto> map(List<Product> products) {
        return products.stream().map(this::map).toList();
    }

    public Product map(ProductSaveDto productSaveDto) {
        return Product.builder()
                .name(productSaveDto.name())
                .price(productSaveDto.price())
                .taxRate(productSaveDto.taxRate())
                .discountRate(productSaveDto.discountRate())
                .stock(productSaveDto.stock())
                .build();
    }

}
