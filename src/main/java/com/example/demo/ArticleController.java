package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ArticleController {

    private Map<Integer, Article> articleMap = new HashMap<>();
    private int idCount = 0;

    @GetMapping("/article/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable Integer id) {
        Article article = articleMap.get(id);

        if (article == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(article);
    }

    @PostMapping("/article")
    public ResponseEntity<Article> postArticle(@RequestBody Article article) {
        idCount++;
        article.setId(idCount);

        articleMap.put(idCount, article);
        return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }

    @PutMapping("/article/{id}")
    public ResponseEntity<Article> putArticle(@PathVariable Integer id, @RequestBody Article update) {
        Article article = articleMap.get(id);

        if (article == null) {
            return ResponseEntity.notFound().build();
        }

        article.setTitle(update.getTitle());
        article.setContent(update.getContent());
        return ResponseEntity.ok(article);
    }

    @DeleteMapping("/article/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Integer id) {
        if (!articleMap.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        articleMap.remove(id);
        return ResponseEntity.noContent().build();
    }
}
