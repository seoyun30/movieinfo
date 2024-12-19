package com.example.movieinfo.Entity;

import jakarta.persistence.*;
import lombok.*;

/*주요 어노테이션
@Entity, @Table, @Id, @GeneratedValue, @SequenceGenerator
@Column, @ManyToOne, @OneToMany, @OneToOne, @ManyToMany
@JoinColumn, @Transeient, @JoinTable, @OrderBy
*/
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "movie")
public class MovieEntity {

    @Id //생략불가능
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer code; //일련번호
    private String name;
    private String director;
    private String nation;
    private String genre;
    private String actor;
    private String opendate;
    private String synopsis;
    private String poster;
    //@Column 생략시 변수명과 동일한 이름으로 자동적용, 길이 생략시 기본값으로 자동지정
}

/* 작업후
1. 프로그램(서버) 실행
2. 브라우저에서 localhost:8080/h2-console 접속
3. url은 allication.properites에 datasource.url주소를 입력
    spring.datasource.url=    jdbc:h2:~/db/moviedb;(이부분)
4. Entity의 테이블이름과 동일한 테이블이 존재하는지 확인
    @Table(name = "movie")
 */
