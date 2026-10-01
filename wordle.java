import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class wordle {
  static char[] knownLetters;
  static char[] greyLetters;
  static String[] yellowLeters;
  static char[] greenLetters;
  static String[] wordlist;

  public static void main(String[] args) {
    // code
    try {
      wordlist = Files.readAllLines(Paths.get("wordleList.txt")).toArray(new String[0]);
    } catch (IOException e) {
      System.out.println("Wordlist \"wordleList.txt\" not loaded!");
      e.printStackTrace();
    }

    if (args.length == 4) {
      knownLetters = args[0].toCharArray();
      greyLetters = args[1].toCharArray();
      yellowLeters = args[2].concat(" ").split(",");
      greenLetters = args[3].toCharArray();
    } else {
      System.out.println("Imporoper arguments!!");
      System.exit(0);
    }
    printAll(fourthPass(thirdPass(secondPass(firstPass()))));
  }

  public static String[] firstPass() {
    List<String> wordsOut = new ArrayList<String>();
    char[] working;
    for (String word : wordlist) { // each word in
      working = word.toLowerCase().toCharArray();
      boolean Possible = true;
      for (char letter : knownLetters) {
        if (!contains(working, letter)) {
          Possible = false;
        }
      }
      if (Possible) {
        // wordsOut = toString(working);
        wordsOut.add(toString(working));
      }
    }
    return wordsOut.toArray(new String[0]);
  }

  public static String[] secondPass(String[] wordsIn) {
    List<String> wordsOut = new ArrayList<String>();
    char[] working;
    for (String word : wordsIn) { // each word in
      working = word.toCharArray();
      boolean Possible = true;
      for (char letter : greyLetters) {
        if (contains(working, letter)) {
          Possible = false;
        }
      }
      if (Possible) {
        wordsOut.add(toString(working));
      }
    }
    return wordsOut.toArray(new String[0]);
  }

  public static String[] thirdPass(String[] wordsIn) {
    // printAll(yellowLeters);
    List<String> wordsOut = new ArrayList<String>();
    for (String word : wordsIn) {
      boolean Possible = true;
      int i = 0;
      for (String yellowLets : yellowLeters) {
        for (char yellowLet : yellowLets.toCharArray()) {
          if (yellowLet == word.charAt(i)) {
            Possible = false;
          }
        }
        i++;
      }
      if (Possible) {
        wordsOut.add(word);
      }
    }
    return wordsOut.toArray(new String[0]);
  }

  public static String[] fourthPass(String[] wordsIn) {
    List<String> wordsOut = new ArrayList<String>();
    char[] working;
    for (String word : wordsIn) {
      working = word.toCharArray();
      boolean Possible = true;
      for (int i = 0; i < 5; i++) { // each letter in working
        if (greenLetters[i] != ' ') {
          if (working[i] != greenLetters[i]) {
            Possible = false;
          }
        }
      }
      if (Possible) {
        wordsOut.add(toString(working));
      }
    }
    return wordsOut.toArray(new String[0]);
  }

  // ================================== //

  public static boolean contains(char[] WordIn, char Letter) {
    for (char letter : WordIn) {
      if (letter == Letter) {
        return true;
      }
    }
    return false;
  }

  public static String toString(char[] arrayIn) {
    String working = "" + arrayIn[0];
    for (int i = 1; i < 5; i++) {
      working = working + arrayIn[i];
    }
    return working;
  }

  public static void printAll(String[] input) {
    for (String word : input) {
      System.out.println(word);
    }
  }
}