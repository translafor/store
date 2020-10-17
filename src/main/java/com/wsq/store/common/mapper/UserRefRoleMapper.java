package com.wsq.store.common.mapper;

import com.wsq.store.common.domain.user.UserRefRole;

public interface UserRefRoleMapper {
    int deleteByPrimaryKey(Long FId);

    int insert(UserRefRole record);

    int insertSelective(UserRefRole record);

    UserRefRole selectByPrimaryKey(Long FId);

    int updateByPrimaryKeySelective(UserRefRole record);

    int updateByPrimaryKey(UserRefRole record);
}