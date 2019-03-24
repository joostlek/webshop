package nl.hu.bracketboys.webshop.backend.category.repository;

import nl.hu.bracketboys.webshop.backend.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
