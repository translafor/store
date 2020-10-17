package com.wsq.store.common.mapper;

import com.wsq.store.common.domain.user.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long FId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long FId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}