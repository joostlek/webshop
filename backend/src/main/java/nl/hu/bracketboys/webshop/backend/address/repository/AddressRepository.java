package nl.hu.bracketboys.webshop.backend.address.repository;

import nl.hu.bracketboys.webshop.backend.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> getAddressesByUserId(Long userId);
}
