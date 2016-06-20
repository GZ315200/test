/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.example.test.dao.factory;

import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.example.test.entity.factory.Product;

/**
 * 产品DAO接口
 * @author ThinkGem
 * @version 2016-06-05
 */
@Repository
public class ProductDao extends BaseDao<Product> {
	
}
