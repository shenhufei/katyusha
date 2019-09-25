package com.shenhufei.Katyusha.core;

import java.util.concurrent.CountDownLatch;

public class BeforeAfterAroundMethods implements Runnable{
	private CountDownLatch downLatch;
	 
    public BeforeAfterAroundMethods(CountDownLatch downLatch){
        this.downLatch = downLatch;
    }

	@Override
	public void run() {
		
		
	}

}
