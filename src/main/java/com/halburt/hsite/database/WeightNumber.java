package com.halburt.hsite.database;
/**
 * 权重
 * @author Administrator
 *
 */
public class WeightNumber  {
	
	private String key;//主键
	
	private Double weight;//权重值
	
	private String flag   ;//标志
	
	
	public WeightNumber(String key, Double weight, String flag) {
		super();
		this.key = key;
		this.weight = weight;
		this.flag = flag;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}

}
