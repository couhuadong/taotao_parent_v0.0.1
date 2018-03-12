package com.taotao.manager.service.impl;

import com.github.abel533.entity.Example;
import com.github.abel533.mapper.Mapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.manager.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Id;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;



public class BaseServiceImpl<T> implements BaseService<T> {

    private Class clazz;

    public BaseServiceImpl() {

        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        clazz = (Class) type.getActualTypeArguments()[0];
    }

    @Autowired
    private Mapper<T> mapper;

    @Override
    public int save(T t) {
        return mapper.insert(t);
    }

    @Override
    public int saveSelective(T t) {
        return mapper.insertSelective(t);
    }

    @Override
    public int delete(T t) {
        return mapper.delete(t);
    }

    @Override
    public int deleteById(Object t) {
        return mapper.deleteByPrimaryKey(t);
    }

    @Override
    public int deleteByIds(List<Object> ids) {
        Example example = new Example(clazz);
        Example.Criteria criteria = example.createCriteria();

        String key = "";
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType().equals(Id.class)){
                    key = field.getName();
                    break;
                }
                
            }
            if (!"".equals(key.trim())){
                break;
            }

        }

        criteria.andIn(key,ids);

        return mapper.deleteByPrimaryKey(example);
    }

    @Override
    public T getOneById(Object id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public T getOne(T t) {
        return mapper.selectOne(t);
    }

    @Override
    public int update(T t) {
        return mapper.updateByPrimaryKey(t);
    }

    @Override
    public int updateSelective(T t) {
        return mapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public List<T> getList() {
        return mapper.select(null);
    }

    @Override
    public List<T> getPageList(int page, int size) {


        //设置分页条件
        PageHelper.startPage(page,size);


        List<T> ts = mapper.select(null);

        PageInfo<T> pageInfo = new PageInfo<T>(ts);

        //获取分页信息中的总记录数
        pageInfo.getTotal();

        //获取分页信息中的总页数
        pageInfo.getPages();


        return ts;
    }
}
