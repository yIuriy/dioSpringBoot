package br.com.iuri.warehouse.repository;

import br.com.iuri.warehouse.entity.ProductEntity;
import br.com.iuri.warehouse.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, UUID> {
}
