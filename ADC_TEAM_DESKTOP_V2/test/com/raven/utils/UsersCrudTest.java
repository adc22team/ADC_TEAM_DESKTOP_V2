/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.raven.utils;

import javax.swing.JTable;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aigardo
 */
public class UsersCrudTest {
    
    public UsersCrudTest() {
    }

    @Test
    public void testGetId_conn() {
        System.out.println("getId_conn");
        UsersCrud instance = new UsersCrud();
        int expResult = 0;
        int result = instance.getId_conn();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetId_conn() {
        System.out.println("setId_conn");
        int id_conn = 0;
        UsersCrud instance = new UsersCrud();
        instance.setId_conn(id_conn);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetUser() {
        System.out.println("getUser");
        UsersCrud instance = new UsersCrud();
        String expResult = "";
        String result = instance.getUser();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetUser() {
        System.out.println("setUser");
        String user = "";
        UsersCrud instance = new UsersCrud();
        instance.setUser(user);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        UsersCrud instance = new UsersCrud();
        String expResult = "";
        String result = instance.getPassword();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "";
        UsersCrud instance = new UsersCrud();
        instance.setPassword(password);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetName() {
        System.out.println("getName");
        UsersCrud instance = new UsersCrud();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        UsersCrud instance = new UsersCrud();
        instance.setName(name);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetLastname() {
        System.out.println("getLastname");
        UsersCrud instance = new UsersCrud();
        String expResult = "";
        String result = instance.getLastname();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetLastname() {
        System.out.println("setLastname");
        String lastname = "";
        UsersCrud instance = new UsersCrud();
        instance.setLastname(lastname);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetDepartment() {
        System.out.println("getDepartment");
        UsersCrud instance = new UsersCrud();
        String expResult = "";
        String result = instance.getDepartment();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetDepartment() {
        System.out.println("setDepartment");
        String department = "";
        UsersCrud instance = new UsersCrud();
        instance.setDepartment(department);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetRole() {
        System.out.println("getRole");
        UsersCrud instance = new UsersCrud();
        String expResult = "";
        String result = instance.getRole();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetRole() {
        System.out.println("setRole");
        String role = "";
        UsersCrud instance = new UsersCrud();
        instance.setRole(role);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetState() {
        System.out.println("getState");
        UsersCrud instance = new UsersCrud();
        String expResult = "";
        String result = instance.getState();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetState() {
        System.out.println("setState");
        String state = "";
        UsersCrud instance = new UsersCrud();
        instance.setState(state);
        fail("The test case is a prototype.");
    }

    @Test
    public void testListUsers() {
        System.out.println("listUsers");
        JTable tableListUsers = null;
        UsersCrud instance = new UsersCrud();
        instance.listUsers(tableListUsers);
        fail("The test case is a prototype.");
    }

    @Test
    public void testListUsersAdmin() {
        System.out.println("listUsersAdmin");
        JTable tableListUsers = null;
        UsersCrud instance = new UsersCrud();
        instance.listUsersAdmin(tableListUsers);
        fail("The test case is a prototype.");
    }

    @Test
    public void testListUsersTecnics() {
        System.out.println("listUsersTecnics");
        JTable tableListUsers = null;
        UsersCrud instance = new UsersCrud();
        instance.listUsersTecnics(tableListUsers);
        fail("The test case is a prototype.");
    }

    @Test
    public void testListUsersUsuaris() {
        System.out.println("listUsersUsuaris");
        JTable tableListUsers = null;
        UsersCrud instance = new UsersCrud();
        instance.listUsersUsuaris(tableListUsers);
        fail("The test case is a prototype.");
    }

    @Test
    public void testFiltraryRefrescar() {
        System.out.println("filtraryRefrescar");
        JTable tableListUsers = null;
        UsersCrud instance = new UsersCrud();
        instance.filtraryRefrescar(tableListUsers);
        fail("The test case is a prototype.");
    }

    @Test
    public void testDeleteUsers() {
        System.out.println("deleteUsers");
        UsersCrud instance = new UsersCrud();
        instance.deleteUsers();
        fail("The test case is a prototype.");
    }

    @Test
    public void testAddUsers() {
        System.out.println("addUsers");
        UsersCrud instance = new UsersCrud();
        boolean expResult = false;
        boolean result = instance.addUsers();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testModifyUsers() {
        System.out.println("modifyUsers");
        UsersCrud instance = new UsersCrud();
        boolean expResult = false;
        boolean result = instance.modifyUsers();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
