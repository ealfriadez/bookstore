package pe.edu.unfv.bookstore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.unfv.bookstore.model.Book;
import pe.edu.unfv.bookstore.service.BookService;

import java.util.List;

@RestController//soportar metodos HTTP como GET, POST, PUT, DELETE
                //Respuestas en formato JSON
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    private Long contadorId = 4L;

    //GET /api/books -> Listar todos los libros
    @GetMapping
    public ResponseEntity<List<Book>> getAll() {
        return new ResponseEntity<>(bookService.getAll(), HttpStatus.OK);
    }

   //GET /api/books/{id} -> Buscar por ID (@PathVariable)
    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable Long id){
        return bookService.getById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //GET /api/books/search?author=Martin -> Buscar por autor (@RequestParam)
    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchByAuthor(
            @RequestParam String author
    ){
        List<Book> booksFound = bookService.searchByAuthor(author);
        return new ResponseEntity<>(booksFound, HttpStatus.OK);
    }

     //POST /api/books -> Crear un nuevo libro (@RequestBody)
    @PostMapping
    public ResponseEntity<Book> save(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.save(book), HttpStatus.CREATED);
    }

    //PUT /api/books -> Crear un nuevo libro (@RequestBody)
    @PutMapping("/{id}")
    public ResponseEntity<Book> update(
            @PathVariable Long id,
            @RequestBody Book book) {
        Book updateBook = bookService.update(id, book);
        if(updateBook != null) {
            return new ResponseEntity<>(updateBook, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

     //DELETE /api/books/{id} -> Eliminar un libro por ID (@PathVariable)
    @DeleteMapping("/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id) {
        return bookService.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
