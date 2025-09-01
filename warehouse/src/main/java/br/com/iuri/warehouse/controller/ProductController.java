package br.com.iuri.warehouse.controller;

import br.com.iuri.warehouse.controller.request.ProductSaveRequest;
import br.com.iuri.warehouse.controller.response.ProductDetailResponse;
import br.com.iuri.warehouse.controller.response.ProductSaveResponse;
import br.com.iuri.warehouse.mapper.IProductMapper;
import br.com.iuri.warehouse.service.IProductQueryService;
import br.com.iuri.warehouse.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.rmi.server.UID;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("products")
@AllArgsConstructor
public class ProductController {

    private final IProductService service;
    private final IProductQueryService queryService;
    private final IProductMapper mapper;

    @PostMapping
    @ResponseStatus(CREATED)
    ProductSaveResponse create(@RequestBody final ProductSaveRequest request) {
        var entity = mapper.toEntity(request);
        entity = service.save(entity);
        return mapper.toSavedResponse(entity);
    }

    @PostMapping("{id}/purchase")
    @ResponseStatus(NO_CONTENT)
    void purchase(@PathVariable final UUID id) {
        service.purchase(id);
    }

    @GetMapping("{id}")
    ProductDetailResponse findById(@PathVariable final UUID id) {
        var dto = queryService.findById(id);
        return mapper.toDetailResponse(dto);
    }
}
