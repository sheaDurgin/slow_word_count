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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class JeffR_Solution {

    static final boolean VERBOSE = false;

    Map<String,Integer> wordCounts = new HashMap<>();
    
    public void processWord(String nextWord) {
        String key = nextWord.toLowerCase();
        Integer countSoFar = wordCounts.get(key);
        boolean firstTime = countSoFar == null || countSoFar.intValue() == 0;
        if( !firstTime ) {
            countSoFar++;
        }
        else {
            countSoFar = Integer.valueOf(1);
        }
        wordCounts.put(key, countSoFar);

    }

    static final int FILE_BUFF_SIZE = 1; // 64*1024;
    BufferedReader fileReader;
    char buff[] = new char[FILE_BUFF_SIZE+1];
    int charsInBuff = 0;
    int offset = 0;
    boolean eof = false;

    Pattern pattern = Pattern.compile("[^\s\r\n]+");
    Matcher matcher;

    public String getNextWord() throws IOException {

        String nextWord = null;
        boolean foundNextWord = false;
        while( !foundNextWord ) {

            if( charsInBuff == 0 && !eof) {
                int charsToRead = FILE_BUFF_SIZE - offset;
                int actuallyRead = fileReader.read(buff, offset, charsToRead);
                if( actuallyRead > 0 ) {
                    charsInBuff = actuallyRead;
                    buff[offset+actuallyRead] = '\0';

                    if( actuallyRead == charsToRead ) {
                        // *maybe* more content in the file
                    }
                    else {
                        eof = true;
                    }
                }
                else {
                    charsInBuff = 0;
                    eof = true;
                }
            }

            if( charsInBuff == 0) {
                return nextWord;
            }

            String inBuff = new String(buff,offset,charsInBuff);

            if( VERBOSE ) {
                System.out.println( "Processing `" + inBuff + "`");
            }

            if( matcher == null ) {
                matcher = pattern.matcher(inBuff);
            }

            if( matcher.find()) {
                if( nextWord == null ) {
                    nextWord = matcher.group();
                }
                else {
                    if( matcher.start() > 0) {
                        matcher = null;
                        return nextWord;
                    }
                    nextWord = nextWord.concat(matcher.group());
                }
                int endsAt = matcher.end();
                if( endsAt > 0 ) {
                    offset += endsAt;
                    charsInBuff -= endsAt;
                    if( charsInBuff > 0 ) {
                        foundNextWord = true;
                    }
                    else {
                        // bumped up against the end of the buffer, we might have split a word across a read
                    }
                    
                }
                else {
                    offset++;
                    charsInBuff--;
                }

                if( offset >= FILE_BUFF_SIZE) {
                    offset = 0;
                    charsInBuff = 0;
                }
            }
            else {
                offset = 0;
                charsInBuff = 0;
                if( nextWord != null ) {
                    foundNextWord = true;
                }
            }

            matcher = null;

            if( eof ) {
                break;
            }
            
        }

        return nextWord;
    }

    public void setup(File testFile) throws FileNotFoundException {

        fileReader = new BufferedReader(new FileReader(testFile));
        
        buff[0] = '\0';
        charsInBuff = 0;
        offset = 0;
        
        eof = false;

        wordCounts.clear();
    }


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

            System.out.println( "~~~~~~~~~~~");
            System.out.println("Processing " + testFileName + "...");

            long startTime = System.nanoTime();
            setup(testFile);
            for(String nextWord; (nextWord = getNextWord()) != null; ) {
                processWord(nextWord);
            }
            long endTime = System.nanoTime();
            double totalTimeMillis = 1.0 * (endTime - startTime) / 1000000.0;
            

            System.out.println( "-----------");
            System.out.println( "Word Counts");
            System.out.println( "-----------");
            List<String> words = new ArrayList<>(wordCounts.keySet());
            words.sort(new Comparator<String>(){

                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }

            });
            for( String word : words ) {
                System.out.println( word + ": " + wordCounts.get(word));
            }
            System.out.println( "___________");
            System.out.println( "...Processed " + words.size() + " unique words in " + totalTimeMillis + "ms.");
            System.out.println( "===========");

            return words.size();

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

        String sampleTestFile = "JeffR_Sample_OneLine.txt";

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
