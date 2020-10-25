package com.wsq.store.common.mapper;

import com.wsq.store.common.domain.user.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long FId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long FId);

    List<User> selectBySelective(User selective);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}