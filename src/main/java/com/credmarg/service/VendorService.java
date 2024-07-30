package com.credmarg.service;

import com.credmarg.model.User;
import com.credmarg.model.Vendor;
import com.credmarg.repository.UserRepository;
import com.credmarg.repository.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VendorService {

    private final VendorRepository vendorRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Vendor createVendor(Vendor vendor) {

        User user = new User();
        user.setUsername(vendor.getEmail());
        user.setPassword(passwordEncoder.encode("default_password"));
        user.setRole("Role_Admin");

        userRepository.save(user);
        vendor.setUser(user);

        return vendorRepository.save(vendor);
    }

    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

}
