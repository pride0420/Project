package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.vo.LoginRequest;
import com.example.demo.vo.Member;

/**
 * 帳戶的Mapper
 */
@Mapper
public interface MemberMapper {

	/**
	 * 新增帳戶
	 * @param m
	 */
	@Insert("insert into test.member(username,password,name,email,phone,role) "
			+ "values(#{username},#{password},#{name},#{email},#{phone},#{role})")
	void addMember(Member m);

	/**
	 * 查詢帳號、密碼
	 * @param m
	 * @return
	 */
	@Select("select * from member where username=#{username} and password=#{password}")
	Member queryUser(Member m);

	/**
	 * 查詢帳號
	 * @param m
	 * @return
	 */
	@Select("select * from member where username=#{username}")
	LoginRequest queryUserName(LoginRequest m);

	/**
	 * 查詢帳號
	 * @param m
	 * @return
	 */
	@Select("select * from member where username=#{username}")
	Member queryUserNameForUpdate(Member m);
	
	/**
	 * 查詢全部帳號
	 */
	@Select("select * from member")
	List<Member> queryAllMember();
	
	/**
	 * 查詢帳號 security使用
	 * @param username
	 * @return
	 */
	@Select("select * from member where username=#{username}")
	Member queryMemberUsername(String username);
	
	@Select("select * from member where username=#{username} and email=#{email}")
	Member queryUserAndEmailTest(Member m);
	/**
	 * 更新帳號
	 * @param m
	 */
	@Update("update member set name=#{name},phone=#{phone},email=#{email} where memberId=#{memberId}")
	void updateMember(Member m);
	
	@Update("update member set password=#{password} where memberId=#{memberId}")
	void updatePassword(Member m);

	/**
	 * 刪除帳號
	 * @param memberId
	 */
	@Delete("delete from member where memberId=#{memberId}")
	void deleteMember(Integer memberId);
}
