package com.wsq.store.common.mapper;

import com.wsq.store.common.domain.base.Resource;

public interface ResourceMapper {
    int deleteByPrimaryKey(Long FId);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Long FId);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);
}