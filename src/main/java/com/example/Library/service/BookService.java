package com.example.Library.service;

import com.example.Library.dto.BookResponseDto;
import com.example.Library.dto.BookUpdateRequestDto;
import com.example.Library.entity.Book;
import com.example.Library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
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

    // 반납
    public void returnBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("책을 찾을 수 없습니다"));
        book.setAvailable(true);
        bookRepository.save(book);
    }

    public BookResponseDto updateBook(Long id, BookUpdateRequestDto request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("책을 찾을 수 없습니다"));

        // 👇 받은 값으로 필드 갱신 (이게 빠져있었음!)
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setPrice(request.getPrice());

        // @Transactional이 있으면 save() 생략 가능 (dirty checking)
        // 명시적으로 쓰고 싶다면:

        bookRepository.save(book);
        return BookResponseDto.from(book);
    }
}