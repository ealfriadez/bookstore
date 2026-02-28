package pe.edu.unfv.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.unfv.bookstore.model.Author;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    //QUERY METHOD
    List<Author> findByNationality(String nationality);

    //JPQL
    @Query("""
            SELECT a FROM Author a
            WHERE LOWER(a.lastName) LIKE LOWER(CONCAT('%', :lastname, '%'))
            """)
    List<Author> searchByLastName(@Param("lastname") String lastName);

    //SQL Nativo
    @Query(value = """
            select a.*
            from authors a
            left join books b on a.id = b.author_id
            group by a.id
            order by count(b.id) desc
        """, nativeQuery = true)
    List<Author> findAuthorsOrderedByBookCount();
}
