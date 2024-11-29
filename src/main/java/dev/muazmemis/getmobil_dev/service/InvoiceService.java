package dev.muazmemis.getmobil_dev.service;

import dev.muazmemis.getmobil_dev.dto.InvoiceResponseDto;
import dev.muazmemis.getmobil_dev.entity.Invoice;
import dev.muazmemis.getmobil_dev.exception.ItemNotFoundException;
import dev.muazmemis.getmobil_dev.mapper.InvoiceMapper;
import dev.muazmemis.getmobil_dev.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static dev.muazmemis.getmobil_dev.helper.LogHelperComponent.logInfo;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;

    public Invoice save(Invoice invoice) {
        Invoice saveInvoice = invoiceRepository.save(invoice);
        logInfo("Invoice saved: {}", saveInvoice);

        return saveInvoice;
    }

    public List<InvoiceResponseDto> findAll() {
        List<Invoice> invoices = invoiceRepository.findAll();
        // TODO: This is a bug. It should be checked if the list is null or empty.
        if (invoices == null)
            return null;

        logInfo("All invoices fetched. Invoice count: {}", invoices.size());

        return invoiceMapper.map(invoices);
    }

    public InvoiceResponseDto findById(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
        logInfo("Invoice fetched. Invoice id: {}", id);

        return invoiceMapper.map(invoice);
    }

}

