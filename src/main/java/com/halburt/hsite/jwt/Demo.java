package com.halburt.hsite.jwt;

import java.security.Key;
import java.security.SignatureException;

import org.apache.commons.codec.binary.Base64;

import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.utils.IdGen;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
public class Demo {
	
	public static void main(String[] args) throws  Exception {test1();
		JSONObject json = new JSONObject();
//		json.put("id", "哈哈");
//		json.put("name", "张三哈ahs");
//		json.put("id1", "哈哈1");
//		json.put("name1", "张三哈ah1s");
		
		String token = TokenMgr.createJWT(IdGen.uuid(), json.toString(), 2000);
		System.out.println(token);
		System.out.println();
		/*String[] arr = token.split("\\."); 
		System.err.println(arr[1]);
		System.out.println();*/
//		TokenMgr.generalSubject(sub)
		System.out.println(TokenMgr.validateJWT(token));
		try {
			System.out.println(TokenMgr.parseJWT(  token));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(2000);
		System.out.println(TokenMgr.validateJWT(token));
		try {
			System.out.println(TokenMgr.parseJWT(  token));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static void test1() throws SignatureException {
		// JHL Auto-generated method stub
		JSONObject json = new JSONObject();
		json.put("id", "哈哈");
		json.put("name", "张三哈ahs");
		json.put("id1", "哈哈1");
		json.put("name1", "张三哈ah1s");

		// We need a signing key, so we'll create one just for this example. Usually
		// the key would be read from your application configuration instead.
		Key key2 = MacProvider.generateKey();
		System.out.println("key2="+key2.getAlgorithm());

//		加密数据
		String compactJws2 = Jwts.builder()
		  .setSubject(json.toJSONString())
		  .compressWith(CompressionCodecs.DEFLATE)
		  .signWith(SignatureAlgorithm.HS512, key2)
		  .compact();
		System.out.println(compactJws2);
		
//		解析数据 (Claims继承了Map)
		Claims   claims = Jwts.parser().setSigningKey(key2).parseClaimsJws(compactJws2).getBody();
		System.out.println(claims);;
	}

}
