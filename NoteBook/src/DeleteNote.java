import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DeleteNote {
    public DeleteNote(File notebook,String noteToDelete){
//        List<String> remainingNotes = new ArrayList<>();
//        //reading
//        if (notebook.exists()) {
//            try (BufferedReader reader = new BufferedReader(new FileReader(notebook))){// Auto-closed
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    if (!line.contains(noteToDelete) && !line.startsWith(noteToDelete)){
//                        remainingNotes.add(line);
//                    }
//                }
//            } catch (IOException e) {
//                System.out.println("Error in buffering your note book!");
//                e.printStackTrace();
//            }
//        }else System.out.println("There's no such a note book bitch!");
//
//        //writing
//        try(BufferedWriter writer = new BufferedWriter(new FileWriter(notebook,false))) {
//            for (String note : remainingNotes){
//                writer.write(note);
//                writer.newLine();
//            }
//            System.out.println("Note Deleted successfully");
//        }catch (IOException e){
//            System.out.println("Error in deleting");
//            e.printStackTrace();
//        }
    }

    public void advancedDeletion(File originalFile,String noteToDelete){
        File tmpFile = new File("notebook_tmp.txt");
        boolean deleteAtLeatOne = false;
        //reading
        if (originalFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(originalFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tmpFile))){// Auto-closed
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.toLowerCase().contains(noteToDelete.toLowerCase()) || line.toLowerCase().startsWith(noteToDelete.toLowerCase())){
                        deleteAtLeatOne = true ;
                        continue;
                    }
                    writer.write(line);
                    writer.newLine();
                }
            } catch (IOException e) {
                System.out.println("Error in buffering your note book!");
                e.printStackTrace();
            }
        }else System.out.println("There's no such a note book bitch!");

        //deleting and renaming
        if (originalFile.delete()){
            if (tmpFile.renameTo(originalFile)){
                System.out.println(deleteAtLeatOne ? "Note deleted":"No such note");
            }else System.out.println("Error in renaming!");
        }else System.out.println("error in deleting original file");

    }
}
