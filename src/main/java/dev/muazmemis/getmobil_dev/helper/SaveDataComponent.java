package dev.muazmemis.getmobil_dev.helper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.muazmemis.getmobil_dev.entity.Product;
import dev.muazmemis.getmobil_dev.service.ProductService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SaveDataComponent {

    private final ProductService productService;

    @PostConstruct
    public void saveProducts() throws IOException {
        if (productService.findAll().isEmpty()) {
            ClassPathResource resource = new ClassPathResource("product_data.json");

            ObjectMapper mapper = new ObjectMapper();
            List<Product> products = mapper.readValue(resource.getInputStream(), new TypeReference<>() {
            });
            productService.saveAll(products);
        }
    }
}
