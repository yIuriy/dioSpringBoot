package br.com.iuri.storefront.mapper;

import br.com.iuri.storefront.controller.request.ProductSaveRequest;
import br.com.iuri.storefront.controller.response.ProductAvailableResponse;
import br.com.iuri.storefront.controller.response.ProductDetailResponse;
import br.com.iuri.storefront.controller.response.ProductSavedResponse;
import br.com.iuri.storefront.dto.ProductDetailDTO;
import br.com.iuri.storefront.dto.ProductInfoDTO;
import br.com.iuri.storefront.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface IProductMapper {
    ProductInfoDTO toDTO(final ProductEntity entity, final BigDecimal price);

    @Mapping(target = "active", constant = "false")
    ProductEntity toEntity(final ProductSaveRequest request);

    ProductSavedResponse toResponse(final ProductEntity entity);

    List<ProductAvailableResponse> toResponse(final List<ProductEntity> entities);

    ProductDetailResponse toResponse(final ProductInfoDTO dto);
}
