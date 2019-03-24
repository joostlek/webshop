package nl.hu.bracketboys.webshop.backend.category;

import nl.hu.bracketboys.webshop.backend.product.Product;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Category {

    @Id
    @SequenceGenerator(name = "category_generator", sequenceName = "category_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_generator")
    private Long id;

    private String name;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Category_Product",
            joinColumns = {
                    @JoinColumn(name = "category_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "product_id")
            }
    )
    private Set<Product> products = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }
}
