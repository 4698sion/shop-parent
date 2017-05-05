package com.shop.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Hello world!
 *
 */
public class App 
{
	@Transactional(readOnly=true)
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
