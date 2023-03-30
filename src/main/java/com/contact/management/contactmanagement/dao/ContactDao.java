package com.contact.management.contactmanagement.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContactDao {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    public ContactDao(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
