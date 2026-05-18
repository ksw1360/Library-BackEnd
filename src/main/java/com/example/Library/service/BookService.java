package com.example.Library.service;

import com.example.Library.dto.BookResponseDto;
import com.example.Library.entity.Book;
import com.example.Library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    // 전체 조회
    public List<BookResponseDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookResponseDto::from)
                .toList();
    }

    // 단건 조회
    public BookResponseDto getBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 도서입니다."));
        return BookResponseDto.from(book);
    }

    // 대출 (available: true → false)
    public BookResponseDto loanBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 도서입니다."));

        if (!book.getAvailable()) {
            throw new RuntimeException("이미 대출 중인 도서입니다.");
        }

        book.setAvailable(false);
        return BookResponseDto.from(bookRepository.save(book));
    }

    // 삭제
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("존재하지 않는 도서입니다.");
        }
        bookRepository.deleteById(id);
    }

    // 추가
    public BookResponseDto addBook(Book book) {
        return BookResponseDto.from(bookRepository.save(book));
    }
}