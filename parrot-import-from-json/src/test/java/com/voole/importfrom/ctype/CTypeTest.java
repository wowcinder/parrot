/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package com.voole.importfrom.ctype;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.voole.parrot.importfrom.ctype.CtypeColumnTypeAdapter;
import com.voole.parrot.importfrom.ctype.TopCtype;

/**
 * @author XuehuiHe
 * @date 2013年12月27日
 */
// @RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(locations = "classpath:/spring-test.xml")
public class CTypeTest {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapterFactory(CtypeColumnTypeAdapter.FACTORY);
		Gson gson = gb.create();
		List<TopCtype> list = (List<TopCtype>) gson.fromJson(read(),
				new TypeToken<List<TopCtype>>() {
				}.getType());

		GsonBuilder gb2 = new GsonBuilder();
		gb2.setPrettyPrinting();
		System.out.println(gb2.create().toJson(list));

	}

	public static String read() throws IOException {
		InputStream in = CTypeTest.class.getClassLoader().getResourceAsStream(
				"c.json");

		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		reader.close();
		return sb.toString();

	}

}
