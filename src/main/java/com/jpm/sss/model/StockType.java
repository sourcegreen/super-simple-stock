package com.jpm.sss.model;

public class StockType {
	
	private String typeName;
	
	public StockType() {
	}
	
	public StockType(String typeName) {
		this.setTypeName(typeName);
	}

	public String getTypeName() {
		return typeName;
	}
	
	public void setTypeName(String typeName) {
		if(typeName == null || typeName.isEmpty()) {
			throw new IllegalArgumentException("Stock type name cannot be empty or null");
		}
		this.typeName = typeName;
	}

	public boolean equals(Object obj2) {
		StockType type2 = (StockType) obj2;
		return this.typeName.equals(type2.getTypeName());
	}
	
	public boolean isCommon() {
		return this.typeName.equalsIgnoreCase("Common");
	}
	
	public boolean isPreferred() {
		return this.typeName.equalsIgnoreCase("Preferred");
	}
}
