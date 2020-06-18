package com.shenhufei.service;

public interface SqlAbstaractFactory {
	public <T> Boolean insert(T t) throws Exception;
}
