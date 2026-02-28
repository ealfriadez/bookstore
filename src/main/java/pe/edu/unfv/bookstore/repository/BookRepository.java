package pe.edu.unfv.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.unfv.bookstore.model.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    //QUERY METHOD
    boolean existsByTitleAndAuthorId(String title, Long authorId);

    @Query(value = """
        select *
        from books b
        where b.price > (select AVG(price) from books)
        """, nativeQuery = true)
    List<Book> findBooksAboveAveragePrice();

    @Modifying
    @Query(value = """
        UPDATE books
        SET price = price * :factor
        WHERE editorial = :editorial
        """, nativeQuery = true)
    int updatePriceByEditorial(
            @Param("editorial") String editorial,
            @Param("factor") Double factor
    );
}
