package dev.muazmemis.getmobil_dev.controller;

import dev.muazmemis.getmobil_dev.dto.OrderRequestDto;
import dev.muazmemis.getmobil_dev.dto.OrderResponseDto;
import dev.muazmemis.getmobil_dev.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public void save(@Valid @RequestBody OrderRequestDto orderRequestDto) {
        orderService.processSaveOrder(orderRequestDto);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> findAll() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.findById(id));
    }

}
