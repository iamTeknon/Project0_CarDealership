package com.automart.exceptions;

public class NonExistentCustomerException extends NonExistentEntityException{
    private static final long serialVersionUID = 8633588908169766368L;

    public NonExistentCustomerException() {
        super("Customer does not exist");
    }
}
