package com.jonm.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.jonm.entity.User;

/**
 * @Description: 用户持久层接口
 * @Author: Naccl
 * @Date: 2020-07-19
 */
@Mapper
@Repository
public interface UserMapper {
	User findByUsername(String username);
}
