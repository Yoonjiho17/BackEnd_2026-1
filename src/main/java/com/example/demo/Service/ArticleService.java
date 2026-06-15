package com.example.demo.Service;

import com.example.demo.Model.Article;
import com.example.demo.Repository.ArticleRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private int idCount = 0;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Article getArticle(Integer id) {
        return articleRepository.findById(id);
    }

    public Article postArticle(Article article) {
        idCount++;
        article.setId(idCount);
        article.setCreatedAt(LocalDateTime.now());
        return articleRepository.save(article);
    }

    public Article putArticle(Integer id, Article update) {
        Article article = articleRepository.findById(id);
        if (article == null) {
            return null;
        }
        article.setTitle(update.getTitle());
        article.setContent(update.getContent());
        article.setAuthor(update.getAuthor());
        return articleRepository.save(article);
    }

    public boolean deleteArticle(Integer id) {
        if (!articleRepository.existById(id)) {
            return false;
        }
        articleRepository.delete(id);
        return true;
    }
}
