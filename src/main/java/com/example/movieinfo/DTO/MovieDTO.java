package com.example.movieinfo.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieDTO {

    private Integer code; //일련번호
    private String name;
    private String director;
    private String nation;
    private String genre;
    private String actor;
    private String opendate;
    private String synopsis;
    private String poster;
}
