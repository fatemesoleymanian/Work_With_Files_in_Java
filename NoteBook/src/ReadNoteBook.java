import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ReadNoteBook {
    public ReadNoteBook(File notebook) {
        //not efficient
//        if (notebook.exists()){
//            try {
//                Scanner scanner = new Scanner(notebook);
//                while (scanner.hasNext()) {
//                    String notes = scanner.nextLine();
//                    System.out.println(notes);
//                }
//                scanner.close();
//            }catch (IOException e){
//                System.out.println("Error in reading your note book!");
//                e.printStackTrace();
//            }
//        }else System.out.println("There's no such a note book bitch!");
    }
    public void bufferRead(File notebook){
        if (notebook.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(notebook))){// Auto-closed
                String line;
                System.out.println("Notes:");
                while ((line = reader.readLine()) != null) {
                    System.out.println("- " + line);
                }
            } catch (IOException e) {
                System.out.println("Error in buffering your note book!");
                e.printStackTrace();
            }
        }else System.out.println("There's no such a note book bitch!");
    }
}
