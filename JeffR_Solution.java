/**
 * to test a specific file, pass the filespec on the command-line
 * e.g.
 * java JeffR_Solution.java someFile.txt
 * -OR-
 * javac JeffR_Solution.java
 * java JeffR_Solution someFile.txt
 */
/**
 * Count the frequency of words in a file, S-L-O-W-L-Y
 */
import java.io.File;
import java.io.IOException;

public class JeffR_Solution {

    public int countWordFrequencyInFile(File testFile) {
        String testFileName;
        try {
            testFileName = testFile.getCanonicalPath();

            if( !testFile.exists()) {
                System.err.println("testFile " + testFileName + " does not exist.");
                return -2; // DOS file not found
            }

            if( !testFile.canRead()) {
                System.err.println("testFile " + testFileName + " cannot be read.");
                return -5; // DOS access denied
            }
            System.out.println("Processing " + testFileName);

            return 0;

        }
        catch( IOException ex) {
            System.err.println("Failure Processing " + ex.getClass().getName() + " " + ex.getMessage());
            return -(0x1F); // DOS general failure
        }

    }
    

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // external entry points
    //

    public static int processFile(File testFile) {

        return new JeffR_Solution().countWordFrequencyInFile(testFile);

    }

    public static int processFile(String testFileName) {
        File testFile = new File(testFileName);
        return processFile(testFile);
    }

    public static void main(String[] args){

        String sampleTestFile = "JeffR_Sample.txt";

        String[] testFiles;

		if( args.length > 0 ) {
            testFiles = args;
        }
        else {
            testFiles = new String[]{ sampleTestFile };
        }

        for( String testFileName : testFiles) {

            int err = processFile(testFileName);

            if( err < 0) {
                // note any additional files won't be processed if one fails
                System.exit(-err);
            }
            

        }
	
	}
}
