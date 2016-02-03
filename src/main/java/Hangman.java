import static java.lang.String.valueOf;

public class Hangman {

    private String targetWord;
    private String basicFilter = "aeiou";
    private String wordTemplate;
    private int canTryTimes;
    private boolean winGame;

    public void start(String word) {
        targetWord = word;
        getWordTemplateWithFilter();
        canTryTimes = targetWord.length() + 1;
        winGame = wordTemplate.endsWith(targetWord);
    }

    private void getWordTemplateWithFilter() {
        char[] wordArray = targetWord.toCharArray();
        StringBuilder tempWord = new StringBuilder();

        for (char type : wordArray) {
            if (basicFilter.contains(valueOf(type))){
                tempWord.append(type);
            } else {
                tempWord.append("_");
            }
        }

        wordTemplate = tempWord.toString();
    }

    public int getTries() {
        return canTryTimes;
    }

    public int getWordLength() {
        return targetWord.length();
    }

    public String getWordUsed() {
        return basicFilter;
    }

    public String getWordTemplate() {
        return wordTemplate;
    }

    public void typeLetter(char newTyping) {

        if (winGame || canTryTimes == 0) {
            return;
        }

        if (!targetWord.contains(valueOf(newTyping)) || wordTemplate.contains(valueOf(newTyping))) {
            canTryTimes--;
            if (!basicFilter.contains(valueOf(newTyping)) && !wordTemplate.contains(valueOf(newTyping))) {
                basicFilter += valueOf(newTyping);
            }
        } else {
            acceptLetter(newTyping);
        }
    }

    private void acceptLetter(char newTyping) {
        char[] wordArray = targetWord.toCharArray();
        StringBuffer tempWord = new StringBuffer(wordTemplate);

        for (int i = 0; i < targetWord.length(); ++i) {
            if (wordArray[i] == newTyping) {
                tempWord.setCharAt(i, newTyping);
            }
        }

        wordTemplate = tempWord.toString();
        winGame = wordTemplate.equals(targetWord);
    }

    public boolean winGame() {
        return winGame;
    }
}

