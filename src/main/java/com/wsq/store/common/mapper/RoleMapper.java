package com.wsq.store.common.mapper;

import com.wsq.store.common.domain.user.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Long FId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long FId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}