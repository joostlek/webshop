package nl.hu.bracketboys.webshop.backend.address.exceptions;

public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(Long id) {
        super(String.format("Address with id %d not found!", id));
    }
}
