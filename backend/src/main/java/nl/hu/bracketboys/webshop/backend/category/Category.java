package nl.hu.bracketboys.webshop.backend.category;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "categories")
public class Category {

    @Id
    @SequenceGenerator(name = "category_generator", sequenceName = "category_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_generator")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double price;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date updated;

    public Category() {
    }

}
