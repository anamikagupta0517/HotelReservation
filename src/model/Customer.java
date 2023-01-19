package model;

import Resources.DataValidator;

public class Customer {
    private String email;
    private String firstName;
    private String lastName;

    public Customer(String firstName, String lastName, String email) throws IllegalArgumentException{
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        if(!DataValidator.isEmailValid(email)){
            throw new IllegalArgumentException();
        }
    }

    public String getEmail() {
        return this.email;
    }

    @Override
    public String toString(){
        return String.format("First Name: %s Last Name: %s email: %s", this.firstName, this.lastName, this.email);
    }
}
