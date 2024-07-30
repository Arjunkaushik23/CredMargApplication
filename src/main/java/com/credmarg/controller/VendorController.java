package com.credmarg.controller;

import com.credmarg.model.Vendor;
import com.credmarg.service.EmailService;
import com.credmarg.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vendors")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class VendorController {

    private static final Logger logger = LoggerFactory.getLogger(VendorController.class);

    private final VendorService vendorService;
    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<Vendor> createVendor(@RequestBody Vendor vendor) {
        return ResponseEntity.ok(vendorService.createVendor(vendor));
    }

    @GetMapping
    public ResponseEntity<List<Vendor>> getAllVendors() {
        return ResponseEntity.ok(vendorService.getAllVendors());
    }

    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmailToVendors(@RequestBody List<String> vendorEmails) {
        // Implement email sending logic here
        emailService.sendEmail(vendorEmails);
        logger.info("Email Sent Successfully");
        return ResponseEntity.ok("Email Sent Successfully");
    }

    @GetMapping("/sendEmails")
    public ResponseEntity<List<String>> getAllEmails() {
        return ResponseEntity.ok(emailService.getAllEmails());
    }
}
