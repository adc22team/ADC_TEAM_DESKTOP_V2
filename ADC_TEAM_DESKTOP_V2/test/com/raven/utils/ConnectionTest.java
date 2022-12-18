/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.raven.utils;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aigardo
 */
public class ConnectionTest {
    
    public ConnectionTest() {
    }

    @Test
    public void testGetUser() {
        System.out.println("getUser");
        Connection instance = new Connection();
        String expResult = "";
        String result = instance.getUser();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetUser() {
        System.out.println("setUser");
        String user = "";
        Connection instance = new Connection();
        instance.setUser(user);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        Connection instance = new Connection();
        String expResult = "";
        String result = instance.getPassword();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "";
        Connection instance = new Connection();
        instance.setPassword(password);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetId_conn() {
        System.out.println("getId_conn");
        Connection instance = new Connection();
        int expResult = 0;
        int result = instance.getId_conn();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetId_conn() {
        System.out.println("setId_conn");
        int id_conn = 0;
        Connection instance = new Connection();
        instance.setId_conn(id_conn);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetRol() {
        System.out.println("getRol");
        Connection instance = new Connection();
        int expResult = 0;
        int result = instance.getRol();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetRol() {
        System.out.println("setRol");
        int rol = 0;
        Connection instance = new Connection();
        instance.setRol(rol);
        fail("The test case is a prototype.");
    }

    @Test
    public void testLogin() {
        System.out.println("login");
        Connection instance = new Connection();
        instance.login();
        fail("The test case is a prototype.");
    }

    @Test
    public void testLogout() {
        System.out.println("logout");
        int idconn = 0;
        Connection instance = new Connection();
        instance.logout(idconn);
        fail("The test case is a prototype.");
    }
    
}
