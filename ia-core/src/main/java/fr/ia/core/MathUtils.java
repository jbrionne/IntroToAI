package fr.ia.core;

import java.math.BigDecimal;

public class MathUtils {
	
	public MathUtils(){
		throw new IllegalAccessError("No instantiation");
	}

	public static boolean equalsApproxBigDecimal(BigDecimal bigDec1,
			BigDecimal bigDec2) {
		return bigDec1.subtract(bigDec2).abs().compareTo(new BigDecimal(0.002)) == -1;
	}
}
