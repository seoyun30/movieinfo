package com.example.movieinfo.Controller;

import com.example.movieinfo.DTO.MovieDTO;
import com.example.movieinfo.Service.MovieService;
import com.example.movieinfo.Util.PagenationUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@Log
//swagger로 클래스이름을 정의
@Tag(name = "MovieController", description = "영화정보")
public class MovieController {
    private final MovieService movieService;
    private final PagenationUtil pagenationUtil; //페이지번호 처리 클래스

    @Operation(summary = "등록폼", description = "등록폼 페이지로 이동한다")
    @GetMapping("/register")
    public String registerHTML() {
        return "register";
    }

    @Operation(summary = "등록처리", description = "DB에 등록 후 목록으로 이동한다. ")
    @PostMapping("/register")
    public String registerService(MovieDTO movieDTO, MultipartFile imagefile) {
        movieService.insert(movieDTO, imagefile);

        return "redirect:/list";
    }

    @Operation(summary = "수정폼", description = "해당 데이터 조회 후 수정폼으로 이동한다.")
    @GetMapping("/modify")
    public String modifyServiceHTML(Integer code, Model model) {
        MovieDTO movieDTO = movieService.read(code);
        model.addAttribute("movieDTO", movieDTO);
        return "modify";
    }

    @Operation(summary = "수정처리", description = "수정할 내용을 DB에 저장 후 목록으로 이동한다.")
    @PostMapping("/modify")
    public String modifyService(MovieDTO movieDTO, MultipartFile imagefile) {
        movieService.update(movieDTO, imagefile);

        return "redirect:/list";
    }

    @Operation(summary = "삭제폼", description = "해당 번호를 삭제 후 목록으로 이동한다.")
    @GetMapping("/remove")
    public String removeServiceRegister(Integer code) {
        movieService.delete(code);

        return "redirect:/list";
    }

    @Operation(summary = "전체조회", description = "해당페이지번호의 데이터를 조회한다.")
    @GetMapping( {"/","index","list"} )
    public String listServiceHTML(@PageableDefault(page = 1) Pageable page, Model model) {
        Page<MovieDTO> movieDTOS = movieService.list(page);
        Map<String, Integer> pageInfo = PagenationUtil.pagination(movieDTOS);
        model.addAttribute("movieDTO", movieDTOS);
        model.addAllAttributes(pageInfo);
        return "list";
    }

    @Operation(summary = "개별조회", description = "해당번호의 데이터를 조회한다.")
    @GetMapping("/read")
    public String readServiceHTML(Integer code, Model model) {
        MovieDTO movieDTO = movieService.read(code);
        model.addAttribute("movieDTO", movieDTO);
        return "read";
    }

}

