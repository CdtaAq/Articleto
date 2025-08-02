package com.codility.tasks.hibernate.crud.solution;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = “article”)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Article {

```
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(nullable = false)
private String title;

@Column(columnDefinition = "TEXT")
private String content;

@ElementCollection
@CollectionTable(name = "article_tags",
joinColumns = @JoinColumn(name = "article_id"))
@Column(name = "tag")
private List<String> tags;

// Constructor without id (for creating new articles)
public Article(String title, String content, List<String> tags) {
this.title = title;
this.content = content;
this.tags = tags;
}
```

}
