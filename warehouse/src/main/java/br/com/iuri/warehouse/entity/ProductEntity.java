package br.com.iuri.warehouse.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.*;

import static br.com.iuri.warehouse.entity.StockStatus.AVAILABLE;
import static jakarta.persistence.CascadeType.ALL;

@Entity
@Getter
@Setter
@ToString
public class ProductEntity {
    @Id
    private UUID id;

    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "product", cascade = ALL)
    private Set<StockEntity> stocks = new HashSet<>();

    public StockEntity decStock() {
        var stock = getStockWithMinSoldPrice();
        stock.decAmount();
        return stock;
    }

    private BigDecimal getPrice() {
        return getStockWithMinSoldPrice().getSoldPrice();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        ProductEntity that = (ProductEntity) object;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @PrePersist
    private void prePersist() {
        this.id = UUID.randomUUID();
    }

    private StockEntity getStockWithMinSoldPrice() {
        return this.stocks.stream()
                .filter(s -> s.getStatus().equals(AVAILABLE))
                .min(Comparator.comparing(StockEntity::getSoldPrice))
                .orElseThrow();
    }
}
