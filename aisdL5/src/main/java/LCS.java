
import java.util.Arrays;

/**
 * Created by malbor806 on 16.05.2017.
 */
public class LCS {
    private int[][] tab;
    private int firstWordLength;
    private int secondWordLength;
    private long counter;

    public LCS() {
        firstWordLength = 0;
        secondWordLength = 0;
        counter = 0;
    }

    public void getLCS(String firstWord, String secondWord, boolean printValue) {
        firstWordLength = firstWord.length();
        secondWordLength = secondWord.length();
        counter = 0;
        createSubsequenceLengthArray(firstWord, secondWord);
        printLCS(firstWord, secondWord, printValue);
    }

    private void createSubsequenceLengthArray(String firstWord, String secondWord) {
        tab = new int[firstWordLength + 1][secondWordLength + 1];
        for (int i = 0; i < firstWordLength; i++) {
            tab[i][0] = 0;
        }
        for (int j = 0; j < secondWordLength; j++) {
            tab[0][j] = 0;
        }
        for (int i = 1; i < firstWordLength + 1; i++) {
            for (int j = 1; j < secondWordLength + 1; j++) {
                if (firstWord.charAt(i - 1) == (secondWord.charAt(j - 1))) {
                    tab[i][j] = tab[i - 1][j - 1] + 1;
                } else if (tab[i - 1][j] >= tab[i][j - 1]) {
                    tab[i][j] = tab[i - 1][j];
                } else {
                    tab[i][j] = tab[i][j - 1];
                }
                counter++;
            }
        }
    }

    private void printLCS(String firstWord, String secondWord, boolean printValue) {
        int l = tab[firstWordLength][secondWordLength];
        Character[] result = new Character[l];
        int i = firstWordLength, j = secondWordLength;
        while (i > 0 && j > 0) {
            if (firstWord.charAt(i - 1) == (secondWord.charAt(j - 1))) {
                result[l - 1] = firstWord.charAt(i - 1);
                i--;
                j--;
                l--;
            } else if (tab[i - 1][j] >= tab[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        if (printValue) {
            System.out.println(Arrays.toString(result));
        }
    }

    public long getCounter(){
        return counter;
    }

}
