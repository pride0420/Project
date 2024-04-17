package com.example.demo.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.vo.Shop;

/**
 * 商城的Mapper
 */
@Mapper
public interface ShopMapper {

	/**
	 * 新增商品到購物車
	 * @param s
	 */
	@Insert("insert into shopcar(memberId,porderId,num,sum) values(#{memberId},#{porderId},#{num},#{sum})")
	void addShop(Shop s);

	/**
	 * 查詢帳號內有無該商品
	 * @param porderId
	 * @param memberId
	 * @return
	 */
	@Select("select * from shopcar where porderId=#{porderId} and memberId=#{memberId}")
	Shop queryPorderId(Integer porderId,Integer memberId);

	/**
	 * 查詢儲存商品的總金額
	 * @param memberId
	 * @return
	 */
	@Select("select sum(sum) from test.shopcar where memberId=#{memberId}")
	Integer querySum(Integer memberId);

	/**
	 * 查詢單筆資料
	 * @param id
	 * @return
	 */
	@Select("select * from shopcar where id=#{id}")
	Shop queryShopId(Integer id);

	/**
	 * 更新購物車商品數量及金額
	 * @param s
	 */
	@Update("update shopcar set num=#{num},sum=#{sum} where id=#{id}")
	void updateShop(Shop s);

	/**
	 * 刪除內容
	 * @param Id
	 */
	@Delete("delete from shopcar where id=#{id}")
	void deleteShop(Integer Id);
}
