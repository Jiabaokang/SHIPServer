package com.ship.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jbk.springmybatis.entity.Clazz;

import java.util.List;

//@Mapper
//@Repository
public interface IClazzDao extends BaseMapper<Clazz> {
    List<Clazz> select();
}
