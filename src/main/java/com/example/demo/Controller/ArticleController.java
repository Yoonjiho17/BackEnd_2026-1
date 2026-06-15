package com.example.demo.Controller;

import com.example.demo.Model.Article;
import com.example.demo.Service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/article/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable Integer id) {
        Article article = articleService.getArticle(id);
        if (article == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(article);
    }

    @PostMapping("/article")
    public ResponseEntity<Article> postArticle(@RequestBody Article article) {
        Article created = articleService.postArticle(article);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/article/{id}")
    public ResponseEntity<Article> putArticle(@PathVariable Integer id, @RequestBody Article update) {
        Article updated = articleService.putArticle(id, update);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/article/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Integer id) {
        if (!articleService.deleteArticle(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
