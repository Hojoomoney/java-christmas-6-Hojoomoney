package christmas;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
    	OutputView outputView = new OutputView();
    	InputView inputView = new InputView();
    	outputView.printIntro();
    	while(true) {
    		try {
    			inputView.readDate();
    			break;
    		} catch(NumberFormatException e) {
    			System.out.println("[ERROR] 숫자를 입력해 주세요.");
    		} catch(IllegalArgumentException e) {
    			System.out.println(e.getMessage());
    		} 
    	}
    	
    }
}
