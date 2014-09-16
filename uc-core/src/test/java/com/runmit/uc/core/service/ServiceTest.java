package com.runmit.uc.core.service;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.runmit.uc.core.domain.*;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.Assert.*;

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

    @Autowired
    private AdminService adminService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private DeviceInfoService deviceInfoService;
    @Autowired
    private UserDeviceRelaService userDeviceRelaService;
    @Autowired
    private UserDeviceRelaDelService userDeviceRelaDelService;

    @Before
    public void initDbunit() throws Exception {
        //Load mock data into DB
        IDatabaseConnection connection = new DatabaseConnection(DataSourceUtils.getConnection(dataSource));
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(new ClassPathResource("mock-data.xml").getFile());
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
        System.setProperty("memcached.properties.filename", "memcached_test.properties");
    }

    @Test
    public void testUser() {
        UserInfo ui= new UserInfo();
        ui.setId(9999);
        ui.setUserid("user_test");
        ui.setPassword("c4aecd7a22b3eeb1190ac1b616e1ac63");
        ui.setCreateby("user_create");
        ui.setUpdateby("user_update");
        ui.setUpdatetime("1970-10-10 10:00:00");
        userInfoService.deleteUser(ui.getId());//先删一遍,防止存在未删除的测试数据
        assertNull(userInfoService.getUserInfo(ui.getId()));//确认数据库和缓存均不存在
        userInfoService.addUserInfo(ui);
        assertNotNull(userInfoService.getUserInfo(ui.getId()));//确认新增数据成功
        ui.setUpdateby("test_update");
        userInfoService.updateUser(ui);
        assertEquals(userInfoService.getUserInfo(ui.getId()).getUpdateby(),"test_update");//确认修改数据成功,且刷新了缓存
        userInfoService.deleteUser(ui.getId());//清空测试数据
        assertNull(userInfoService.getUserInfo(ui.getId()));//确认测试数据在数据库和缓存均不存在
    }

    @Test
    public void testUserDevice() {
        UserDeviceRela udr=new UserDeviceRela();
        udr.setId(9999);
        udr.setDevicesn("devicesn_test");
        udr.setDevicetype("1");
        udr.setToken("c4aecd7a22b3eeb1190ac1b616e1ac63");
        userDeviceRelaService.deleteUserDeviceRela(udr.getId(),udr.getDevicesn());//先删一遍,防止存在未删除的测试数据
        assertNull(userDeviceRelaService.getUserDeviceRela(udr.getId(),udr.getDevicesn()));//确认数据库和缓存均不存在
        userDeviceRelaService.addUserDeviceRela(udr);
        assertNotNull(userDeviceRelaService.getUserDeviceRela(udr.getId(),udr.getDevicesn()));//确认新增数据成功
        udr.setToken("test_token");
        userDeviceRelaService.updateUserDeviceRela(udr);
        assertEquals(userDeviceRelaService.getUserDeviceRela(udr.getId(),udr.getDevicesn()).getToken(),"test_token");//确认修改数据成功,且刷新了缓存
        userDeviceRelaService.deleteUserDeviceRela(udr.getId(),udr.getDevicesn());//清空测试数据
        assertNull(userDeviceRelaService.getUserDeviceRela(udr.getId(),udr.getDevicesn()));//确认测试数据在数据库和缓存均不存在
    }

    @Test
    public void testUserDeviceDel() {
        UserDeviceRelaDel udr= new UserDeviceRelaDel();
        udr.setId(9999);
        udr.setDevicesn("devicesn_test");
        udr.setStatus("ACTIVE");
        userDeviceRelaDelService.deleteUserDeviceRelaDel(udr.getId(),udr.getDevicesn());//先删一遍,防止存在未删除的测试数据
        assertTrue(userDeviceRelaDelService.getUserDeviceRelaDel(udr.getId(),udr.getDevicesn()).size()==0);//确认数据库和缓存均不存在
        int delid=userDeviceRelaDelService.addUserDeviceRelaDel(udr);
        assertTrue(delid>0);
        assertTrue(userDeviceRelaDelService.getUserDeviceRelaDel(udr.getId(),udr.getDevicesn()).size()==1);//确认新增数据成功
        assertTrue(userDeviceRelaDelService.getUserDeviceRelaDel(udr.getId(),udr.getDevicesn()).size()==1);//确认新增数据成功
        udr.setStatus("test_status");
        userDeviceRelaDelService.updateUserDeviceRelaDel(udr);
        assertEquals(userDeviceRelaDelService.getUserDeviceRelaDel(udr.getId(),udr.getDevicesn()).get(0).getStatus(),"test_status");//确认修改数据成功,且刷新了缓存
        userDeviceRelaDelService.deleteUserDeviceRelaDel(udr.getId(),udr.getDevicesn());//清空测试数据
        assertTrue(userDeviceRelaDelService.getUserDeviceRelaDel(udr.getId(),udr.getDevicesn()).size()==0);//确认测试数据在数据库和缓存均不存在
    }

    @Test
    public void testDevice() {
        List<DeviceInfo> ldi=deviceInfoService.getAllDeviceInfo();
        deviceInfoService.addDeviceRegUser("abc");
    }

