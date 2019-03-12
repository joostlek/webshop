package nl.hu.bracketboys.webshop.backend.address;

import nl.hu.bracketboys.webshop.backend.address.exceptions.AddressNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService implements AddressServiceInterface {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> getAddressesByUserId(Long userId) {
        return addressRepository.getAddressesByUserId(userId);
    }

    @Override
    public Address getAddressById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException(id));
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address update(Address newAddress) {
        return addressRepository.findById(newAddress.getId())
                .map(address -> {
                    address.setStreet(newAddress.getStreet());
                    address.setCity(newAddress.getCity());
                    address.setCountry(newAddress.getCountry());
                    address.setHouseNumber(newAddress.getHouseNumber());
                    address.setZipCode(newAddress.getZipCode());
                    return addressRepository.save(address);
                })
                .orElseThrow(
                        () -> new AddressNotFoundException(newAddress.getId())
                );
    }

    @Override
    public void delete(Long addressId) {
        addressRepository.deleteById(addressId);
    }
}
