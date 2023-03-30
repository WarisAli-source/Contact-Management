package com.contact.management.contactmanagement.service;

import com.contact.management.contactmanagement.dao.ContactDao;
import com.contact.management.contactmanagement.entity.Contact;
import com.contact.management.contactmanagement.exception.NoSuchContactsExistException;
import com.contact.management.contactmanagement.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository repository;


    public List<ContactDao> saveContacts(List<ContactDao> contactDao) {
        log.info("Adding Parts");
        if (contactDao.isEmpty()) {throw new NoSuchContactsExistException("No Contacts Available");
        }
        List<Contact> parts = new ArrayList<Contact>();
        for (ContactDao contacts : contactDao) {
            Contact contact = new Contact();
            BeanUtils.copyProperties(contacts, contact);
            parts.add(contact);
        }
        repository.saveAll(parts);
        return contactDao;
    }


    public List<ContactDao> getContacts() {
        log.info("Getting Parts");
        List<Contact> contacts = repository.findAll();
        if (contacts.isEmpty()) { throw new NoSuchContactsExistException("Contacts not Found :" + contacts);
        }
        else {
            List<ContactDao> allContacts = new ArrayList<ContactDao>();
            for (Contact contact : contacts) {
                ContactDao contactDao = new ContactDao();
                BeanUtils.copyProperties(contact, contactDao);
                allContacts.add(contactDao);
            }
            return allContacts;
        }

    }

    public List<ContactDao> getContactByFirstName(String firstName) {
        List<Contact> contacts = repository.findByFirstName(firstName);
        List<ContactDao> contactDaoList = new ArrayList<ContactDao>();
        log.info("Getting Contact by first name" + firstName);
        if (contacts.isEmpty()) {throw new NoSuchContactsExistException("No Contact found for first name:" + firstName);
        } else {

            for (Contact contact : contacts) {
                ContactDao contactDao = new ContactDao();
                BeanUtils.copyProperties(contact, contactDao);
                contactDaoList.add(contactDao);
            }
        }
        return contactDaoList;

    }

    public List<ContactDao> getContactByLastName(String lastName) {
        List<Contact> contacts = repository.findByLastName(lastName);
        List<ContactDao> contactDaoList = new ArrayList<ContactDao>();
        log.info("Getting Contact by last name" + lastName);
        if (contacts.isEmpty()) {throw new NoSuchContactsExistException("No Contact found for last name:" + lastName);
        } else {

            for (Contact contact : contacts) {
                ContactDao contactDao = new ContactDao();
                BeanUtils.copyProperties(contact, contactDao);
                contactDaoList.add(contactDao);
            }
        }
        return contactDaoList;

    }

    public List<ContactDao> getContactByEmail(String email) {
        List<Contact> contacts = repository.findByEmail(email);
        List<ContactDao> contactDaoList = new ArrayList<ContactDao>();
        log.info("Getting Contact by email" + email);
        if (contacts.isEmpty()) {throw new NoSuchContactsExistException("No Contact found for email:" + email);
        } else {

            for (Contact contact : contacts) {
                ContactDao contactDao = new ContactDao();
                BeanUtils.copyProperties(contact, contactDao);
                contactDaoList.add(contactDao);
            }
        }
        return contactDaoList;

    }


    public ContactDao updateContact(ContactDao contactDao, String id) {

        Contact contact = repository.findById(id).orElseThrow(() -> new NoSuchContactsExistException("No ContactFound for ID:" + id));
        log.info("Updating parts by ID: " + id);

        BeanUtils.copyProperties(contactDao, contact);

        log.info("Parts Updated by ID: " + contactDao.getId());
        Contact parts = repository.save(contact);
        return contactDao;

    }

    public void deleteContact(String id) {
        repository.findById(id).orElseThrow(() -> new NoSuchContactsExistException("No Contact Found for ID:" + id));
        log.error("Deleting Parts By ID" + id);
        repository.deleteById(id);

    }
}
