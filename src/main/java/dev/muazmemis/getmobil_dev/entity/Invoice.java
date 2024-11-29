package dev.muazmemis.getmobil_dev.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "invoices")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_invoice_order"))
    private Order order;

    @CreationTimestamp
    private LocalDateTime date;

}
