package christmas;

import java.util.*;

public class OutputView {
	
	public void printIntro() {
		System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
	}
	public void printDate(int date) {
		System.out.println("12월 " + date + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
	}
	public void printMenu(Map<String, Integer> menu) {
		System.out.println();
		System.out.println("<주문 메뉴>");
		for (Map.Entry<String, Integer> entry : menu.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue() + "개");
		}
	}
	
}
