package br.com.iuri.warehouse.controller;

import br.com.iuri.warehouse.mapper.IStockMapper;
import br.com.iuri.warehouse.service.IStockService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("stocks")
@AllArgsConstructor
public class StockController {

    private final IStockService service;
    private final IStockMapper mapper;

}
