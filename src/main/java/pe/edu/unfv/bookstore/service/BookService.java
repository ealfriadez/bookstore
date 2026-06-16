package pe.edu.unfv.bookstore.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.unfv.bookstore.model.Book;
import pe.edu.unfv.bookstore.repository.AuthorRepository;
import pe.edu.unfv.bookstore.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Transactional(readOnly = true)
    public List<Book> fidAll(){
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Book> getById(Long id) {
        return bookRepository.findById(id);
    }

    @Transactional
    public Book save(Book book){
        Long authorId = book.getAuthor().getId();
        var author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + authorId));

        boolean exists = bookRepository.existsByTitleAndAuthorId(book.getTitle(), authorId);
        if(exists) throw new RuntimeException("Book with title '" + book.getTitle() + "' already exists for this author.");

        book.setAuthor(author);
        return bookRepository.save(book);
    }

    @Transactional(readOnly = true)
    public List<Book> searchByAuthor(String author){
        return bookRepository.searchByLastName(author);
    }

    @Transactional
    public Book update(Long id, Book updatedDetails){

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));

        Long authorId = updatedDetails.getAuthor().getId();
        var author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + authorId));

        boolean exists = bookRepository.existsByTitleAndAuthorId(updatedDetails.getTitle(), authorId);
        if(exists && !book.getTitle().equals(updatedDetails.getTitle())) throw new RuntimeException("Book with title '" + updatedDetails.getTitle() + "' already exists for this author.");

        book.setTitle(updatedDetails.getTitle());
        book.setAuthor(author);
        book.setPrice(updatedDetails.getPrice());
        return bookRepository.save(book);
    }
}
