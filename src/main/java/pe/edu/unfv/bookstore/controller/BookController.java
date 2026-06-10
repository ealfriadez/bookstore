package pe.edu.unfv.bookstore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//soportar metodos HTTP como GET, POST, PUT, DELETE
@RequestMapping("/api/books")
public class BookController {

    //GET /api/books -> Listar todos los libros
    @GetMapping
    public String getBooks(){
        return "Books";
    }
}
