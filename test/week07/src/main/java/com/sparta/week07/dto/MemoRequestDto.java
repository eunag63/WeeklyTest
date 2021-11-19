package com.sparta.week07.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemoRequestDto {
    private String username;
    private String contents;
}
