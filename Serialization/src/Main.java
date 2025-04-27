import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();
        players.add(new Player("John",20));
        players.add(new Player("Alice",32));
        players.add(new Player("Eric",12));

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("players.ser"));){
            objectOutputStream.writeObject(players);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("players.ser"));){
          ArrayList<Player> loadedList =  (ArrayList<Player>) objectInputStream.readObject();
            for (Player player : loadedList) {
                System.out.println(player.getName()+" , "+player.getScore());
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}