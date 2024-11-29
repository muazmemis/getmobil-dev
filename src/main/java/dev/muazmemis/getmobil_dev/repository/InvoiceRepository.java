package dev.muazmemis.getmobil_dev.repository;

import dev.muazmemis.getmobil_dev.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}
