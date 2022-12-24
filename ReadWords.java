import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList;

public class ReadWords 
{
    public static List<String> readWordsList()
    {
        List<String> words = new ArrayList<String>();

        try 
        {
            File myObj = new File("wordList.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) 
            {
              String data = myReader.nextLine();
              words.add(data);
            }

            myReader.close();
        }
        catch (FileNotFoundException e) 
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return words;
    }
}
