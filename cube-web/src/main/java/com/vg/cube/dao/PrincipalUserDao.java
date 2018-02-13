package com.vg.cube.dao;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.vg.cube.entity.PrincipalUser;

@EnableScan
public interface PrincipalUserDao extends CrudRepository<PrincipalUser, String> {

    @SuppressWarnings("unchecked")
    PrincipalUser save(PrincipalUser entity);
}
