package com.contact.management.contactmanagement.service;

import com.contact.management.contactmanagement.dao.ContactDao;

import java.util.List;

public interface ContactService {

    public List<ContactDao> saveContacts(List<ContactDao> contactDao);
    public List<ContactDao> getContacts();
    public List<ContactDao> getContactByFirstName(String firstName);
    public List<ContactDao> getContactByLastName(String lastName);
    public List<ContactDao> getContactByEmail(String email);
    public ContactDao updateContact(ContactDao contactDao, String id);
    public void deleteContact(String id);

}
