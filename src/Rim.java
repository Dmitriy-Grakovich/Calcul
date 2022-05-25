import java.util.*;

enum Rim {
    I(1),IV(4),V(5), IX(9),X(10),XL(40),L(50),XC(90),C(100);
    private int value;

    Rim(int value) {
        this.value = value;
    }

    //получения значения римской цифры в арабском виде.
    public int getValue() {
        return value;
    }

    //получение коллекции Rim отсортированных в обратном порядке для перевода цифр <->
    public static List<Rim> getValueAllRev(){
        List<Rim> list = new ArrayList<>();
        list.addAll(Arrays.asList(Rim.values()));
        Comparator<Rim> comparator = new Comparator<Rim>() {
            @Override
            public int compare(Rim o1, Rim o2) {
                return o1.value-o2.value;
            }
        };
        Collections.sort(list,comparator);
        Collections.reverse(list);
       return list;
    }
}
