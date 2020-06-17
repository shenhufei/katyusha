package com.shenhufei.exception;

import lombok.Data;

/**
 *   
 * 
 * @Description: 读取分表配置文件中的实体类全路径时候，如果根据路径拿不到这个
 *  数据库表对应的实体类的时候抛出该一场；
 * @author shenhufei  
 * @date 2019年5月1日 下午21:34:07 
 * @version V1.0   
 */
@Data
public class PojoClassNotFoundException extends ClassNotFoundException{
		/**
	 * 
	 */
	private static final long serialVersionUID = 4101455533850983792L;
		private String message;

		public PojoClassNotFoundException() {
			super();
		}

		public PojoClassNotFoundException(String s, Throwable ex) {
			super(s, ex);
		}

		public PojoClassNotFoundException(String message) {
			super();
			this.message = message;
		}

}
