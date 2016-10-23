/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adr.hellocalc.test;

import java.util.NoSuchElementException;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author adrian
 */
public class DSLTest {

    public DSLTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void scanLiterals() {
        String rx;
        Scanner scanner;
        
        rx = "[^\"\\s]+|\"(\\\\.|[^\\\\\"])*\"";
        scanner = new Scanner("P 160,  SomethingElse \"A string \\\"literal\" end"); // does not check \" outside a literal
        System.out.println("-->");
        System.out.println(scanner.findInLine(rx)); // => P
        System.out.println(scanner.findInLine(rx)); // => 160
        System.out.println(scanner.findInLine(rx)); // => SomethingElse
        System.out.println(scanner.findInLine(rx)); // => "A string literal"
        System.out.println(scanner.findInLine(rx)); // => end     

        rx = "[^\"\\s\\,]+|\"(\\\\.|[^\\\\\"])*\""; // This is the good expression: separator spaces and commas.
        scanner = new Scanner("P 160,SomethingElse \"A string \\\"literal\" end\nNew line"); // does not check \" outside a literal
        System.out.println("-->");        
        System.out.println(scanner.findInLine(rx)); // => P
        System.out.println(scanner.findInLine(rx)); // => 160
        System.out.println(scanner.findInLine(rx)); // => SomethingElse
        System.out.println(scanner.findInLine(rx)); // => "A string literal"
        System.out.println(scanner.findInLine(rx)); // => end    
        System.out.println(scanner.findInLine(rx)); // => null    
        
        System.out.println("nextline -> " + scanner.nextLine());
        System.out.println(scanner.findInLine(rx)); // => New         
        System.out.println(scanner.findInLine(rx)); // => line         
        System.out.println(scanner.findInLine(rx)); // => null
        
        try {
            System.out.println("nextline -> " + scanner.nextLine()); // throws Exception
            Assert.fail();
        } catch (NoSuchElementException ex) {
            System.out.println("End of file");
        }
        
    }
}
