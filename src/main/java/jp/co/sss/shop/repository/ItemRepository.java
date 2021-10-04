package jp.co.sss.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.shop.entity.Item;


public interface ItemRepository extends JpaRepository<Item, Integer> {
	
	List<Item>findAllByOrderByPriceDesc();
	
	//その他の検索
	List<Item> findByPrice(Integer price);

	//複数列による条件検索
	List<Item> findByNameAndPrice(String name, Integer price);




}
