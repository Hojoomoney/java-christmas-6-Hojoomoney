package christmas;

import java.util.*;

import org.junit.jupiter.params.shadow.com.univocity.parsers.conversions.EnumSelector;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
    	OutputView outputView = new OutputView();
    	CheckValidate checkValid = new CheckValidate();
    	outputView.printIntro();
    	int date = checkValid.checkValidDate();
    	Map<Menu, Integer> validMenu = new LinkedHashMap<>();
    	validMenu = checkValid.checkValidMenu();
    	outputView.printDate(date);
    	outputView.printMenu(validMenu);
    	int total = outputView.printTotalPriceBeforeDiscount(validMenu);
    	boolean giveaway = outputView.printGiveaway(total);
    	int christmasDaySaleAmount = checkChristmasDaySale(date);
    	
    }
    
    //크리스마스 디데이 할인 확인 메소드
    private static int checkChristmasDaySale(int day) {
    	int saleAmount = 0;
    	if(day < 26) {
    		saleAmount = 1000 + (day-1)*100;
    		return saleAmount;
    	}
    	return saleAmount;
    }
}
