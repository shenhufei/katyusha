package springbootmybatis.bean;

import java.io.Serializable;

import lombok.Data;
@Data
public class User implements Serializable{
    /**
	 * TODO
	 */
	private static final long serialVersionUID = -1630224837804371582L;

	private Integer id;
	
    private String name;
    private Integer age;
}
