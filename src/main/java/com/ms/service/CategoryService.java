package com.ms.service;

import java.util.List;

import com.ms.pojo.Category;

public interface CategoryService {
	
	List<Category> list();
	
	void add(Category category);
	
	Category get(int id);
	
	void update(Category category);
	
	void delete(Category category);
	
	void addTwo();
	
	void deleteAll();
	
}
