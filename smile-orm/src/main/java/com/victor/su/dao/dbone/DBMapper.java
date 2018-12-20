package com.victor.su.dao.dbone;

import com.victor.su.entity.dbone.DB;
import com.victor.su.entity.dbone.DBKey;

public interface DBMapper {
    int deleteByPrimaryKey(DBKey key);

    int insert(DB record);

    int insertSelective(DB record);

    DB selectByPrimaryKey(DBKey key);

    int updateByPrimaryKeySelective(DB record);

    int updateByPrimaryKey(DB record);
}