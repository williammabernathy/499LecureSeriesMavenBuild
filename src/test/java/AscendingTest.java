import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
 
class AscendingTest 
{
 
    @Test
    public boolean checkFileAscending() throws IOException
    {
        File sorted = new File("Sorted Names Ascending.txt");
        File compare = new File("SortedTestAscending.txt");
        
        // convert both files to a byte array for easy comparison
        // NOTE: this is poor practice for large files
        byte[] appSorted = Files.readAllBytes(sorted.toPath());
        byte[] testFile = Files.readAllBytes(compare.toPath());
        
        // if the arrays match, return true
        if (Arrays.equals(appSorted, testFile))
            return true;
        else
            return false;
    }
}
