
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.CREATE;
import java.util.ArrayList;
import javax.swing.JFileChooser;

public class Main
{

    public static void main(String[] args)
    {

        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";
        String wordArray[];
        int wordCount = 0;
        int charCount = 0;
        int lineCount = 0;

        final int FIELDS_LENGTH = 5;

        String id, firstName, lastName, title;
        int yob;

        try
        {

            File workingDirectory = new File(System.getProperty("user.dir"));

            chooser.setCurrentDirectory(workingDirectory);

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));

                int line = 0;
                while(reader.ready())
                {
                    rec = reader.readLine();
                    lineCount++;
                    charCount += rec.length();
                    wordArray = rec.split(" ");
                    wordCount += wordArray.length;
                }
                System.out.println("Line count is: " + lineCount);
                System.out.println("Word count is: " + wordCount);
                System.out.println("Character count is: " + charCount);
                reader.close(); // must close the file to seal it and flush buffer
                System.out.println("\n\nData file read!");

            }
            else
            {
                System.out.println("Failed to choose a file to process");
                System.out.println("Run the program again!");
                System.exit(0);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!!!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

}