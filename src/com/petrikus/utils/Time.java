package com.petrikus.utils;

/**
 * Created by Petrikus on 21.06.2016.
 */
public class Time {
    public static final long SECOND = 1000000000l;


    public static long get(){
        return System.nanoTime();
    }
}
