package pe.edu.unfv.bookstore.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String  title;
    private Double price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Editorial editorial;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
}
