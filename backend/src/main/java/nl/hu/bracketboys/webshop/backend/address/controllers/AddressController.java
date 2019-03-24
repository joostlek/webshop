package nl.hu.bracketboys.webshop.backend.address.controllers;

import nl.hu.bracketboys.webshop.backend.address.Address;
import nl.hu.bracketboys.webshop.backend.address.service.AddressServiceInterface;
import nl.hu.bracketboys.webshop.backend.address.dto.AddressDTO;
import nl.hu.bracketboys.webshop.backend.user.User;
import nl.hu.bracketboys.webshop.backend.user.service.UserServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@BasePathAwareController
public class AddressController {
    private final AddressServiceInterface addressService;

    private final ModelMapper modelMapper;

    private final UserServiceInterface userService;

    @Autowired
    public AddressController(AddressServiceInterface addressService, ModelMapper modelMapper, UserServiceInterface userService) {
        this.addressService = addressService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/users/{userId}/addresses")
    @ResponseStatus(HttpStatus.OK)
    public List<AddressDTO> getAllAddressesByUserId(@PathVariable Long userId) {
        return addressService.getAddressesByUserId(userId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/users/{userId}/addresses")
    @ResponseStatus(HttpStatus.CREATED)
    public AddressDTO addAddress(@PathVariable Long userId, @RequestBody AddressDTO addressDTO) {
        Address address = convertToEntity(addressDTO);
        User user = userService.getUserById(userId);
        address.setUser(user);
        return convertToDTO(addressService.save(address));
    }

    @PutMapping("/addresses/{addressId}")
    @ResponseStatus(HttpStatus.OK)
    public AddressDTO updateAddress(@PathVariable Long addressId, @RequestBody AddressDTO addressDTO) {
        Address address = convertToEntity(addressDTO);
        address.setId(addressId);
        return convertToDTO(addressService.update(address));
    }

    @DeleteMapping("/addresses/{addressId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAddress(@PathVariable Long addressId) {
        addressService.delete(addressId);
    }

    private AddressDTO convertToDTO(Address address) {
        return modelMapper.map(address, AddressDTO.class);
    }

    private Address convertToEntity(AddressDTO addressDTO) {
        return modelMapper.map(addressDTO, Address.class);
    }
}
