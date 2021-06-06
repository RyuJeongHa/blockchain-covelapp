package com.example.covel.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
    private int id;
    private String title;
    private String content;
    private String description;
    private int userId;

    public BoardDTO(int id, String title, String content, String description, int userId) {
    }
}
