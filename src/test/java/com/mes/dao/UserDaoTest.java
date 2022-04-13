package com.mes.dao;

import com.mes.entity.User;
import com.mes.util.HashGenerator;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.junit.Test;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class UserDaoTest {
    private UserDao userDao = new UserDao();

    @Test
    public void createUserDao() {
        String userId = "bbnnmmvv33";
        String name = "장태규";
        String password = "cosmos";
        String md5Password = HashGenerator.generateMD5(password);

        User user = new User();
        user.setUserId(userId);
        user.setName(name);
        user.setPassword(md5Password);

        User createdUser = userDao.create(user);

        assertTrue(createdUser instanceof User);
        assertEquals(user.getId(), createdUser.getId());
        assertEquals(userId, createdUser.getUserId());
        assertEquals(name, createdUser.getName());
        assertEquals(md5Password, createdUser.getPassword());
    }

    @Test
    public void findUserDao() {
        Integer id = 1;
        User user = userDao.find(id);

        System.out.println(user.toString());

        assertTrue(user instanceof User);
        assertEquals(id, user.getId());
    }

    @Test
    public void updateUserDao(){
        Integer id = 14;
        User user = userDao.find(id);

        String userId = "userId_edited";
        String name = "김예원 바보";
        String password = "qlqjs";
        String md5Password = HashGenerator.generateMD5(password);

        user.setId(id);
        user.setUserId(userId);
        user.setName(name);
        user.setPassword(md5Password);

        User updatedUser = userDao.update(user);

        System.out.println(updatedUser.toString());

        assertTrue(updatedUser instanceof User);
        assertEquals(id, updatedUser.getId());
        assertEquals(userId, updatedUser.getUserId());
        assertEquals(name, updatedUser.getName());
        assertEquals(md5Password, updatedUser.getPassword());
    }

    @Test
    public void deleteUserDao() {
        Integer deleteId = 15;
        userDao.delete(deleteId);
        User user = userDao.find(deleteId);

        assertNull(user);
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteFailedUserDao() {
        userDao.delete(999999999);
    }
}