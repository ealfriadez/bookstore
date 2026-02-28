package pe.edu.unfv.bookstore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.unfv.bookstore.model.Book;
import pe.edu.unfv.bookstore.service.BookService;

import java.util.List;

@RestController//soportar metodos HTTP como GET, POST, PUT, DELETE
@RequestMapping("/api/books")
public class BookController {

    //IoD
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    private Long contadorId = 5L;

    //GET /api/books -> Listar todos los libros
    @GetMapping
    public ResponseEntity<List<Book>> getAll(){
        return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
    }

    //POST /api/books -> Crear un nuevo libro
    @PostMapping
    public ResponseEntity<Book> save(@RequestBody Book book){
        return new ResponseEntity<>(bookService.save(book), HttpStatus.CREATED);
    }

    /*//GET /api/books/{id} -> Buscar por ID (@PathVariable)
    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable Long id){
        return bookService.getById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //GET  /api/books/search?author=Martin -> Buscar por autor (@RequestParam)
    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchByAuthor(
            @RequestParam(required = false) String author
    ){
        List<Book> booksFiltered = new ArrayList<>();
        for(Book book: bookService){
            if(book.getAuthor().contains(author)) booksFiltered.add(book);
        }
        return new ResponseEntity<>(booksFiltered, HttpStatus.OK);
    }

    //PUT /api/books/{id} -> Actualizar un nuevo libro
    @PutMapping("/{id}")
    public ResponseEntity<Book> update(
            @PathVariable Long id,
            @RequestBody Book book
    ){
        for(Book b: books){
            if(b.getId().equals(id)){
                b.setTitle(book.getTitle());
                b.setAuthor(book.getAuthor());
                b.setPrice(book.getPrice());
                return new ResponseEntity<>(b, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(book, HttpStatus.NOT_FOUND);
    }

    //DELETE /api/books/{id} -> Borrar un libro por ID (@PathVariable)
    @DeleteMapping("/{id}")
    public ResponseEntity<Book> delete(
            @PathVariable Long id
    ){
        for(Book b: books){
            if(b.getId().equals(id)){
                books.remove(b);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }*/
}
