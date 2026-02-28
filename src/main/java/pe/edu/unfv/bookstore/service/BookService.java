package pe.edu.unfv.bookstore.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.unfv.bookstore.model.Book;
import pe.edu.unfv.bookstore.repository.AuthorRepository;
import pe.edu.unfv.bookstore.repository.BookRepository;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Transactional(readOnly = true)
    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    @Transactional
    public Book save(Book book){
        Long authorId = book.getAuthor().getId();
        var author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Autor no encontrado: " + authorId));
        boolean exists = bookRepository.existsByTitleAndAuthorId(book.getTitle(), authorId);
        book.setAuthor(author);
        if(exists) throw new RuntimeException("El libro ya existe: " + book.getTitle());

        return bookRepository.save(book);
    }
}
