package com.example.covel.DTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardDTO {
    private int id;
    private String title;
    private String content;
    private String description;
    private int userId;
    private String imagePath;
}
