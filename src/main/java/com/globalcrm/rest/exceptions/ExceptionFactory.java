package com.globalcrm.rest.exceptions;

/**
 * Created by Hugo Lezama on April - 2018
 */
public class ExceptionFactory {
    public static ResourceNotFoundException userNotFound (Long id){
        return new ResourceNotFoundException("User ID: "+id+" not found");
    }
    public static ResourceNotFoundException accountNotFound (Long id){
        return new ResourceNotFoundException("Account ID: "+id+" not found");
    }

    public static ResourceNotFoundException contactNotFound(Long id) {
        return new ResourceNotFoundException("Contact ID: "+id+" not found");
    }
}
