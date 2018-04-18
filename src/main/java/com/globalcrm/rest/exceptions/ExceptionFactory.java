package com.globalcrm.rest.exceptions;

/**
 * Created by Hugo Lezama on April - 2018
 */
public class ExceptionFactory {
    public static ResourceNotFoundException userNotFound (Long id){
        return new ResourceNotFoundException("User with ID: "+id+" not found");
    }
}
