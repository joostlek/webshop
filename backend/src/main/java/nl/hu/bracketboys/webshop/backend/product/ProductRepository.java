package nl.hu.bracketboys.webshop.backend.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select p from Product p inner join p.categories category on category.id = :id")
    List<Product> getAllByCategoryId(@Param("id") Long categoryId);
}
