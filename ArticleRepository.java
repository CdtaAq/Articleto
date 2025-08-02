package com.codility.tasks.hibernate.crud.solution;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

```
// Find articles by title
List<Article> findByTitleContainingIgnoreCase(String title);

// Custom query to find by exact title
@Query("SELECT a FROM Article a WHERE a.title = :title")
List<Article> findByTitle(@Param("title") String title);

// Find articles that contain specific tags
@Query("SELECT DISTINCT a FROM Article a JOIN a.tags t WHERE t IN :tags")
List<Article> findByTagsIn(@Param("tags") List<String> tags);
```

}
