package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.Porder;

/**
 * 商品的Mapper
 */
@Mapper
public interface PorderMapper {

	/**
	 * 查詢特定條件的商品
	 * @return
	 */
	@Select("select * from porder where porderId=1 or porderId=15 or porderId=18 or porderId=20")
	List<Porder> queryId();

	/**
	 * 用類別查詢商品
	 * @param items
	 * @return
	 */
	@Select("select * from porder where items=#{items}")
	List<Porder> queryItems(String items);

	/**
	 * 查詢單個商品
	 * @param porderId
	 * @return
	 */
	@Select("select * from porder where porderId=#{porderId}")
	Porder query(Integer porderId);

}
