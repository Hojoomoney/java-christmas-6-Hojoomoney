package christmas;

public enum Menu {
	MUSHROOM_SOUP("양송이수프", "appetizer", 6000),
    TAPAS("타파스", "appetizer", 5500),
    CAESAR_SALAD("시저샐러드", "appetizer", 8000),
    TBONE_STEAK("티본스테이크", "main", 55000),
    BARBEQUE_RIB("바베큐리브", "main", 54000),
    SEAFOOD_PASTA("해산물파스타", "main", 35000),
    CHRISTMAS_PASTA("크리스마스파스타", "main", 25000),
    CHOCO_CAKE("초코케이크", "dessert", 15000),
    ICE_CREAM("아이스크림", "dessert", 5000),
    ZERO_COLA("제로콜라", "beverage", 3000),
    RED_WINE("레드와인", "beverage", 60000),
    CHAMPAGNE("샴페인", "beverage", 25000);
	
	private String typeMenu;
	private int price;
	private String koreanName;
	

	private Menu(String koreanName,String typeMenu, int price) {
		this.koreanName = koreanName;
		this.typeMenu = typeMenu;
		this.price = price;
	}
	
	public static Menu getMenuByKoreanName(String koreanName) {
        for (Menu menu : values()) {
            if (menu.koreanName.equals(koreanName)) {
                return menu;
            }
        }
        throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요");
    }
	
	public String getTypeMenu() {
		return typeMenu;
	}
	
	public int getPrice() {
		return price;
	}
	public String getKoreanName() {
		return koreanName;
	}
}
