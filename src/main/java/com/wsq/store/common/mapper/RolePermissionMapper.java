package com.wsq.store.common.mapper;

import com.wsq.store.common.domain.user.RolePermission;

public interface RolePermissionMapper {
    int deleteByPrimaryKey(Long FId);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    RolePermission selectByPrimaryKey(Long FId);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);
}