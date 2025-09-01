package br.com.iuri.warehouse.mapper;

import br.com.iuri.warehouse.controller.request.ProductSaveRequest;
import br.com.iuri.warehouse.controller.response.ProductDetailResponse;
import br.com.iuri.warehouse.controller.response.ProductSaveResponse;
import br.com.iuri.warehouse.dto.ProductStorefrontSaveDTO;
import br.com.iuri.warehouse.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface IProductMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "stocks", ignore = true)
    ProductEntity toEntity(final ProductSaveRequest request);

    ProductSaveResponse toSavedResponse(final ProductEntity entity);

    ProductStorefrontSaveDTO toDTO(final ProductEntity entity);

    @Mapping(target = "price", expression = "java(entity.getPrice())")
    ProductDetailResponse toDetailResponse(final ProductEntity entity);
}
