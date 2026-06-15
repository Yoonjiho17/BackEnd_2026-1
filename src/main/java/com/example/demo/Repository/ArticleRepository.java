package com.example.demo.Repository;

import com.example.demo.Model.Article;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ArticleRepository {
    private final Map<Integer, Article> articleMap = new HashMap<>();

    public Article save(Article article) {
        articleMap.put(article.getId(), article);
        return article;
    }

    public Article findById(Integer id) {
        return articleMap.get(id);
    }

    public void delete(Integer id) {
        articleMap.remove(id);
    }

    public boolean existById(Integer id) {
        return articleMap.containsKey(id);
    }
}
