import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AddNote {

    public AddNote(File notebook,String note){
        try {
            FileWriter fileWriter = new FileWriter(notebook,true);
            fileWriter.append(note + System.lineSeparator());
            fileWriter.close();
            System.out.println("Note is added to your note book!");
        } catch (IOException e) {
            System.out.println("there's a problem in adding your note x^x");
            e.printStackTrace();
        }

    }
}
