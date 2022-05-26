package chapter2.item8;

public class Main {

    public static void main(String[] args) throws Exception {
        try (Room myRoom = new Room(7)) {
            System.out.println("안녕~");
        }
        new Room(99);
        System.out.println("아무렴~");
    }

}
