import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/*
 * This Java source file was auto generated by running 'gradle init --type java-library'
 * by 'ptang' at '8/4/15 1:34 PM' with Gradle 2.5
 *
 * @author ptang, @date 8/4/15 1:34 PM
 */
public class LibraryTest {
    private Library library;
    private  PrintStream printStream;
    private ArrayList<Book> listOfBooks;
    @Before
    public void setup(){
        printStream = mock(PrintStream.class);
        library = new Library(printStream);

        listOfBooks = new ArrayList<>();
        listOfBooks.add(new Book("Catch-22","Joseph Heller", 1961));
        listOfBooks.add(new Book("Harry Potter and the Sorcerer's Stone", "JK Rowling", 1997));
        listOfBooks.add(new Book("Notes from the Underground", "Fyodor Dostoevsky", 1864));
        listOfBooks.add(new Book("Head First Java", "Bert Bates and Kathy Sierra", 2003));
    }

    @Test public void testSomeLibraryMethod() {
        assertTrue("someLibraryMethod should return 'true'", library.someLibraryMethod());
    }

    @Test
    public void testGetWelcomeMessage() {
        String welcomeMessage = library.getWelcomeMessage();
        assertThat(welcomeMessage, is("Welcome to the Library! Biblioteca is available! :D"));
    }

    @Test
    public void shouldPrintWelcomeWhenOpen() {
        library.open();
        verify(printStream).println("Welcome to the Library! Biblioteca is available! :D");

    }

    @Test
    public void shouldListNothingWhenLibraryCreatedWithNoBooks(){
        library.listAllBooks();
        verify(printStream, never()).println();
    }

    @Test
    public void shouldListAllBooksWhenLibraryCreatedWithBooks() { // ??? is this a dumb name

        library = new Library(printStream, listOfBooks);
        library.listAllBooks();

        verify(printStream).println(contains("Catch-22 | Joseph Heller | 1961"));
        verify(printStream).println(contains("Harry Potter and the Sorcerer's Stone | JK Rowling | 1997"));
        verify(printStream).println(contains("Notes from the Underground | Fyodor Dostoevsky | 1864"));
        verify(printStream).println(contains("Head First Java | Bert Bates and Kathy Sierra | 2003"));
    }
}
