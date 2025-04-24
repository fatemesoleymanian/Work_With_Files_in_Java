import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        File noteBook = new File("notebook.txt");
        try {
            if (noteBook.createNewFile()){
                System.out.println("Your note book is created! ");
            }else System.out.println("Let's update your note book!");
        }catch (IOException e){
            System.out.println("Error occured");
            e.printStackTrace();
        }
        System.out.println("Your note:");
        String note = scanner.nextLine();
        AddNote addNote = new AddNote(noteBook,note);



        System.out.println("To delete a note , enter a number or word");
        String noteTodelete = scanner.nextLine();
        DeleteNote deleteNote = new DeleteNote(noteBook,noteTodelete);
        deleteNote.advancedDeletion(noteBook,noteTodelete);

        ReadNoteBook readNoteBook = new ReadNoteBook(noteBook);
        readNoteBook.bufferRead(noteBook);
    }
}