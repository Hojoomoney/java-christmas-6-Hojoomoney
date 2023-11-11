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
    		String[] menu = inputView.readMenu();
    		Map<String, String> menuItems = convertToMap(menu);
    		Map<String, Integer> menuParseInt = checkValidQuantity(menuItems);
    		validMenu = checkValidNameFromMenu(menuParseInt);
    		validOrder = validMenu.size() == menu.length;
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
				checkMoreThan1(quantity);
				menuParseInt.put(entry.getKey(), quantity);
			} catch(NumberFormatException e) {
				System.out.println(ERROR_MSG);
				menuParseInt.clear();
				break;
			} catch(IllegalArgumentException e) {
				System.out.println(e.getMessage());
				menuParseInt.clear();
				break;
			}
		}
		return menuParseInt;
	}
	//입력받은 메뉴를 Map으로 변환시켜주는 메소드
	private Map<String, String> convertToMap(String[] menu) {
		Map<String, String> menuItems = new LinkedHashMap<>();
		for (String menuItem : menu) {
			String[] parts = menuItem.split("-");
			String menuName = parts[0].trim();
			String quantity = parts[1].trim();
			try {
				checkDuplicateMenu(menuItems, menuName);
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				menuItems.clear();
				break;
			}
			menuItems.put(menuName, quantity);
		}
		return menuItems;
	}
	//중복된 메뉴 확인 메소드
	private void checkDuplicateMenu(Map<String, String> menuItems, String menuName) {
		if (menuItems.containsKey(menuName)) {
			throw new IllegalArgumentException(ERROR_MSG);
		}
	}
	//메뉴의 개수가 1 이상인지 확인하는 메소드
	private void checkMoreThan1(int quantity) {
		if (quantity < 1) {
			throw new IllegalArgumentException(ERROR_MSG);
		}
	}
}
