package jp.co.sss.shop.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sss.shop.entity.ItemsWithCategories;
import jp.co.sss.shop.repository.ItemsWithCategoriesRepository;


@Controller
public class ItemsWithCategoriesController {
	@Autowired
	ItemsWithCategoriesRepository repository;
	
	@Autowired
	 EntityManager entityManager;


	@RequestMapping("/items/findItemAndCategory")
	public String showItemAndCategoryList(Model model) {
		model.addAttribute("items", repository.findAll());
		return "items/item_category_list";
	}

//	@RequestMapping("/items/searchByCategoryId/{categoryId}")
//	 public String searchByCategoryId(@PathVariable int categoryId, Model model) {
//	 //参照先テーブルに対応付けられたエンティティ Category のオブジェクトを生成
//	 Category category = new Category();
//	 //Category のオブジェクト内の id フィールドにパラメータの値を代入
//	 category.setId(categoryId);
//	 //Category のオブジェクト内の id フィールドを使用した条件検索を実行
//	 List<ItemsWithCategories> items = repository.findByCategory(category);
//	 //検索結果をリクエストスコープに保存
//	 model.addAttribute("items", items);
//	 //商品一覧画面に遷移
//	 return "items/item_category_list";
//	 }
	
	//NamedQueryアノテーションを利用したJPQL
	@RequestMapping("/items/searchWithNamedQuery/{id}")
	public String searchWithNamedQuery(@PathVariable int id, Model model) {
	Query query = entityManager.createNamedQuery("findByIdNamedQuery");
	query.setParameter("id", id);
	List<ItemsWithCategories> items =
	(List<ItemsWithCategories>) query.getResultList();
	model.addAttribute("items", items);
	return "items/item_category_list";
	}
	
	//Queryアノテーションを利用したJPQL
	
	@RequestMapping("/items/searchWithQuery")
	 public String searchWithQuery(Model model) {
	model.addAttribute("items", repository.findByPriceGreaterThanEqualAVGPriceQuery());
	return"items/item_category_list";
	}




}