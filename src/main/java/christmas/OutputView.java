package christmas;

import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {
	DecimalFormat df = new DecimalFormat("###,###원");
	//인트로 출력 기능
	public void printIntro() {
		System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
	}
	//날짜 출력 기능
	public void printDate(int date) {
		System.out.println("12월 " + date + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
	}
	//주문한 메뉴 출력 기능
	public void printMenu(Map<Menu, Integer> menu) {
		System.out.println();
		System.out.println("<주문 메뉴>");
		for (Map.Entry<Menu, Integer> entry : menu.entrySet()) {
			System.out.println(entry.getKey().getKoreanName() + " " + entry.getValue() + "개");
		}
	}
	//할인 전 총주문 금액 출력
	public int printTotalPriceBeforeDiscount(Map<Menu, Integer> menu) {
		int total = 0;
		for (Map.Entry<Menu, Integer> entry : menu.entrySet()) {
			total += entry.getKey().getPrice() * entry.getValue();
		}
		System.out.println();
		System.out.println("<할인 전 총주문 금액>");
		System.out.println(df.format(total));
		return total;
	}
	//증정 메뉴 출력 기능
	public boolean printGiveaway(int total) {
		System.out.println();
		System.out.println("<증정 메뉴>");
		boolean giveaway = false;
		if(total >= 120000) {
			giveaway = true;
			System.out.println("샴페인 1개");
			return giveaway;
		}
		System.out.println("없음");
		return giveaway;
	}
}
