package com.shenhufei.Katyusha.core;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class MethodMap implements Runnable{
	 private CountDownLatch downLatch;
     private String name;

     public MethodMap(CountDownLatch downLatch, String name){
         this.downLatch = downLatch;
         this.name = name;
     }

	@Override
	public void run() {
		 //TODO 需要加自己的数据
		List<Class<?>> list = VersionHandler.list;
		List<String> listString = VersionHandler.getListString();
		 this.downLatch.countDown();
	}

}
