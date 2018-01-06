package com.playground.tinderswipe.model;

import com.playground.tinderswipe.repository.entity.AccountEntity;
import org.joda.time.DateTime;

public class Account {
    private String id;
    private String firstName;
    private String lastName;
    private String photoUrl;
    private String gender;
    private DateTime birthday;

    public Account() {}

    public Account(String id, String firstName, String lastName, String photoUrl, String gender, DateTime birthday) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.photoUrl = photoUrl;
        this.gender = gender;
        this.birthday = birthday;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public DateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(DateTime birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                '}';
    }

    public static AccountEntity toAccountEntity(Account account) {
        return new AccountEntity(account.getFirstName(), account.getLastName(), account.getPhotoUrl(),
                account.getGender(), account.getBirthday());
    }

    public static Account fromAccountEntity(AccountEntity entity) {
        return new Account(entity.getId(), entity.getFirstName(), entity.getLastName(),
                entity.getPhotoUrl(), entity.getGender().toLowerCase(), entity.getBirthDate());
    }
}
