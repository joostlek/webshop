package nl.hu.bracketboys.webshop.backend.product.repository;

import nl.hu.bracketboys.webshop.backend.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select p from Product p inner join Category category on category.id = :id")
    List<Product> getAllByCategoryId(@Param("id") Long categoryId);
}
