package pe.edu.unfv.bookstore.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    private Long id;
    private String  title;
    private String author;
    private double price;
}
