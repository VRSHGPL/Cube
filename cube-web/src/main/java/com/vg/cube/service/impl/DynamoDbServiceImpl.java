package com.vg.cube.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vg.cube.dao.PrincipalUserDao;
import com.vg.cube.entity.PrincipalUser;
import com.vg.cube.service.DataCrudService;

@Service
public class DynamoDbServiceImpl extends DataCrudService {

    @Autowired
    private PrincipalUserDao principalUserDao;

    @Override
    public PrincipalUser createUser(PrincipalUser user) {
       return principalUserDao.save(user);
    }
}
