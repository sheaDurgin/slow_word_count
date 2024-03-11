import java.io.*; //Why not just import what you need?
import java.util.*;

public class PhilipHobby_SLOWWordCount {

    
    public static void main(String[] args) {
        List<String> list = new LinkedList<String>();
        Map<String, Integer> wordMap = new HashMap<String, Integer>();
        List<String> wordSet = new LinkedList<String>();
        
        try {
            File words = new File("eclipse-workspace/Code Labs - Slow Word Count/src/words.txt");
            Scanner myReader = new Scanner(words);
            while (myReader.hasNext()) {
                list.add(myReader.next());
            }            
        }
        
        catch(FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        for (int i = 0; i < list.size(); i++) { // a for loop over a Linked List?! I think not! Where is the iterator? Where is the while loop? The for loop be damned in this case, forsooth!
            //for (int j = 0; j < wordMap.size(); j++) { // Wait. A nested for loop? What is this demon sorcery?
                String thisWord = lowerCase(list.get(i));  //Wait, you're lower-casing it inside the nested for-loop each time? Just do this at the beginning when you're using your reader!
                if (wordMap.containsKey(thisWord)) { //It is already in the map. 
                    wordMap.put(thisWord, wordMap.get(thisWord) + 1);
                }
                else { //It ain't on the Map.
                    wordMap.put(thisWord, 1); //Now it is.
                    wordSet.add(thisWord);
                }
            //}                
        }
        printItInnefficiently(wordMap, wordSet);
    }

    private static String lowerCase(String string) {
        StringBuilder output = new StringBuilder();
        
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) < 97) {
                char newChar = (char) (string.charAt(i) + 97 - 65);
                output.append(newChar);
            }
            else {
                output.append(string.charAt(i));
            }
        }
        return output.toString(); //honestly, probably could have done this less efficiently.
    }
    
    private static void printItInnefficiently(Map<String, Integer> wordMap, List<String> wordSet) {
        for (int i = 0; i < wordSet.size(); i++) { //Another for loop on a Linked List? Sheesh, why don't you just nest these for loops at this point?
            System.out.println(wordSet.get(i) + " " + wordMap.get(wordSet.get(i)));
        }
    }
}
