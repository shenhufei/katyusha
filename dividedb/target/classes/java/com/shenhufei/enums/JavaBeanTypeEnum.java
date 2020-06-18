package com.shenhufei.enums;
/**
 *   
 * 
 * @Description: 数据库字段对应java实体类对应的字段数据类型
 * @author shenhufei  
 * @date 2019年4月30日 下午2:08:07 
 * @version V1.0   
 */
public enum JavaBeanTypeEnum {
	  longE("long"),LongE("Long"), doubleE("double"),DoubleE("Double"), floatE("float"),FloatE("Float"),
	  inte("int"),IntegerE("Integer"),shortE("short"),ShortE("Short"),bytes("byte"),Bytes("Byte"),booleanE("boolean"),BooleanE("Boolean"),
	  CharacterE("Character"), stringE("String"),DateE("Date"),bytesArray("byte[]"),BigDecimalE("DECIMAL"),
	  tinyint("tinyint");
    //todo byte[]   BLOB    ;BigDecimal ,DECIMAL;  Date,YEAR
	    private String type;

	    private JavaBeanTypeEnum(String type) {
	       
	        this.type = type;
	    }

	    JavaBeanTypeEnum() {
	    }

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

	    
}
