package com.example.demo.service.Impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.demo.vo.LoginRequest;
import com.example.demo.vo.LoginResponse;
import com.example.demo.vo.Member;

@Service
public class AppService {

	private final static Logger log = LoggerFactory.getLogger(Member.class);

	private final RestTemplate restTemplate;

	@Autowired
	public MemberServiceImpl ms;

	@Autowired
	public AppService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public AppService() {
		this.restTemplate = null; // 或者进行其他默认初始化操作
	}

	/**
	 * 登入
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
		LoginRequest m = ms.queryUserName(request);
		m.setPassword(request.getPassword());
		if (m != null) {
			ResponseEntity<LoginResponse> response = restTemplate.postForEntity(
					"http://localhost:8090/member2/login", // URL
					m, // 请求体
					LoginResponse.class // 响应的数据类型
			);
			if (response.getStatusCode() == HttpStatus.OK) {
				LoginResponse user = response.getBody();
				return ResponseEntity.ok(user);
			} else {
				return ResponseEntity.status(response.getStatusCode()).build();
			}
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

	}

	/**
	 * 查詢全部
	 * 
	 * @param token
	 * @return
	 */
	public ResponseEntity<?> queryAll(String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token);
		HttpEntity<String> entity = new HttpEntity<>("paremeters", headers);
		try {
			ResponseEntity<?> response = restTemplate.exchange(
					"http://localhost:8090/member2/queryAll", // URL
					HttpMethod.GET, 
					entity, 
					String.class // 响应的数据类型
			);
			// 处理响应
			if (response.getStatusCode() == HttpStatus.OK) {
				List<Member> m = null;
				if (response.getBody().equals("admin")) {
					m = ms.queryAllMember();
				} else if (response.getBody().equals("user")) {
					m = ms.queryOnlyUser();
				}
				return ResponseEntity.ok(m);
			} else {
				return ResponseEntity.status(response.getStatusCode()).build();
			}
		} catch (HttpClientErrorException e) {
			// 捕获异常并处理
			if (e.getStatusCode() == HttpStatus.FORBIDDEN) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
			} else {
				// 其他异常处理
				return ResponseEntity.status(e.getStatusCode()).build();
			}
		}
	}

	/**
	 * 更新密碼
	 * 
	 * @param m
	 * @return
	 */
	public ResponseEntity<?> updatePassword(Member m) {
		ResponseEntity<?> response = restTemplate.postForEntity(
				"http://localhost:8090/member2/updatePassword", // URL
				m, // 请求体
				Boolean.class // 响应的数据类型
		);
		if (response.getStatusCode() == HttpStatus.OK) {
			return ResponseEntity.ok(response.getBody());
		} else {
			return ResponseEntity.status(response.getStatusCode()).build();
		}
	}

	/**
	 * 新增帳號
	 * 
	 * @param m
	 * @return
	 */
	public ResponseEntity<?> addMember(Member m) {
		ResponseEntity<?> response = restTemplate.postForEntity(
				"http://localhost:8090/member2/addMember", // URL
				m, // 请求体
				Boolean.class // 响应的数据类型
		);
		if (response.getStatusCode() == HttpStatus.OK) {
			return ResponseEntity.ok(response.getBody());
		} else {
			return ResponseEntity.status(response.getStatusCode()).build();
		}
	}

	/**
	 * 忘記密碼
	 * 
	 * @param m
	 * @return
	 */
	public ResponseEntity<?> forgetPassword(Member m) {
		ResponseEntity<?> response = restTemplate.postForEntity(
				"http://localhost:8090/member2/forget", // URL
				m, // 请求体
				String.class // 响应的数据类型
		);
		if (response.getStatusCode() == HttpStatus.OK) {
			return ResponseEntity.ok(response.getBody());
		} else {
			return ResponseEntity.status(response.getStatusCode()).build();
		}
	}

}
