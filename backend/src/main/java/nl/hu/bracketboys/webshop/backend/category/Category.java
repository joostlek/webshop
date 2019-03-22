package nl.hu.bracketboys.webshop.backend.category;

import javax.persistence.*;

@Entity
public class Category {

    @Id
    @SequenceGenerator(name = "category_generator", sequenceName = "category_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_generator")
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
