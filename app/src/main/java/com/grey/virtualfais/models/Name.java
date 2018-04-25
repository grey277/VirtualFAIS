package com.grey.virtualfais.models;

import com.grey.virtualfais.StringUtils;

import java.util.Objects;

public class Name {
    private String title;

    private String firstName;
    private String firstNameNormalized;

    private String lastName;
    private String lastNameNormalized;

    public Name(String title, String firstName, String lastName) {
        this.title = title;
        this.firstName = firstName;
        this.firstNameNormalized = StringUtils.normalize(firstName);
        this.lastName = lastName;
        this.lastNameNormalized = StringUtils.normalize(lastName);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstNameNormalized() {
        return firstNameNormalized;
    }

    public void setFirstNameNormalized(String firstNameNormalized) {
        this.firstNameNormalized = firstNameNormalized;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastNameNormalized() {
        return lastNameNormalized;
    }

    public void setLastNameNormalized(String lastNameNormalized) {
        this.lastNameNormalized = lastNameNormalized;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return Objects.equals(title, name.title) &&
                Objects.equals(firstName, name.firstName) &&
                Objects.equals(firstNameNormalized, name.firstNameNormalized) &&
                Objects.equals(lastName, name.lastName) &&
                Objects.equals(lastNameNormalized, name.lastNameNormalized);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, firstName, firstNameNormalized, lastName, lastNameNormalized);
    }

    @Override
    public String toString() {
        return "Name{" +
                "title='" + title + '\'' +
                ", firstName='" + firstName + '\'' +
                ", firstNameNormalized='" + firstNameNormalized + '\'' +
                ", lastName='" + lastName + '\'' +
                ", lastNameNormalized='" + lastNameNormalized + '\'' +
                '}';
    }
}
