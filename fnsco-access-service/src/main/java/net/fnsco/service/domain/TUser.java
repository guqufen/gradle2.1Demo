/**
 * 
 */
package net.fnsco.service.domain;

import java.io.Serializable;

/**@desc 
 * @author tangliang
 * @date 2017年6月19日 上午11:49:58
 */
public class TUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4583253290194202301L;

	
	private int id;
	
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
