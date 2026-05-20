package com.example.Library.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookUpdateRequestDto {
    private String title;
    private String author;
    private Integer price;  // null 허용
}
