package com.example.demo.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.GoodsChatMapper;
import com.example.demo.service.GoodsChatService;
import com.example.demo.vo.Chat;

/**貼文按讚的實作
 */
@Service
public class GoodsChatServiceImpl implements GoodsChatService {

	@Autowired
	private GoodsChatMapper gchm;

	/**
	 * 貼文按讚或取消
	 */
	@Override
	public String addGoodChat(Chat c) {
		Chat ch = gchm.queryGoodId(c);
		if (ch == null) {
			gchm.addGoodChat(c);
			return "已按讚";
		} else {
			gchm.deleteGoodChat(ch.getId());
			return "已取消";
		}
	}

	
	

}
