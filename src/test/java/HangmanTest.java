import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class HangmanTest {

    @Test
    public void testHangmanShouldStartWithWordApple() throws Exception {

        Hangman hangman = new Hangman();
        hangman.start("apple");

        assertThat(hangman.getTries(), is(6));
        assertThat(hangman.getWordLength(), is(5));
        assertThat(hangman.getWordUsed(), is("aeiou"));
        assertThat(hangman.getWordTemplate(), is("a___e"));
    }

    @Test
    public void testHangmanShouldStartWithWordApplication() throws Exception {

        Hangman hangman = new Hangman();
        hangman.start("application");

        assertThat(hangman.getTries(), is(12));
        assertThat(hangman.getWordLength(), is(11));
        assertThat(hangman.getWordUsed(), is("aeiou"));
        assertThat(hangman.getWordTemplate(), is("a___i_a_io_"));
    }

    @Test
    public void testHangmanShouldAcceptARightLetter() throws Exception {
        Hangman hangman = new Hangman();
        hangman.start("apple");

        hangman.typeLetter('p');

        assertThat(hangman.getTries(), is(6));
        assertThat(hangman.getWordLength(), is(5));
        assertThat(hangman.getWordUsed(), is("aeiou"));
        assertThat(hangman.getWordTemplate(), is("app_e"));
    }

    @Test
    public void testHangmanShouldNotAcceptWrongLetter() throws Exception {
        Hangman hangman = new Hangman();
        hangman.start("apple");

        hangman.typeLetter('k');

        assertThat(hangman.getTries(), is(5));
        assertThat(hangman.getWordLength(), is(5));
        assertThat(hangman.getWordUsed(), is("aeiouk"));
        assertThat(hangman.getWordTemplate(), is("a___e"));
    }

    @Test
    public void testHangmanShouldNotAcceptAnExistLetter() throws Exception {
        Hangman hangman = new Hangman();
        hangman.start("apple");

        hangman.typeLetter('p');
        hangman.typeLetter('p');

        assertThat(hangman.getTries(), is(5));
        assertThat(hangman.getWordLength(), is(5));
        assertThat(hangman.getWordUsed(), is("aeiou"));
        assertThat(hangman.getWordTemplate(), is("app_e"));
    }

    @Test
    public void testHangmanShouldLostTheGame() throws Exception {
        Hangman hangman = new Hangman();
        hangman.start("apple");

        hangman.typeLetter('b');
        hangman.typeLetter('c');
        hangman.typeLetter('c');
        hangman.typeLetter('c');
        hangman.typeLetter('c');
        hangman.typeLetter('c');
        hangman.typeLetter('c');

        assertThat(hangman.getTries(), is(0));
        assertThat(hangman.getWordLength(), is(5));
        assertThat(hangman.getWordUsed(), is("aeioubc"));
        assertThat(hangman.getWordTemplate(), is("a___e"));
        assertFalse(hangman.winGame());
    }

    @Test
    public void testHangmanShouldLostTheGameWhenTryTimesIsOver() throws Exception {
        Hangman hangman = new Hangman();
        hangman.start("apple");

        hangman.typeLetter('b');
        hangman.typeLetter('c');
        hangman.typeLetter('c');
        hangman.typeLetter('c');
        hangman.typeLetter('c');
        hangman.typeLetter('c');
        hangman.typeLetter('p');
        hangman.typeLetter('l');

        assertThat(hangman.getTries(), is(0));
        assertThat(hangman.getWordLength(), is(5));
        assertThat(hangman.getWordUsed(), is("aeioubc"));
        assertThat(hangman.getWordTemplate(), is("a___e"));
        assertFalse(hangman.winGame());
    }

    @Test
    public void testHangmanShouldWinTheGameWithErrorTyping() throws Exception {
        Hangman hangman = new Hangman();
        hangman.start("apple");

        hangman.typeLetter('b');
        hangman.typeLetter('c');
        hangman.typeLetter('c');
        hangman.typeLetter('c');
        hangman.typeLetter('c');
        hangman.typeLetter('p');
        hangman.typeLetter('l');

        assertThat(hangman.getTries(), is(1));
        assertThat(hangman.getWordLength(), is(5));
        assertThat(hangman.getWordUsed(), is("aeioubc"));
        assertThat(hangman.getWordTemplate(), is("apple"));
        assertTrue(hangman.winGame());
    }

    @Test
    public void testHangmanShouldWinTheGame() throws Exception {
        Hangman hangman = new Hangman();
        hangman.start("apple");

        hangman.typeLetter('p');
        hangman.typeLetter('p');
        hangman.typeLetter('l');
        hangman.typeLetter('b');

        assertThat(hangman.getTries(), is(5));
        assertThat(hangman.getWordLength(), is(5));
        assertThat(hangman.getWordUsed(), is("aeiou"));
        assertThat(hangman.getWordTemplate(), is("apple"));
        assertTrue(hangman.winGame());
    }
}
