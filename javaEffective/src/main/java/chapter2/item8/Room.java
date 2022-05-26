package chapter2.item8;

import java.lang.ref.Cleaner;

import static java.lang.ref.Cleaner.*;

public class Room implements AutoCloseable {

    private static final Cleaner cleaner = create();

    private static class State implements Runnable {
        int numJunkpiles;

        State(int numJunkpiles) {
            this.numJunkpiles = numJunkpiles;
        }

        @Override
        public void run() {
            System.out.println("방 청소");
            numJunkpiles = 0;
        }
    }
    private final State state;
    private final Cleanable cleanable;

    public Room(int numJunkpiles) {
        state = new State(numJunkpiles);
        cleanable = cleaner.register(this, state);
    }

    @Override
    public void close() throws Exception {
        cleanable.clean();
    }

}
