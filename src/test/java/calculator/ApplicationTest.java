package calculator;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    @Test
    void 커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//;\\n1");
            assertThat(output()).contains("결과 : 1");
        });
    }
    @Test
    void 커스텀_구분자_사용_실수덧셈() {
        assertSimpleTest(() -> {
            run("37://;\\n1,3.2;2");
            assertThat(output()).contains("결과 : 43.2");
        });
    }

    @Test
    void 커스텀_구분자3개_사용() {
        assertSimpleTest(() -> {
            run("37://;\\n1,//*\\n//%\\n3.2*2%3");
            assertThat(output()).contains("결과 : 46.2");
        });
    }
    @Test
    void 커스텀_구분자_사용_() {
        assertSimpleTest(() -> {
            run("");
            assertThat(output()).contains("결과 : 46.2");
        });
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("-1,2,3"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }
    @Test
    void 예외_실수_온점위치_맨앞() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException(".37.4:27"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_실수_온점위치_맨뒤() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("37.4:27."))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_실수_온점연속() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("37..4:27"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_입력_문자포함() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("32.7n:28,32:23j"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_입력_문자열x() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("\n"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_입력_숫자x() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("n:fswef,fse"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_구분_숫자() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("3://8\\n,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }



    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
