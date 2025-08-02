package com.codility.tasks.hibernate.crud.solution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ArticleService {

```
@Autowired
private ArticleRepository articleRepository;

/**
* Find article by ID
* @param id The article ID
* @return Optional containing the article if found, empty otherwise
*/
public Optional<ArticleDTO> findById(Long id) {
Optional<Article> article = articleRepository.findById(id);
return article.map(this::convertToDTO);
}

/**
* Find articles by title (partial match, case insensitive)
* @param title The title to search for
* @return List of articles matching the title
*/
public List<ArticleDTO> findByTitle(String title) {
List<Article> articles = articleRepository.findByTitle(title);
return articles.stream()
.map(this::convertToDTO)
.toList();
}

/**
* Create a new article
* @param articleDTO The article data
* @return ID of the created article
*/
public Long create(ArticleDTO articleDTO) {
Article article = convertToEntity(articleDTO);
Article savedArticle = articleRepository.save(article);
return savedArticle.getId();
}

/**
* Update an existing article
* @param id The article ID
* @param articleDTO The updated article data
*/
public void update(Long id, ArticleDTO articleDTO) {
Optional<Article> existingArticle = articleRepository.findById(id);
if (existingArticle.isPresent()) {
Article article = existingArticle.get();
article.setTitle(articleDTO.getTitle());
article.setContent(articleDTO.getContent());
article.setTags(articleDTO.getTags());
articleRepository.save(article);
}
}

/**
* Delete an article by ID
* @param id The article ID to delete
*/
public void delete(Long id) {
if (articleRepository.existsById(id)) {
articleRepository.deleteById(id);
}
}

/**
* Get all articles
* @return List of all articles
*/
public List<ArticleDTO> findAll() {
return articleRepository.findAll().stream()
.map(this::convertToDTO)
.toList();
}

/**
* Find articles by tags
* @param tags List of tags to search for
* @return List of articles containing any of the specified tags
*/
public List<ArticleDTO> findByTags(List<String> tags) {
return articleRepository.findByTagsIn(tags).stream()
.map(this::convertToDTO)
.toList();
}

// Helper methods for conversion between Entity and DTO
private ArticleDTO convertToDTO(Article article) {
return new ArticleDTO(
article.getTitle(),
article.getContent(),
article.getTags()
);
}

private Article convertToEntity(ArticleDTO dto) {
return new Article(
dto.getTitle(),
dto.getContent(),
dto.getTags()
);
}
```

}
