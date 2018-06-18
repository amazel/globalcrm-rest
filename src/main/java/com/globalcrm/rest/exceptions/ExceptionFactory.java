package com.globalcrm.rest.exceptions;

/**
 * Created by Hugo Lezama on April - 2018
 */
public class ExceptionFactory {
    public static ResourceNotFoundException userNotFound(Long id) {
        return new ResourceNotFoundException("User ID: " + id + " not found");
    }
    public static ResourceNotFoundException userNotFound(String email) {
        return new ResourceNotFoundException("User not found. EmailDTO: "+email);
    }

    public static ResourceNotFoundException accountNotFound(Long id) {
        return new ResourceNotFoundException("Account ID: " + id + " not found");
    }

    public static ResourceNotFoundException contactNotFound(Long id) {
        return new ResourceNotFoundException("Contact ID: " + id + " not found");
    }

    public static MismatchException accountIdMismatch() {
        return new MismatchException("Account ID mismatch");
    }

    public static ResourceNotFoundException userNotCreated() {
        return new ResourceNotFoundException("User not creationDateTime");
    }

    public static ResourceNotFoundException companyNotCreated() {
        return new ResourceNotFoundException("Company was not creationDateTime");
    }

    public static ResourceNotFoundException companyNotFound(Long id) {
        return new ResourceNotFoundException("Company ID: " + id + " not found");
    }

    public static ResourceNotFoundException contactNotCreated() {
        return new ResourceNotFoundException("Contact was not creationDateTime");
    }

    public static ResourceNotFoundException saleNotFound(Long saleId) {
        return new ResourceNotFoundException("Sale ID: " + saleId + " not found");
    }

    public static BadLoginException badLoginException (){
        throw new BadLoginException("Bad credentials!");
    }
}
