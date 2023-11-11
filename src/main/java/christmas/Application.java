package christmas;

import java.time.*;
import java.util.*;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
    	OutputView outputView = new OutputView();
    	CheckValidate checkValid = new CheckValidate();
    	outputView.printIntro();
    	int day = checkValid.checkValidDate();
    	Map<Menu, Integer> validMenu = new LinkedHashMap<>();
    	validMenu = checkValid.checkValidMenu();
    	outputView.printDate(day);
    	outputView.printMenu(validMenu);
    	int totalBeforeDiscount = calculateTotalPriceBeforeDiscount(validMenu);
    	outputView.printTotalPriceBeforeDiscount(totalBeforeDiscount);
    	boolean giveaway = outputView.printGiveaway(totalBeforeDiscount);
    	int giveawayAmount = calculateGiveawayAmount(giveaway);
    	int christmasDaySaleAmount = checkChristmasDaySale(day);
    	int dayOfWeekNumber = findDayOfWeek(day);
    	int dayOfWeekSaleAmount = checkDayOfWeekSale(dayOfWeekNumber, validMenu);
    	int specialDaySaleAmount = checkSpecialDaySale(dayOfWeekNumber, day);
    	int totalSaleAmount = calculateTotalSaleAmount(christmasDaySaleAmount, dayOfWeekSaleAmount, specialDaySaleAmount, giveawayAmount);
    	outputView.printAllBenefits(christmasDaySaleAmount, dayOfWeekSaleAmount, dayOfWeekNumber, specialDaySaleAmount, giveawayAmount, totalSaleAmount);
    	outputView.printTotalSaleAmount(totalSaleAmount);
    	int totalAfterDiscount = calculateTotalPriceAfterDiscount(totalBeforeDiscount, christmasDaySaleAmount, dayOfWeekSaleAmount, specialDaySaleAmount);
    	outputView.printTotalAfterDiscount(totalAfterDiscount);
    	String eventBadge = checkEventBadge(totalSaleAmount);
    	outputView.printEventBadge(eventBadge);
    }
    //할인 전 총주문 금액 계산 메소드
    private static int calculateTotalPriceBeforeDiscount(Map<Menu, Integer> menu) {
		int total = 0;
		for (Map.Entry<Menu, Integer> entry : menu.entrySet()) {
			total += entry.getKey().getPrice() * entry.getValue();
		}
		return total;
	}
    //크리스마스 디데이 할인 확인 메소드
    private static int checkChristmasDaySale(int day) {
    	int christmasDaySaleAmount = 0;
    	if(day < 26) {
    		christmasDaySaleAmount -= 1000 + (day-1)*100;
    		return christmasDaySaleAmount;
    	}
    	return christmasDaySaleAmount;
    }
    //특정 날짜의 요일 구하기
    private static int findDayOfWeek(int day) {
    	LocalDate date = LocalDate.of(2023, 12, day);
    	DayOfWeek dayOfWeek = date.getDayOfWeek();
    	int dayOfWeekNumber = dayOfWeek.getValue();
    	return dayOfWeekNumber;
    }
    //평일 주말 확인 메소드
    private static int checkDayOfWeekSale(int dayOfWeekNumber, Map<Menu, Integer> validMenu) {
    	int dayOfWeekSaleAmount = 0;
    	//주말일때
    	if(dayOfWeekNumber == 5 || dayOfWeekNumber == 6) {
    		dayOfWeekSaleAmount = calculateWeekendSale(validMenu);
    	}
    	//평일일때
    	if (!(dayOfWeekNumber == 5 || dayOfWeekNumber == 6)) {
    		dayOfWeekSaleAmount = calculateWeekdaySale(validMenu);
		}
    	return dayOfWeekSaleAmount;
    }
    //평일 할인 계산 메소드
    private static int calculateWeekdaySale(Map<Menu, Integer> validMenu) {
    	int dayOfWeekSaleAmount = 0;
    	for (Map.Entry<Menu, Integer> entry : validMenu.entrySet()) {
			if(entry.getKey().getTypeMenu().equals("dessert")) {
				dayOfWeekSaleAmount -= entry.getValue() * 2023; 
			}
		}
    	return dayOfWeekSaleAmount;
    }
    //주말 할인 계산 메소드
    private static int calculateWeekendSale(Map<Menu, Integer> validMenu) {
    	int dayOfWeekSaleAmount = 0;
    	for (Map.Entry<Menu, Integer> entry : validMenu.entrySet()) {
    		if(entry.getKey().getTypeMenu().equals("main")) {
				dayOfWeekSaleAmount -= entry.getValue() * 2023; 
			}
		}
    	return dayOfWeekSaleAmount;
    }
    //특별 할인 확인 메소드
    private static int checkSpecialDaySale(int dayOfWeekNumber, int day) {
    	int specialDaySaleAmount = 0;
    	if(dayOfWeekNumber == 7 || day == 25) {
    		specialDaySaleAmount -= 1000;
    	}
    	return specialDaySaleAmount;
    }
    //증정 이벤트 계산 메소드
    private static int calculateGiveawayAmount(boolean giveaway) {
    	int giveawayAmount = 0;
    	if(giveaway) {
    		giveawayAmount = -25000;
    	}
    	return giveawayAmount;
    }
    //총혜택 금액 계산 메소드
    private static int calculateTotalSaleAmount(int christmasDaySaleAmount, 
												int dayOfWeekSaleAmount,
												int specialDaySaleAmount, 
												int giveawayAmount) {
    	int totalSaleAmount = christmasDaySaleAmount 
    						+ dayOfWeekSaleAmount
    						+ specialDaySaleAmount
    						+ giveawayAmount;
    	return totalSaleAmount;
    }
    //할인 후 예상 결제 금액 계산 메소드
    private static int calculateTotalPriceAfterDiscount(int totalBeforeDiscount,
    													int christmasDaySaleAmount, 
														int dayOfWeekSaleAmount,
														int specialDaySaleAmount) {
    	int totalAfterDiscount = totalBeforeDiscount 
    							+ christmasDaySaleAmount 
    							+ dayOfWeekSaleAmount 
    							+ specialDaySaleAmount;
    	return totalAfterDiscount;
    }
    //이벤트 배지 부여 확인 메소드
    private static String checkEventBadge(int totalSaleAmount) {
    	String eventBadge = "없음";
    	if(totalSaleAmount >= 5000) {
    		eventBadge = "별";
    	}
    	if(totalSaleAmount >= 10000) {
    		eventBadge = "트리";
    	}
    	if(totalSaleAmount >= 20000) {
    		eventBadge = "산타";
    	}
    	return eventBadge;
    }
}
