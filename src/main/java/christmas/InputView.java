package christmas;

import java.util.*;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
	private static final String ERROR_MSG = "[ERROR]";
	//날짜 입력 기능
	public int readDate() {
		System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
		String input = Console.readLine();
		int date = Integer.parseInt(input);
		if(date > 31 || date < 1) {
			throw new IllegalArgumentException(ERROR_MSG + " 유효하지 않은 날짜입니다. 다시 입력해 주세요");
		}
		return date;
	}
	//주문하는 메뉴와 개수 입력 기능
	public String[] readMenu() {
		System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
		String input = Console.readLine();
		String[] menu = input.split(",");
		return menu;
	}

}
