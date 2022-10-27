package com.phptravels.utils;

public class RandomGenerator {

    public static String generateValidEmail(){
        return "mymail"+System.nanoTime()+"@gmail.com";
    }
}
