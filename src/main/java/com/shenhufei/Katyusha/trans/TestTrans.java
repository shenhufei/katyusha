package com.shenhufei.Katyusha.trans;

import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSONArray;

public class TestTrans {

	public static void main(String[] args) {
		Man man = new Man();
		man.setAge(1);
		man.setName("11");
		Student student = new Student();
		BeanUtils.copyProperties(man, student);
		System.out.println(JSONArray.toJSONString(student));
	}

}
