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
	public void printTotalPriceBeforeDiscount(int totalBeforeDiscount) {
		System.out.println();
		System.out.println("<할인 전 총주문 금액>");
		System.out.println(df.format(totalBeforeDiscount));
	}
	//증정 메뉴 출력 기능
	public boolean printGiveaway(int totalBeforeDiscount) {
		System.out.println();
		System.out.println("<증정 메뉴>");
		boolean giveaway = false;
		if(totalBeforeDiscount >= 120000) {
			giveaway = true;
			System.out.println("샴페인 1개");
			return giveaway;
		}
		System.out.println("없음");
		return giveaway;
	}
	//혜택 내역, 총혜택 금액, 할인 후 예상 결제 금액, 이벤트 배지 출력 기능
	public void printAllResults(int christmasDaySaleAmount, 
								int dayOfWeekSaleAmount,
								int dayOfWeekNumber,
								int specialDaySaleAmount, 
								int giveawayAmount,
								int totalSaleAmount,
								int totalAfterDiscount,
								String eventBadge) {
		printAllBenefits(christmasDaySaleAmount,dayOfWeekSaleAmount,dayOfWeekNumber,specialDaySaleAmount, giveawayAmount,totalSaleAmount);
		printTotalSaleAmount(totalSaleAmount);
		printTotalAfterDiscount(totalAfterDiscount);
		printEventBadge(eventBadge);
	}
	//혜택 내역 출력 기능
	public void printAllBenefits(int christmasDaySaleAmount, 
								int dayOfWeekSaleAmount,
								int dayOfWeekNumber,
								int specialDaySaleAmount, 
								int giveawayAmount,
								int totalSaleAmount) {
		System.out.println();
		System.out.println("<혜택 내역>");
		if (totalSaleAmount != 0) {
			printChristmanDaySaleAmount(christmasDaySaleAmount);
			printDayOfWeekSaleAmount(dayOfWeekSaleAmount, dayOfWeekNumber);
			printSpecialDaySaleAmount(specialDaySaleAmount);
			printGiveawayAmount(giveawayAmount);
		}
		if (totalSaleAmount == 0) {
			System.out.println("없음");
		}
	}
	private void printChristmanDaySaleAmount(int christmasDaySaleAmount) {
		if(christmasDaySaleAmount != 0) {
			System.out.println("크리스마스 디데이 할인: " + df.format(christmasDaySaleAmount));
		}
	}
	private void printDayOfWeekSaleAmount(int dayOfWeekSaleAmount, int dayOfWeekNumber) {
		if(dayOfWeekSaleAmount != 0) {
			//주말일때
			if(dayOfWeekNumber == 5 || dayOfWeekNumber == 6) {
				System.out.println("주말 할인: " + df.format(dayOfWeekSaleAmount));
			}
			//평일일때
			if (!(dayOfWeekNumber == 5 || dayOfWeekNumber == 6)) {
				System.out.println("평일 할인: " + df.format(dayOfWeekSaleAmount));
			}
		}
	}
	private void printSpecialDaySaleAmount(int specialDaySaleAmount) {
		if(specialDaySaleAmount != 0) {
			System.out.println("특별 할인: " + df.format(specialDaySaleAmount));
		}
	}
	private void printGiveawayAmount(int giveawayAmount) {
		if(giveawayAmount == -25000) {
			System.out.println("증정 이벤트: " + df.format(giveawayAmount));
		}
	}
	//총혜택 금액 출력 메소드
	public void printTotalSaleAmount(int totalSaleAmount) {
		System.out.println();
		System.out.println("<총혜택 금액>");
		System.out.println(df.format(totalSaleAmount));
	}
	//할인 후 예상 결제 금액 출력 메소드
	public void printTotalAfterDiscount(int totalAfterDiscount) {
		System.out.println();
		System.out.println("<할인 후 예상 결제 금액>");
		System.out.println(df.format(totalAfterDiscount));
	}
	//이벤트 배지 출력 기능
	public void printEventBadge(String eventBadge) {
		System.out.println();
		System.out.println("<12월 이벤트 배지>");
		System.out.println(eventBadge);
	}
	
}
