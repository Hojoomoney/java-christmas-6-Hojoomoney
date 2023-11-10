package christmas;

import java.util.*;

public class CheckValidate {
	private static final String ERROR_MSG = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요";
	InputView inputView = new InputView();
	Map<Menu, Integer> validMenu = new LinkedHashMap<>();
	public int checkValidDate() {
		int date = 0;
		while(true) {
    		try {
    			date = inputView.readDate();
    			break;
    		} catch(NumberFormatException e) {
    			System.out.println("[ERROR] 숫자를 입력해 주세요.");
    		} catch(IllegalArgumentException e) {
    			System.out.println(e.getMessage());
    		} 
    	}
		return date;
	}
	public Map<Menu, Integer> checkValidMenu() {
		boolean	validOrder = false;
    	while (!validOrder) {
    		Map<String, String> menuItems = inputView.readMenu();
    		Map<String, Integer> menuParseInt = checkValidQuantity(menuItems);
    		validMenu = checkValidNameFromMenu(menuParseInt);
    		validOrder = validMenu.size() == menuItems.size();
    	}
    	return validMenu;
	}
	//유효한 메뉴 확인 메소드
	private Map<Menu, Integer> checkValidNameFromMenu(Map<String, Integer> menuItems) {
		for (Map.Entry<String, Integer> entry : menuItems.entrySet()) {
			try {
				Menu menu = Menu.getMenuByKoreanName(entry.getKey());
				validMenu.put(menu, entry.getValue());
			} catch(IllegalArgumentException e) {
				System.out.println(e.getMessage());
				validMenu.clear();
				break;
			}
		}
		return validMenu;
	}
	//유효한 메뉴의 개수 확인 메소드
	private Map<String, Integer> checkValidQuantity(Map<String, String> menuItems) {
		Map<String, Integer> menuParseInt = new LinkedHashMap<>();
		for (Map.Entry<String, String> entry : menuItems.entrySet()) {
			try {
				int quantity = Integer.parseInt(entry.getValue());
				menuParseInt.put(entry.getKey(), quantity);
			} catch(NumberFormatException e) {
				System.out.println(ERROR_MSG);
				menuParseInt.clear();
				break;
			}
		}
		return menuParseInt;
	}
	
}
