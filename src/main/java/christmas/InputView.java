package christmas;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
	private static final String ERROR_MSG = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요";
	public int readDate() {
		System.out.println("12월 중 식당 예쌍 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
		String input = Console.readLine();
		int date = Integer.parseInt(input);
		if(date > 31 || date < 1) {
			throw new IllegalArgumentException(ERROR_MSG);
		}
		return date;
	}
}
