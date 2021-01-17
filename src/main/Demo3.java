package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.nio.channels.Channels;

public class Demo3 {

    public static void main(String[] args) throws IOException {
        File file = new File("C:/demo/employee3.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        try (Writer writer = Channels.newWriter(new FileOutputStream(file.getAbsoluteFile(), true).getChannel(), "UTF-8")) {
            for (int i = 0; i < 10000000; i++) {
                System.out.println(i);
                writer.append(i + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n");
            }
        }

    }
}
