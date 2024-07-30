package com.credmarg.controller;

import com.credmarg.model.Vendor;
import com.credmarg.service.EmailService;
import com.credmarg.service.VendorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vendors")
@CrossOrigin(origins = "http://localhost:3000")
public class VendorController {

    private final VendorService vendorService;
    private final EmailService emailService;

    public VendorController(VendorService vendorService, EmailService emailService) {
        this.vendorService = vendorService;
        this.emailService = emailService;
    }

    @PostMapping
    public Vendor createVendor(@RequestBody Vendor vendor) {
        return vendorService.createVendor(vendor);
    }

    @GetMapping
    public List<Vendor> getAllVendors() {
        return vendorService.getAllVendors();
    }

    @PostMapping("/send-email")
    public String sendEmailToVendors(@RequestBody List<String> vendorEmails) {
        // Implement email sending logic here
        emailService.sendEmail(vendorEmails);
        System.out.println("Email sent Successfully");
        return "Email Sent Successfully";
    }

    @GetMapping("/sendEmails")
    public List<String> sendEmails() {
        return emailService.getAllEmails();
    }
}
