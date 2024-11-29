package dev.muazmemis.getmobil_dev.repository;

import dev.muazmemis.getmobil_dev.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
