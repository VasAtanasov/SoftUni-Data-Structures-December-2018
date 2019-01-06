public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();

        list.add(4);
        list.add(4);
        list.add(5);
        list.add(5);
        list.add(7);
        list.add(7);
        System.out.println(list);
        System.out.println(list.getCount());
        System.out.println();
        System.out.println(list.removeAt(2));
        System.out.println(list.removeAt(2));
        System.out.println(list.removeAt(2));
        System.out.println(list.removeAt(2));
    }

}
