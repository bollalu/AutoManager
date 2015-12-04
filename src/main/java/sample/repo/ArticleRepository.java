package sample.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sample.model.Article;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> 
{
    public Iterable<Article> findArticlesByTitle(@Param("title") String title);
    public Iterable<Article> findByTitleContainingIgnoreCase(String title);
}