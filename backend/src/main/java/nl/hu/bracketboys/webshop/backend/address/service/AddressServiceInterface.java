package nl.hu.bracketboys.webshop.backend.address.service;

import nl.hu.bracketboys.webshop.backend.address.Address;

import java.util.List;

public interface AddressServiceInterface {
    List<Address> getAddressesByUserId(Long userId);

    Address getAddressById(Long id);

    Address save(Address address);

    Address update(Address address);

    void delete(Long addressId);
}
