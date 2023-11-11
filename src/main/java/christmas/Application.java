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
    	int total = outputView.printTotalPriceBeforeDiscount(validMenu);
    	boolean giveaway = outputView.printGiveaway(total);
    	int christmasDaySaleAmount = checkChristmasDaySale(day);
    	
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
    //특정 날짜의 요일 구하기
    private static int findDayOfWeek(int day) {
    	LocalDate date = LocalDate.of(2023, 12, day);
    	DayOfWeek dayOfWeek = date.getDayOfWeek();
    	int dayOfWeekNumber = dayOfWeek.getValue();
    	return dayOfWeekNumber;
    }
}
