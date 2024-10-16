package com.diogofranca.automatedtestswithjava.modulo6mockito.service;

public class MyUtils {

    public static String getWelcomeMessage(String username, boolean isCustomer) {

        if(isCustomer) {
            return "Welcome Dear" + username + "!";
        } else {
            return "Welcome " + username + "!";
        }
    }
}
