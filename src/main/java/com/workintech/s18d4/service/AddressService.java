package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Address;
import com.workintech.s18d4.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

    public List<Address> findAll() { return addressRepository.findAll(); }
    public Address find(Long id) { return addressRepository.findById(id).orElse(null); }
    public Address save(Address address) { return addressRepository.save(address); }
    public Address delete(Long id) {
        Address address = find(id);
        if(address != null) addressRepository.delete(address);
        return address;
    }
}