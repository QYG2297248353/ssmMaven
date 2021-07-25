package com.ms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.mapper.CategoryMapper;
import com.ms.pojo.Category;
import com.ms.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryMapper categoryMapper;
	
	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		return categoryMapper.list();
	}

	@Override
	public void add(Category category) {
		// TODO Auto-generated method stub
		categoryMapper.add(category);
	}

	@Override
	public Category get(int id) {
		// TODO Auto-generated method stub
		return categoryMapper.get(id);
	}

	@Override
	public void update(Category category) {
		// TODO Auto-generated method stub
		categoryMapper.update(category);
	}

	@Override
	public void delete(Category category) {
		// TODO Auto-generated method stub
		categoryMapper.delete(category.getId());
	}

	@Override
//	@Transactional(propagation = Propagation.REQUIRED,rollbackForClassName = "Exception")
	public void addTwo() {
		// TODO Auto-generated method stub
		Category c1 = new Category();
		c1.setName("字数较短");
		categoryMapper.add(c1);
		
		Category c2 = new Category();
		c2.setName("字数太长了，名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,"
				+ "名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下名字长对应字段放不下,"
				+ "名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下");
		categoryMapper.add(c2);
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		List<Category> cs=list();
		for (Category c : cs) {
			categoryMapper.delete(c.getId());
		}
	}
	
}
 