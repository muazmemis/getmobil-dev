package dev.muazmemis.getmobil_dev.repository;

import dev.muazmemis.getmobil_dev.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Modifying
    @Query("UPDATE Product p SET p.stock = :newStock WHERE p.id = :id")
    void updateStock(Long id, Integer newStock);

}
