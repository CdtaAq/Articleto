import java.util.List;

public class ArticleDTO {
private String title;
private String content;
private List<String> tags;

```
// No-args constructor
public ArticleDTO() {}

// All-args constructor
public ArticleDTO(String title, String content, List<String> tags) {
this.title = title;
this.content = content;
this.tags = tags;
}

// Getters and setters
public String getTitle() { return title; }
public void setTitle(String title) { this.title = title; }

public String getContent() { return content; }
public void setContent(String content) { this.content = content; }

public List<String> getTags() { return tags; }
public void setTags(List<String> tags) { this.tags = tags; }
```

}

// 2. Optional: REST Controller for testing
package com.codility.tasks.hibernate.crud.solution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(”/api/articles”)
public class ArticleController {

```
@Autowired
private ArticleService articleService;

@GetMapping("/{id}")
public ResponseEntity<ArticleDTO> getById(@PathVariable Long id) {
Optional<ArticleDTO> article = articleService.findById(id);
return article.map(ResponseEntity::ok)
.orElse(ResponseEntity.notFound().build());
}

@GetMapping
public List<ArticleDTO> getAll() {
return articleService.findAll();
}

@GetMapping("/search")
public List<ArticleDTO> searchByTitle(@RequestParam String title) {
return articleService.findByTitle(title);
}

@PostMapping
public ResponseEntity<Long> create(@RequestBody ArticleDTO articleDTO) {
Long id = articleService.create(articleDTO);
return ResponseEntity.ok(id);
}

@PutMapping("/{id}")
public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody ArticleDTO articleDTO) {
articleService.update(id, articleDTO);
return ResponseEntity.ok().build();
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> delete(@PathVariable Long id) {
articleService.delete(id);
return ResponseEntity.ok().build();
}
```

}

// 3. Application properties configuration (application.yml or application.properties)
/*

# For application.properties:

spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.h2.console.enabled=true
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Or for application.yml:

spring:
datasource:
url: jdbc:h2:mem:testdb
driver-class-name: org.h2.Driver
username: sa
password: password
h2:
console:
enabled: true
jpa:
database-platform: org.hibernate.dialect.H2Dialect
hibernate:
ddl-auto: create-drop
show-sql: true
properties:
hibernate:
format_sql: true
*/

// 4. Optional: Data initialization class
package com.codility.tasks.hibernate.crud.solution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

```
@Autowired
private ArticleService articleService;

@Override
public void run(String... args) throws Exception {
// Initialize some sample data
ArticleDTO article1 = new ArticleDTO(
"Spring Boot Tutorial",
"Learn Spring Boot from scratch",
Arrays.asList("spring", "java", "tutorial")
);

ArticleDTO article2 = new ArticleDTO(
"Database Design Patterns",
"Common patterns in database design",
Arrays.asList("database", "design", "patterns")
);

articleService.create(article1);
articleService.create(article2);

System.out.println("Sample data initialized!");
}
```

}
