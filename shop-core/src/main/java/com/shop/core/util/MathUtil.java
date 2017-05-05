package com.shop.core.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtil {
	
	public static BigDecimal setScale(BigDecimal bigDecimal) {
		return bigDecimal.setScale(2, RoundingMode.HALF_UP);
	}
	
}
