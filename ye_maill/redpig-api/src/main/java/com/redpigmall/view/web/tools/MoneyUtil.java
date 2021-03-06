package com.redpigmall.view.web.tools;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.redpigmall.api.tools.CommUtil;

/**
 * <p>
 * Title: MoneyUtil.java
 * </p>
 * 
 * <p>
 * Description:针对钱的加减乘除操作
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * 
 * <p>
 * Company: www.redpigmall.net
 * </p>
 * 
 * @author redpig
 * 
 * @date 2017-3-21
 * 
 * @version redpigmall_b2b2c v8.0 2016版
 */
public class MoneyUtil {
	public String getFormatMoney(Object money, int scale, double divisor) {
		if (divisor == 0.0D) {
			return "0.00";
		}
		if (scale < 0) {
			return "0.00";
		}
		BigDecimal moneyBD = new BigDecimal(CommUtil.null2Double(money));
		BigDecimal divisorBD = new BigDecimal(divisor);

		return moneyBD.divide(divisorBD, scale, RoundingMode.HALF_UP)
				.toString();
	}

	public String getAccountantMoney(Object money, int scale, double divisor) {
		String disposeMoneyStr = getFormatMoney(money, scale, divisor);

		int dotPosition = disposeMoneyStr.indexOf(".");
		String exceptDotMoeny = null;
		String dotMeony = null;
		if (dotPosition > 0) {
			exceptDotMoeny = disposeMoneyStr.substring(0, dotPosition);
			dotMeony = disposeMoneyStr.substring(dotPosition);
		} else {
			exceptDotMoeny = disposeMoneyStr;
		}
		int negativePosition = exceptDotMoeny.indexOf("-");
		if (negativePosition == 0) {
			exceptDotMoeny = exceptDotMoeny.substring(1);
		}
		StringBuffer reverseExceptDotMoney = new StringBuffer(exceptDotMoeny);
		reverseExceptDotMoney.reverse();

		char[] moneyChar = reverseExceptDotMoney.toString().toCharArray();
		StringBuffer returnMeony = new StringBuffer();
		for (int i = 0; i < moneyChar.length; i++) {
			if ((i != 0) && (i % 3 == 0)) {
				returnMeony.append(",");
			}
			returnMeony.append(moneyChar[i]);
		}
		returnMeony.reverse();
		if (dotPosition > 0) {
			returnMeony.append(dotMeony);
		}
		if (negativePosition == 0) {
			return "-" + returnMeony.toString();
		}
		return returnMeony.toString();
	}

	public static void testmai(String[] args) {
		BigDecimal money = BigDecimal.valueOf(8.52145218E7D);
		int scale = 2;
		double divisor = 10000.0D;
		System.out.println("原货币值: " + money);
		MoneyUtil util = new MoneyUtil();
		
		String formatMeony = util.getFormatMoney(money, scale, divisor);
		System.out.println("格式化货币值: " + formatMeony + "万元");
		String accountantMoney = util.getAccountantMoney(money, scale, divisor);
		System.out.println("会计货币值: " + accountantMoney + "万元");
	}
}
