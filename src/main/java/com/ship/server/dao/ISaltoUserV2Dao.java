package com.ship.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ship.server.entity.salto.SaltoDBUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ISaltoUserV2Dao extends BaseMapper<SaltoDBUser> {

    /**
     * 批量插入
     * @param saltoDBUserList
     */
    int insertBatchSomeColumn(List<SaltoDBUser> saltoDBUserList);
}