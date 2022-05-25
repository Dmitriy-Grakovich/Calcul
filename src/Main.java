import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        System.out.println(calc(input));
    }

    public static String calc(String input) {
        String result;
        Double resultInt;
        String[] st = input.split(" ");//разделяем строку на подстроки

        if(st.length != 3) throw new IllegalArgumentException("Не корректное выражение");//если больше 3 элементов исключение
        if((arabC(st[0])&&!arabC(st[2]))||(arabC(st[2])&&!arabC(st[0]))) throw new IllegalArgumentException(" Числа должны бать одного исчисления");//если елементы разных исчесление есключение
        if(integerC(st[0])==-1||integerC(st[2])==-1) throw new IllegalArgumentException("Не корректное выражение");
        if(!arabC(st[0])&&rimToArab(st[0])<=rimToArab(st[2])&&st[1].equals("-")) throw new IllegalArgumentException(" Первое число меньше или равно второго, отрицательных римских чисел нет");
        switch (st[1]){
            case "+": resultInt = integerC(st[0]) + integerC(st[2]);
            break;
            case "-": resultInt = integerC(st[0]) - integerC(st[2]);
            break;
            case "*": resultInt = integerC(st[0]) * integerC(st[2]);
            break;
            case "/": resultInt = integerC(st[0]) / integerC(st[2]);
            break;
            default: resultInt=-1.0;
        }
        if (resultInt==-1) throw new IllegalArgumentException("Действие не предусмотрено");
        if(arabC(st[0])) {
            result = String.format("%.0g%n",resultInt);

        } else {

            result = arabToRim((int)Math.ceil(resultInt));
        }

        return result;
    }

    // перевод арабских чисел в риские числа
    private static String arabToRim(Integer resultInt) {
        if(resultInt <=0 || resultInt>100) throw new IllegalArgumentException(resultInt + "Число в не диапазона");
        StringBuilder result = new StringBuilder();
        List<Rim> list = Rim.getValueAllRev();
        for (int i = 0; i < list.size();i++){
            Rim rim = list.get(i);
            while (resultInt >= rim.getValue()){
                resultInt -= rim.getValue();
                result.append(rim.name());
            }
        }
        return result.toString();
    }

    // перевод римских чисел в арабсие числа
    private static int rimToArab(String in) {
        int result = 0;
        List<Rim> list = Rim.getValueAllRev();
        int i = 0;
        while (in.length()>0 && i < list.size()){
            Rim rim = list.get(i);
            if(in.startsWith(rim.name())){
                result += rim.getValue();
                in = in.substring(rim.name().length());
            } else {
                i++;
            }
        }
        return result;
    }

    //проверка является ли строка арабской цифрой от 1 до 10
    public static boolean arabC(String in){
        boolean bl = false;
        char[] ch = in.toCharArray();
        int a = ch[0];
        if(a<48||a>57) {
            bl = false;
        }
        else {
            if (ch.length > 2 || (ch.length > 1 && (a != 49 || (int) ch[1] != 48)))
                throw new IllegalArgumentException(in + " Число в не диапазона");
            bl = true;
        }
        return bl;
    }

    //перевод строк в числа
    public static double integerC(String in){
        int i = -1;
        int a;
        if (arabC(in)) {
            a =  Integer.parseInt(in);
        } else {
            a = rimToArab(in);
        }
        //проверка что число 1-10
        for (int j = 1; j<11;j++){
            if(a==j)
                i=a;
        }
        return i;
    }




}
