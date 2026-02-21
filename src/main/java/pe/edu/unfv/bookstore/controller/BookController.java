package pe.edu.unfv.bookstore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//soportar metodos HTTP como GET, POST, PUT, DELETE
@RequestMapping("/api/books")
public class BookController {

    @GetMapping
    public String getBooks(){
        return "Books";
    }
}
