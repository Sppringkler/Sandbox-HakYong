package com.sandbox.paging.controller;

import com.sandbox.paging.domain.Article;
import com.sandbox.paging.domain.ArticleWrapper;
import com.sandbox.paging.domain.CursorResponseMessage;
import com.sandbox.paging.domain.OffsetResponseMessage;
import com.sandbox.paging.service.PagingService;
import com.sandbox.todos.domain.ResponseMessage;
import com.sandbox.todos.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/articles")
public class PagingController {

    private final PagingService pagingService;

    @GetMapping("/paging/offset")
    public ResponseEntity<OffsetResponseMessage> pagingOffset(@RequestParam(value = "size") int size,
                                                      @RequestParam(value = "page") int page) {
        OffsetResponseMessage result = pagingService.findOffset(size, page);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/paging/cursor")
    public ResponseEntity<CursorResponseMessage> pagingCursor(@RequestParam(value = "size") int size,
                                                      @RequestParam(value = "cursorId") int cursorId) {
        CursorResponseMessage result = pagingService.findCursor(size, cursorId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/make")
    public void makeArticles(@RequestBody ArticleWrapper articleWrapper){
        List<Article> articles = articleWrapper.getArticles();
        pagingService.makeArticles(articles);
    }

}
