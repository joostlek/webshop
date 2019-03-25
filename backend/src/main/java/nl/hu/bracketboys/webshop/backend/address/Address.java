package nl.hu.bracketboys.webshop.backend.address;

import nl.hu.bracketboys.webshop.backend.order.Order;
import nl.hu.bracketboys.webshop.backend.user.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Address {

    @Id
    @SequenceGenerator(name = "address_generator", sequenceName = "address_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_generator")
    private Long id;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String houseNumber;

    @Column(nullable = false)
    private String zipCode;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private User user;

    @OneToMany(mappedBy = "address")
    private Set<Order> orders = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
