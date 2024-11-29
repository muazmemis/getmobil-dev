package dev.muazmemis.getmobil_dev.service;

import dev.muazmemis.getmobil_dev.dto.InvoiceResponseDto;
import dev.muazmemis.getmobil_dev.entity.Invoice;
import dev.muazmemis.getmobil_dev.entity.Invoice;
import dev.muazmemis.getmobil_dev.exception.ItemNotFoundException;
import dev.muazmemis.getmobil_dev.mapper.InvoiceMapper;
import dev.muazmemis.getmobil_dev.repository.InvoiceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InvoiceServiceTest {

    @Mock
    Invoice invoice;

    @Mock
    InvoiceResponseDto invoiceResponseDto;

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private InvoiceMapper invoiceMapper;

    @InjectMocks
    private InvoiceService invoiceService;

    @Test
    void shouldSave() {
        when(invoice.getId()).thenReturn(1L);
        when(invoiceRepository.save(any())).thenReturn(invoice);

        Invoice result = invoiceService.save(invoice);

        assertEquals(1L, result.getId());
    }

    @Test
    void shouldFindAll() {
        when(invoiceMapper.map(anyList())).thenReturn(List.of());

        assertEquals(0, invoiceService.findAll().size());
    }

    @Test
    void shouldFindAllWhenReturnsInvoices() {
        when(invoiceMapper.map(anyList())).thenReturn(List.of(invoiceResponseDto));

        assertEquals(1, invoiceService.findAll().size());
    }

    @Test
    void shouldFindAllWhenInvoiceListIsNull() {
        when(invoiceRepository.findAll()).thenReturn(null);

        assertNull(invoiceService.findAll());
    }

    @Test
    void shouldFindById() {
        when(invoiceResponseDto.id()).thenReturn(1L);
        when(invoiceRepository.findById(anyLong())).thenReturn(Optional.of(invoice));
        when(invoiceMapper.map(invoice)).thenReturn(invoiceResponseDto);


        InvoiceResponseDto result = invoiceService.findById(1L);
        assertEquals(1L, result.id());
    }

    @Test
    void shouldNotFindByIdWhenIdDoesNotExist() {
        when(invoiceRepository.findById(anyLong())).thenThrow(ItemNotFoundException.class);
        assertThrows(ItemNotFoundException.class, () -> invoiceService.findById(-1L));
    }
}