//-----------------------------------以下测试尚未完成-----------------------------------
    @Test
    public void testGet() {
        Integer id = 1;
        Admin result = adminService.getAdmin(id);
        assertEquals("Peter", result.getRealname());
        assertEquals("523cc8202b0bc93d019aceaf3dc4393a", result.getPassword());
    }

    @Test
    public void testTemp() {
//        UserDeviceRela udr=userDeviceRelaService.getUserDeviceRela(297,"devicehwid1038");
//        udr.getToken();
//        System.out.println("dur");
    }

//    @Test
//    public void testUserInfo() {
//        List<UserInfo> uis=userInfoService.getAllUserInfo();
//        assertNotNull(uis);
//        UserInfo ui=userInfoService.getUserInfo(2);
//        ui.setUpdateby("abcedef");
//        userInfoService.updateUser(ui);
//        assertEquals(userInfoService.getUserInfo(2).getUpdateby(),"abcedef");
//        UserInfo insertui=new UserInfo();
//        insertui.setUserid("panydsfs@163.com");
//        insertui.setPassword("fdsfs23423");
//        insertui.setCreateby("fsdfds");
//        insertui.setUpdateby("fsfsdfsd");
//        int a = userInfoService.addUserInfo(insertui);
//        assertEquals(a,Integer.MIN_VALUE);
//    }

    @Test
    public void testInsert() {
        Admin adminEntity = new Admin();
        adminEntity.setRealname("Mike");
        adminEntity.setPassword("poor-pswd");
        adminEntity.setUsername("mike");
        adminEntity.setEmail("mike@some.com");
        adminEntity.setPhone("1234534444");
        adminEntity.setQq("1222222");
        adminEntity.setType("2");
        adminEntity.setAuth("2");
        int id = adminService.addUser(adminEntity);
        System.out.println(id);
        System.out.println(adminEntity.getId());
        assertNotNull(adminEntity.getId());
    }

    @Test
    public void testDeviced() {
        DeviceInfo deviceInfo=deviceInfoService.getbysn("abc");
//        UserInfo userInfo=userInfoService.getUserInfo(43);
        assertNotNull(deviceInfo);
//        List<UserDeviceRela> udr=userDeviceRelaService.getLoginbyId(102);
//        assertNotNull(udr);
//        UserDeviceRela userDeviceRela=userDeviceRelaService.getRelabyId(96).get(0);
//        UserDeviceRela userDeviceRela1=userDeviceRelaService.getUserDeviceRela(userDeviceRela.getId(),userDeviceRela.getDevicesn());
//        UserDeviceRela userDeviceRela3=userDeviceRelaService.getUserDeviceRela(userDeviceRela.getId(),userDeviceRela.getDevicesn());
//        userDeviceRela.setToken("abc");
//        userDeviceRelaService.updateUserDeviceRela(userDeviceRela);
//        UserDeviceRela userDeviceRela2=userDeviceRelaService.getUserDeviceRela(userDeviceRela.getId(),userDeviceRela.getDevicesn());
//        System.out.println("sfsd");

    }
}
