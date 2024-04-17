package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.Cart;

/**
 * 購物車的Mapper
 */
@Mapper
public interface CartMapper {

	/**
	 * 查詢帳號的購物車內容
	 * @param memberId
	 * @return
	 */
	@Select("select * from car where memberId=#{memberId}")
	List<Cart> queryAll(Integer memberId);

	/**
	 * 結帳時呼叫 刪除帳號購物車內容
	 * @param memberId
	 */
	@Delete("delete from shopcar where memberId=#{memberId}")
	void checkCar(Integer memberId);
}