package calculator;
import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        System.out.println("덧셈할 문자열을 입력해주세요:");
        String input = Console.readLine(); // 문자열 입력 받기
        Calculator calculator = new Calculator();
        String No_Space_Input = input.replaceAll("\\s+",""); // 문자열 공백제거
        calculator.semi_main(No_Space_Input);
    }

}
