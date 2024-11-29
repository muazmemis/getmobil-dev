package dev.muazmemis.getmobil_dev.mapper;

import dev.muazmemis.getmobil_dev.dto.InvoiceResponseDto;
import dev.muazmemis.getmobil_dev.entity.Invoice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InvoiceMapper {

    private final OrderItemMapper orderItemMapper;

    public InvoiceResponseDto map(Invoice invoice) {
        return new InvoiceResponseDto(invoice.getId(),
                invoice.getOrder().getId(),
                orderItemMapper.mapToResponse(invoice.getOrder().getItems()),
                invoice.getOrder().getTotalAmount(),
                invoice.getDate());
    }

    public List<InvoiceResponseDto> map(List<Invoice> invoices) {
        return invoices.stream().map(this::map).toList();
    }

}
