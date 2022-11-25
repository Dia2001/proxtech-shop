package com.proxtechshop.enums;

public enum RateEnum {

	one_star(1),  
    two_star(2),  
    three_star(3),
    four_star(4),
	five_star(5);
    private final double starValue;

    RateEnum(double starValue) {
        this.starValue = starValue;
    }

	public double getStartValue() { 
		return starValue;//final variable should have only getter
	}
	
}
