package csd214.app.repositories;

import csd214.app.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @Repository tells Spring's IoC container to manage this bean
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    // Spring Data JPA automatically provides:
    // save(), findById(), findAll(), deleteById(), count(), deleteAll()
}