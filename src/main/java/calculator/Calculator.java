package calculator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Calculator {
    public void semi_main(String input) {
        String del_deli_input = "";
        List<String> parts_input;

        if(No_input(input))
            System.out.println("결과 : " + 0);
        if(negative_num(input))
            throw new IllegalArgumentException("음수값이 포함되면 안됩니다.");

        List<String> Delimiter = new ArrayList<>(Arrays.asList(",",":"));
        del_deli_input = Custom_Deli(input,Delimiter);

        parts_input = split_input(del_deli_input,Delimiter);
        double_check(parts_input);
        System.out.println("결과 : "+ add(parts_input));

    }


    public boolean No_input(String input){
        return input.isEmpty() || !input.matches(".*\\d.*");
    }

    public boolean negative_num(String input){
        return input.matches(".*-\\d.*");
    }

    public String Custom_Deli(String input, List<String> Delimiter){
        int index = 0;
        StringBuilder returnstring = new StringBuilder(input);

        while((index = returnstring.indexOf("//",index)) != -1){
            if(index + 4 < returnstring.length()){
                String enter = Character.toString(returnstring.charAt(index + 3)) + Character.toString(returnstring.charAt(index + 4));
                char ch = returnstring.charAt(index+2);
                if(enter.equals("\\n") && !Character.isDigit(ch)){
                    Delimiter.add(Character.toString(returnstring.charAt(index + 2)));
                    returnstring.delete(index,index+5);
                    index = 0; // 수정된 문자열 다시 순회해서 찾게하기
                }
            }
            index += 2;
        }
        return returnstring.toString();
    }

    public List<String> split_input(String input,List<String> Delimiter){
        List<String> escapedDelimiters = escapeDelimiters(Delimiter);

        List<String> parts = new ArrayList<>();

        for(String delimiter : escapedDelimiters){
            if(parts.isEmpty()){
                parts.addAll(Arrays.asList(input.split(delimiter)));
            }else{
                List<String> newparts = new ArrayList<>();
                for(String part : parts){
                    newparts.addAll(Arrays.asList(part.split(delimiter)));
                }
                parts = newparts;
            }
        }
        for(String part : parts){
            //System.out.println(part);
            if(part.matches(".*[^0-9.].*"))
                throw new IllegalArgumentException("입력된 값에 문자가 있습니다.");
        }
        return parts;
    }

    private static List<String> escapeDelimiters(List<String> delimiter) {
        List<String> escaped = new ArrayList<>();
        for (String deli : delimiter) {
            escaped.add(Pattern.quote(deli));
        }
        return escaped;
    }

    public double add(List<String> input){
        double sum = 0;
        for(String num : input){
            sum += Double.parseDouble(num);
        }
        return sum;
    }

    public void double_check(List<String> input){
        for(String num : input){
            if(num.startsWith(".") || num.endsWith(".") || num.contains("..")){
                throw new IllegalArgumentException("실수 표현이 잘못된 입력값이 있습니다.");
            }
        }
    }

}
