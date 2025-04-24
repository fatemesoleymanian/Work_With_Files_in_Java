import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        //Creating file
        try{
            File myFile = new File("D:\\java EE\\practice\\fileHndling\\files\\myFile.txt");
            if (myFile.createNewFile()){
                System.out.println("File created "+myFile.getName());
            }else {
                System.out.println("File already exists!");
            }

        }catch (IOException e){
            System.out.println("An error accured!");
            e.printStackTrace();

        }

        // writing in file
        try {
            FileWriter myFileWriter = new FileWriter("D:\\java EE\\practice\\fileHndling\\files\\myFile.txt");
            myFileWriter.write("I'm a sentense which i'm added to the current file!");
            myFileWriter.close();
            System.out.println("Writing was successful!");
        }catch (IOException e){
            System.out.println("Error in writing");
            e.printStackTrace();
        }

        //read the file
        try {
            File myFile = new File("D:\\java EE\\practice\\fileHndling\\files\\myFile.txt");
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()){
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        }catch (IOException e){
            System.out.println("Error in reading file!");
            e.printStackTrace();
        }

        //file information
        File myFile = new File("D:\\java EE\\practice\\fileHndling\\files\\myFile.txt");
        if (myFile.exists()){
            System.out.println("File name "+myFile.getName());
            System.out.println("absolute path "+myFile.getAbsolutePath());
            try {
                System.out.println("canonical path "+myFile.getCanonicalPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Writeable  "+myFile.canWrite());
            System.out.println("Readable  "+myFile.canRead());
            System.out.println("File size in bytes  "+myFile.length());
        }else System.out.println("file doesn't exists!");



        //Deleting file
        if (myFile.delete()){
            System.out.println(myFile.getName()+" has been deleted");
        }else System.out.println("Failed to delete!");


        //Deleting folder
        File myFolder = new File("D:\\java EE\\practice\\fileHndling\\files");
        if (myFolder.delete()){
            System.out.println(myFolder.getName()+" folder has been deleted");
        }else System.out.println("Failed to delete folder!");
    }
}