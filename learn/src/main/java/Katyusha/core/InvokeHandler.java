package Katyusha.core;

import com.shenhufei.Katyusha.exception.MethodCodeNotFoundException;
import com.shenhufei.Katyusha.exception.MethodNotFoundException;
import com.shenhufei.Katyusha.model.Request;

/**
 *  提供执行的接口
 * @date 2019年9月20日  
 * @version 1.0  
 * @author shenhufei
 */
public interface InvokeHandler {
	
	/**
	 * 提供执行的方法
	 * @author shenhufei
	 * 
	 * @param param
	 * @return
	 * @throws ClassNotFoundException
	 * @throws MethodNotFoundException 
	 * @throws MethodCodeNotFoundException 
	 */
	public Object doHandler(Request param) throws ClassNotFoundException, MethodNotFoundException, MethodCodeNotFoundException;
}
