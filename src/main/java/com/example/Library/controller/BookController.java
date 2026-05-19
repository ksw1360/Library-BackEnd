package com.example.Library.controller;

import com.example.Library.dto.BookResponseDto;
import com.example.Library.entity.Book;
import com.example.Library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private  final BookService bookService;

    // 전체 조회
    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    // 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBook(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBook(id));
    }

    // 대출
    @PatchMapping("/{id}/loan")
    public ResponseEntity<BookResponseDto> loanBook(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.loanBook(id));
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    // 추가
    @PostMapping
    public ResponseEntity<BookResponseDto> addBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.addBook(book));
    }

    // 반납
    @PatchMapping("/books/{id}/return")
    public ResponseEntity<Void> returnBook(@PathVariable Long id) {
        bookService.returnBook(id);
        return ResponseEntity.ok().build();
    }
}