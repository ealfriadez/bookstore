package pe.edu.unfv.bookstore.service;

import org.springframework.stereotype.Service;
import pe.edu.unfv.bookstore.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private List<Book> books = new ArrayList<>(List.of(
            Book.builder().id(1L).title("Clean Code").author("Robert C. Martin").price(45.00).build(),
            Book.builder().id(2L).title("The Pragmatic").author("Andy Hunt").price(50.00).build(),
            Book.builder().id(3L).title("Refactoring").author("Martin Fowler").price(55.00).build(),
            Book.builder().id(4L).title("COBOL").author("Justin Timberland").price(65.00).build()
    ));

    public List<Book> getAll() {
        return books;
    }

    public Optional<Book> getById(Long id) {
        return books.stream().filter(book -> book.getId().equals(id)).findFirst();
    }

    public Book save(Book book){
        book.setId(books.size() + 1L);
        books.add(book);
        return book;
    }

    public boolean delete(Long id){
        for(Book book : books){
            if(book.getId().equals(id)){
                books.remove(book);
                return true;
            }
        }
        return false;
    }

    public Book update(Long id, Book updatedDetails){
        for(Book book : books){
            if(book.getId().equals(id)){
                book.setTitle(updatedDetails.getTitle());
                book.setAuthor(updatedDetails.getAuthor());
                book.setPrice(updatedDetails.getPrice());
                return book;
            }
        }
        return null;
    }

    public List<Book> searchByAuthor(String author){
        return books.stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .collect(Collectors.toList());
    }
}
