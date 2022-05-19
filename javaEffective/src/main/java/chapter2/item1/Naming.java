package chapter2.item1;

import java.nio.file.Files;
import java.nio.file.Path;

public class Naming {

    public static void methodNaming() {
        try {
            Files.getFileStore(Path.of("first"));
        } catch (Exception e) {

        }
    }

}
