package com.example.Library.dto;

import com.example.Library.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;

// 도서 목록/단건 조회 응답용
@Getter
@AllArgsConstructor
public class BookResponseDto {
    private Long id;
    private String title;
    private String author;
    private Integer price;
    private Boolean available;

    public static BookResponseDto from(Book book) {
        return new BookResponseDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPrice(),
                book.getAvailable()
        );
    }
}