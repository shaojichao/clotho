package com.runmit.clotho.core.service;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Junit test,and you can use it to initialize database with mock data too.
 *
 * @author TianLiang
 */
//This is my test for gitlib
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext_test.xml"})
@TransactionConfiguration(defaultRollback = false)
public class ServiceTest {

   /* @Autowired
    private VersionService VersionService;*/
    
    @Test
    public void test(){
    	System.out.println("test");
    }

}
