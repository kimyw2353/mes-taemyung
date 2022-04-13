package com.mes.dao;

import com.mes.entity.User;

import java.util.Date;

public class UserDao extends JpaDao<User> implements GenericDao<User> {
    public UserDao() {
    }

    @Override
    public User create(User user) {

        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        return super.create(user);
    }

    @Override
    public User find(Object id)  {
        return super.find(User.class, id);
    }

    @Override
    public User update(User user){
        user.setUpdatedAt(new Date());
        return super.update(user);
    }

    @Override
    public void delete(Object id) {
        super.delete(User.class, id);
    }
}
