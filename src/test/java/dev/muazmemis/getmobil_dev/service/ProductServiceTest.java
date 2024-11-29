package dev.muazmemis.getmobil_dev.service;

import dev.muazmemis.getmobil_dev.dto.ProductResponseDto;
import dev.muazmemis.getmobil_dev.dto.ProductSaveDto;
import dev.muazmemis.getmobil_dev.entity.Product;
import dev.muazmemis.getmobil_dev.exception.ItemNotFoundException;
import dev.muazmemis.getmobil_dev.mapper.ProductMapper;
import dev.muazmemis.getmobil_dev.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private Product product;

    @Mock
    private ProductResponseDto productResponseDto;

    @Mock
    private ProductSaveDto productSaveDto;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    @Test
    void shouldFindAll() {
        assertEquals(0, productService.findAll().size());
    }

    @Test
    void shouldFindAllWhenReturnsProducts() {
        when(productRepository.findAll()).thenReturn(List.of(product, product));
        when(productMapper.map(anyList())).thenReturn(List.of(productResponseDto, productResponseDto));

        assertEquals(2, productService.findAll().size());
    }

    @Test
    void shouldFindAllWhenProductListIsNull() {
        when(productRepository.findAll()).thenReturn(null);

        assertNull(productService.findAll());
    }

    @Test
    void shouldFindById() {
        when(product.getId()).thenReturn(1L);
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        Product result = productService.findById(1L);
        assertEquals(1L, result.getId());
    }

    @Test
    void shouldNotFindByIdWhenIdDoesNotExist() {
        when(productRepository.findById(anyLong())).thenThrow(ItemNotFoundException.class);
        assertThrows(ItemNotFoundException.class, () -> productService.findById(-1L));
    }

    @Test
    void shouldSave() {
        when(product.getName()).thenReturn("Product");
        when(productRepository.save(any())).thenReturn(product);

        ProductResponseDto result = productService.save(productSaveDto);

        assertEquals("Product", result.name());
    }

    @Test
    void shouldSaveAll() {
        List<Product> products = List.of(product, product);
        when(productRepository.saveAll(any())).thenReturn(products);
        List<Product> saveAll = productService.saveAll(products);

        assertEquals(2, saveAll.size());
    }

    @Test
    void shouldUpdateStock() {
        when(productRepository.existsById(anyLong())).thenReturn(true);
        doNothing().when(productRepository).updateStock(anyLong(), anyInt());

        productService.updateStock(1L, 100);

        verify(productRepository).updateStock(1L, 100);
    }

    @Test
    void shouldUpdateStockNotIsExistByIdWhenIdDoesNotExist() {
        when(productRepository.existsById(1L)).thenReturn(false);
        assertThrows(ItemNotFoundException.class, () -> productService.updateStock(1L, 100));
    }

}
