package pe.edu.unfv.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.unfv.bookstore.model.Author;
import pe.edu.unfv.bookstore.model.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    //QUERY METHOD
    boolean existsByTitleAndAuthorId(String title, Long authorId);

    @Query(value = """
            select *
            from books
            where price > (select AVG(price) from books)
            """, nativeQuery = true)
    List<Book> findBooksAboveAveragePrice();

    //SQL NATIVO
    @Modifying //UPDATE, INSERT OR DELETE
    @Query(value = """
            UPDATE books
            SET price = price * :factor
            WHERE editorial = :editorial
            """, nativeQuery = true)
    int updatePriceByEditorial(@Param("editorial") String editorial, @Param("factor") double factor);

    //QUERY METHOD
    List<Book> findByAuthorContaining(String author);

    /*//SQL NATIVO
    @Query(value = """
                select b.*
                from books b
                left join authors a on b.author_id  = a.id
                where lower(a.last_name) like lower(concat('%', :lastName, '%'))   
           """, nativeQuery = true)
    List<Book> searchByLastName(@Param("lastName") String lastName);*/

    //JPQL Java Persistence Query Language
    @Query(value = """
            SELECT b 
            FROM Book b 
            JOIN b.author a 
            WHERE LOWER(a.lastName) LIKE LOWER(CONCAT('%', :lastName, '%'))   
           """)
    List<Book> searchByLastName(@Param("lastName") String lastName);
}
