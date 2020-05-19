package com.wuzheyi.blog.service;

import com.wuzheyi.NotFoundException;
import com.wuzheyi.blog.dao.TypeMapper;
import com.wuzheyi.blog.po.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wuzheyi
 * @version 1.0
 * @date 2020/4/13 10:45
 */
@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    TypeMapper typeMapper;


    @Override
    public int saveType(Type type) {
        return typeMapper.insertSelective(type);
    }


    @Override
    public Type getType(Long id) {
        return typeMapper.selectByPrimaryKey(id);
    }


    @Override
    public List<Type> getAllType() {
        return typeMapper.getAllType();
    }

    @Override
    public int updateType(Type type) {
        Type t = typeMapper.selectByPrimaryKey(type.getId());
        if(t == null){
            throw new NotFoundException("不存在该类型");
        }
        return typeMapper.updateByPrimaryKeySelective(type);
    }



    @Override
    public int deleteType(Long id) {
        return typeMapper.deleteByPrimaryKey(id);
    }


    @Override
    public Type getTypeByName(String name) {
        return typeMapper.getTypeByName(name);
    }

    @Override
    public List<Type> getAdminType() {
        return typeMapper.getAdminType();
    }
}
