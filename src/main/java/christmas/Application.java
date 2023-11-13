package christmas;

import java.util.*;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
    	OutputView outputView = new OutputView();
    	outputView.printIntro();
    	CheckValidate checkValid = new CheckValidate();
    	int day = checkValid.checkValidDate();
    	Map<Menu, Integer> validMenu = checkValid.checkValidMenu();
    	outputView.printDate(day);
    	outputView.printMenu(validMenu);
    	int totalBeforeDiscount = calculateTotalPriceBeforeDiscount(validMenu);
    	outputView.printTotalPriceBeforeDiscount(totalBeforeDiscount);
    	boolean giveaway = outputView.printGiveaway(totalBeforeDiscount);
    	CalculatingSales calculate = new CalculatingSales(validMenu, giveaway, day, totalBeforeDiscount);
    	outputView.printAllResults(calculate.getChristmasDaySaleAmount(), calculate.getDayOfWeekSaleAmount(), 
    			calculate.getDayOfWeekNumber(), calculate.getSpecialDaySaleAmount(), calculate.getGiveawayAmount(),
    			calculate.getTotalSaleAmount(), calculate.getTotalAfterDiscount(), calculate.getEventBadge());
    }
    //할인 전 총주문 금액 계산 메소드
    private static int calculateTotalPriceBeforeDiscount(Map<Menu, Integer> menu) {
		int total = 0;
		for (Map.Entry<Menu, Integer> entry : menu.entrySet()) {
			total += entry.getKey().getPrice() * entry.getValue();
		}
		return total;
	}
    
}
