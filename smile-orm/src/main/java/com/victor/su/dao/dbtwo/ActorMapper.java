package com.victor.su.dao.dbtwo;

import com.victor.su.entity.dbtwo.Actor;

public interface ActorMapper {
    int deleteByPrimaryKey(Short actorId);

    int insert(Actor record);

    int insertSelective(Actor record);

    Actor selectByPrimaryKey(Short actorId);

    int updateByPrimaryKeySelective(Actor record);

    int updateByPrimaryKey(Actor record);
}