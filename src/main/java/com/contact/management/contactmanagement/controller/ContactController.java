package com.contact.management.contactmanagement.controller;

import com.contact.management.contactmanagement.dao.ContactDao;
import com.contact.management.contactmanagement.exception.NoSuchContactsExistException;
import com.contact.management.contactmanagement.service.ContactServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/contact")
public class ContactController {
    @Autowired
    private ContactServiceImpl service;

    @PostMapping()
    public ResponseEntity<List<ContactDao>> addContactsDetail(@RequestBody List<ContactDao> contacts) {

        List<ContactDao> contactDao = service.saveContacts(contacts);
        log.info("Contact Added");
        return new ResponseEntity<>(contactDao, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<ContactDao>> getContactsDetails() {
        log.info("Contact Fetching");
        List<ContactDao> parts = service.getContacts();
        if (parts.isEmpty()) {
            throw new NoSuchContactsExistException("Contacts Not Found");
        }
        return new ResponseEntity<>(parts, HttpStatus.OK);
    }

    @GetMapping("/firstname/{firstName}")
    public ResponseEntity<List<ContactDao>> getContactByFirstName(@PathVariable String firstName) {
        log.info("Parts Fetched by Model");
        List<ContactDao> contactDaoList = service.getContactByFirstName(firstName);
        if (contactDaoList == null) {
            throw new NoSuchContactsExistException("Contact Not Found");
        }
        return new ResponseEntity<>(contactDaoList, HttpStatus.OK);
    }

    @GetMapping("/lastname/{lastName}")
    public ResponseEntity<List<ContactDao>> getContactByLastName(@PathVariable String lastName) {
        log.info("Parts Fetched by Model");
        List<ContactDao> contactDaoList = service.getContactByLastName(lastName);
        if (contactDaoList == null) {
            throw new NoSuchContactsExistException("Contact Not Found");
        }
        return new ResponseEntity<>(contactDaoList, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<List<ContactDao>> getContactByEmail(@PathVariable String email) {
        log.info("Parts Fetched by Model");
        List<ContactDao> contactDaoList = service.getContactByEmail(email);
        if (contactDaoList == null) {
            throw new NoSuchContactsExistException("Contact Not Found");
        }
        return new ResponseEntity<>(contactDaoList, HttpStatus.OK);
    }
}
