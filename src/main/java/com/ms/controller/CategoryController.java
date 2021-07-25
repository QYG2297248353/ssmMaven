package com.ms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.pojo.Category;
import com.ms.service.CategoryService;
import com.ms.util.Page;

@Controller
@RequestMapping("")
public class CategoryController {
	@ResponseBody
	@RequestMapping("submitCategory")
	public String submitCategory(@RequestBody Category category) {
		System.out.println("SSM����������ύ��json����ת������"+category);
		return "ok";
	}
	
	@ResponseBody
	@RequestMapping("getOneCategory")
	public String getOneCategory() {
		Category c=new Category();
		c.setId(100);
		c.setName("��һ�ٸ�����");
		JSONObject json =new JSONObject();
		json.put("category", JSONObject.toJSON(c));
		return json.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping("getManyCategory")
	public String getManyCategory() {
		List<Category> cs = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Category c = new Category();
			c.setId(i);
			c.setName("�������ƣ�"+i);
			cs.add(c);
		}
		return JSONObject.toJSON(cs).toString();
	}
	
	
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping(value="/categories/{id}",method=RequestMethod.DELETE)
	public ModelAndView deleteCategory(Category category){
		categoryService.delete(category);
		ModelAndView mav = new ModelAndView("redirect:/categories");
		return mav;
	}
	
	@RequestMapping(value="/categories/{id}",method=RequestMethod.GET)
	public ModelAndView editCategory(Category category){
		Category c= categoryService.get(category.getId());
		ModelAndView mav = new ModelAndView("editCategory");
		mav.addObject("c", c);
		return mav;
	}	
	@RequestMapping(value="/categories/{id}",method=RequestMethod.PUT)
	public ModelAndView updateCategory(Category category){
		categoryService.update(category);
		ModelAndView mav = new ModelAndView("redirect:/categories");
		return mav;
	}	
	
	
	@RequestMapping(value="/categories",method=RequestMethod.POST)
	public ModelAndView addCategory(Category category) {
		categoryService.add(category);
		ModelAndView mav = new ModelAndView("redirect:/categories");
		return mav;
	}

	@RequestMapping(value="/categories",method=RequestMethod.GET)
	public ModelAndView listCategory(Page page) {
//		��ʾ�ж�->���⸺��
		int starpage=page.getStart();
		ModelAndView mav = new ModelAndView();
//		��ҳ->��һҳ
		if (page.getStart()<0) {
			page.setStart(0);
		}
		
		PageHelper.offsetPage(page.getStart(), 5);
		List<Category> cs = categoryService.list();
		int total=(int) new PageInfo<>(cs).getTotal();
		page.caculateLast(total);
		
//		ĩҳ->��һҳ
		if (page.getStart()>=page.getLast()) {
			page.setStart(page.getLast()-5);
		}
//		��ҳ->��һҳ->�����ַ������->?start=-5
//		<=0��Ӱ�쵽��ҳ->������ҳ�����̶�ֵ��Ӱ��
		if (starpage<=0) {
			page.setStart(5);
		}
		mav.addObject("cs", cs);
		mav.setViewName("listCategory");
		return mav;

	}
	
}
