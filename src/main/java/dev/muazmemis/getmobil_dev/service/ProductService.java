package dev.muazmemis.getmobil_dev.service;

import dev.muazmemis.getmobil_dev.mapper.ProductMapper;
import dev.muazmemis.getmobil_dev.dto.ProductResponseDto;
import dev.muazmemis.getmobil_dev.dto.ProductSaveDto;
import dev.muazmemis.getmobil_dev.entity.Product;
import dev.muazmemis.getmobil_dev.exception.ItemNotFoundException;
import dev.muazmemis.getmobil_dev.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static dev.muazmemis.getmobil_dev.helper.LogHelperComponent.logInfo;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional
    public ProductResponseDto save(ProductSaveDto product) {
        Product saveProduct = productRepository.save(ProductMapper.INSTANCE.map(product));
        logInfo("Product saved. Product id: {}", saveProduct.getId());
        return ProductMapper.INSTANCE.map(saveProduct);
    }

    @Transactional
    public List<Product> saveAll(List<Product> products) {
        List<Product> saveProducts = productRepository.saveAll(products);
        logInfo("Products saved. Size: {}", saveProducts.size());
        return saveProducts;
    }

    @Transactional
    public void updateStock(Long id, Integer quantity) {
        if (!productRepository.existsById(id))
            throw new ItemNotFoundException(id);

        productRepository.updateStock(id, quantity);
        logInfo("Product stock updated. Product id: {}, new stock: {}", id, quantity);
    }

    public List<ProductResponseDto> findAll() {
        List<Product> products = productRepository.findAll();
        logInfo("All products fetched. Product count: {}", products.size());

        return productMapper.map(products);
    }

    public Product findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
        logInfo("Product fetched. Product id: {}", id);

        return product;
    }

}
