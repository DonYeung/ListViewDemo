package com.drcom.ListViewDemo;

/**
 * 服务类型信息(登录运营商信息)
 * @author zhr
 * **/

public class ServiceKindInfo {
	
	private String name="";
	private boolean isSelected = false;
	private String id="";
	private String suffix = "";
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isSelected() {
		return isSelected;
	}
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
}
