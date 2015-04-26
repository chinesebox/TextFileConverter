/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loiane.dao;

import com.loiane.mapper.ContactMapper;
import com.loiane.model.Contact;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 *
 * @author ocean
 */
public class AbstractDAO<T, L> {

    private SqlSessionFactory sqlSessionFactory;

    private Class clazz;

    public AbstractDAO() {
	sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
    }

    public List<Contact> selectOne() {

	SqlSession session = sqlSessionFactory.openSession();

	try {

	    ContactMapper mapper = session.getMapper(ContactMapper.class);
	    List<Contact> list = mapper.selectAll();

	    return list;
	} finally {
	    session.close();
	}
    }

}
