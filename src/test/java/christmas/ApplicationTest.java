package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Test
    void 모든_타이틀_출력() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                "<주문 메뉴>",
                "<할인 전 총주문 금액>",
                "<증정 메뉴>",
                "<혜택 내역>",
                "<총혜택 금액>",
                "<할인 후 예상 결제 금액>",
                "<12월 이벤트 배지>"
            );
        });
    }
    @Test
    void 기능_테스트() {
    	assertSimpleTest(() -> {
    		run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
    		assertThat(output()).contains(
    				"<주문 메뉴>" + LINE_SEPARATOR + "티본스테이크 1개" + LINE_SEPARATOR + "바비큐립 1개" + LINE_SEPARATOR + "초코케이크 2개" + LINE_SEPARATOR + "제로콜라 1개",
    				"<할인 전 총주문 금액>" + LINE_SEPARATOR + "142,000원",
    				"<증정 메뉴>" + LINE_SEPARATOR + "샴페인 1개",
    				"<혜택 내역>" + LINE_SEPARATOR + "크리스마스 디데이 할인: -1,200원" + LINE_SEPARATOR + "평일 할인: -4,046원" + LINE_SEPARATOR + "특별 할인: -1,000원" + LINE_SEPARATOR + "증정 이벤트: -25,000원",
    				"<총혜택 금액>" + LINE_SEPARATOR + "-31,246원",
    				"<할인 후 예상 결제 금액>" + LINE_SEPARATOR + "135,754원",
    				"<12월 이벤트 배지>" + LINE_SEPARATOR + "산타"
    				);
    	});
    }

    @Test
    void 혜택_내역_없음_출력() {
        assertSimpleTest(() -> {
            run("26", "타파스-1,제로콜라-1");
            assertThat(output()).contains("<혜택 내역>" + LINE_SEPARATOR + "없음");
        });
    }

    @Test
    void 날짜_예외_테스트() {
        assertSimpleTest(() -> {
            runException("a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 주문_예외_테스트() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }
    @Test
    void 주문_예외_테스트_총주문_개수_20개이하() {
    	assertSimpleTest(() -> {
    		runException("3", "아이스크림-6,제로콜라-6,해산물파스타-5,양송이수프-4");
    		assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    	});
    }
    @Test
    void 주문_예외_테스트_음료만_주문시_불가() {
    	assertSimpleTest(() -> {
    		runException("3", "제로콜라-6,레드와인-1,샴페인-1");
    		assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    	});
    }
    @Test
    void 주문_예외_테스트_중복된_음식_불가() {
    	assertSimpleTest(() -> {
    		runException("3", "시저샐러드-1,시저샐러드-2,샴페인-1");
    		assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    	});
    }
    @Test
    void 주문_예외_테스트_메뉴판에_없는_메뉴_입력() {
    	assertSimpleTest(() -> {
    		runException("3", "티본스테이크-1,스프라이트-2,샴페인-1");
    		assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    	});
    }
    @Test
    void 주문_예외_테스트_메뉴의_개수_1이상() {
    	assertSimpleTest(() -> {
    		runException("3", "티본스테이크-0,샴페인-1");
    		assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    	});
    }
    @Test
    void 주문_예외_테스트_메뉴의_형식이_다른_경우() {
    	assertSimpleTest(() -> {
    		runException("3", "티본스테이크1,제로콜라2");
    		assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    	});
    }
    @Test
    void 주문금액_10000원이하시_이벤트_제외() {
    	assertSimpleTest(() -> {
    		runException("4", "아이스크림-1,제로콜라-1");
    		assertThat(output()).contains("<혜택 내역>" + LINE_SEPARATOR + "없음",
    									  "<총혜택 금액>" + LINE_SEPARATOR + "0원"
    									  );
    	});
    }
    @Test
    void 증정_메뉴_없음_출력() {
        assertSimpleTest(() -> {
            run("13", "티본스테이크-2,제로콜라-3");
            assertThat(output()).contains("<증정 메뉴>" + LINE_SEPARATOR + "없음");
        });
    }
    @Test
    void 증정_메뉴_출력() {
    	assertSimpleTest(() -> {
    		run("13", "티본스테이크-2,아이스크림-2");
    		assertThat(output()).contains("<증정 메뉴>" + LINE_SEPARATOR + "샴페인 1개");
    	});
    }
    @Test
    void 혜택_내역_주말할인_출력() {
        assertSimpleTest(() -> {
            run("1", "바비큐립-1,타파스-1,제로콜라-1");
            assertThat(output()).contains("주말 할인");
        });
    }
    @Test
    void 혜택_내역_평일할인_출력() {
    	assertSimpleTest(() -> {
    		run("3", "타파스-1,아이스크림-1,제로콜라-1");
    		assertThat(output()).contains("평일 할인");
    	});
    }
    @Test
    void 혜택_내역_특별할인_출력() {
    	assertSimpleTest(() -> {
    		run("31", "해산물파스타-1,아이스크림-1,제로콜라-1");
    		assertThat(output()).contains("특별 할인");
    	});
    }
    @Test
    void 할인후_예상_결제_금액_출력() {
    	assertSimpleTest(() -> {
    		run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
    		assertThat(output()).contains("<할인 후 예상 결제 금액>" + LINE_SEPARATOR + "135,754원");
    	});
    }
    @Test
    void 이벤트_배지_별_출력() {
    	assertSimpleTest(() -> {
    		run("13", "티본스테이크-1,초코케이크-2,제로콜라-1");
    		assertThat(output()).contains("<12월 이벤트 배지>" + LINE_SEPARATOR + "별");
    	});
    }
    @Test
    void 이벤트_배지_트리_출력() {
    	assertSimpleTest(() -> {
    		run("13", "크리스마스파스타-2,초코케이크-3,아이스크림-1,제로콜라-1");
    		assertThat(output()).contains("<12월 이벤트 배지>" + LINE_SEPARATOR + "트리");
    	});
    }
    @Test
    void 이벤트_배지_산타_출력() {
    	assertSimpleTest(() -> {
    		run("13", "티본스테이크-2,초코케이크-2,제로콜라-1");
    		assertThat(output()).contains("<12월 이벤트 배지>" + LINE_SEPARATOR + "산타");
    	});
    }
    @Test
    void 이벤트_배지_없음_출력() {
    	assertSimpleTest(() -> {
    		run("13", "타파스-1,제로콜라-1,초코케이크-1");
    		assertThat(output()).contains("<12월 이벤트 배지>" + LINE_SEPARATOR + "없음");
    	});
    }
    
    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
