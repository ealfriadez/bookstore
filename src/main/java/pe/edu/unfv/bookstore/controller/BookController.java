package pe.edu.unfv.bookstore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.unfv.bookstore.model.Book;

import java.util.ArrayList;
import java.util.List;

@RestController//soportar metodos HTTP como GET, POST, PUT, DELETE
                //Respuestas en formato JSON
@RequestMapping("/api/books")
public class BookController {

   private List<Book> books = new ArrayList<>(List.of(
            new Book(1L, "Clean Code", "Robert C. Martin", 45.00),
            new Book(2L, "The Pragmatic", "Andy Hunt", 50.00),
            new Book(3L, "Refactoring", "Martin Fowler", 55.00)
    ));

   private Long contadorId = 4L;

    //GET /api/books -> Listar todos los libros
    @GetMapping
    public ResponseEntity<List<Book>> getAll() {
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    //GET /api/books/{id} -> Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable Long id){
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return new ResponseEntity<>(book, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
