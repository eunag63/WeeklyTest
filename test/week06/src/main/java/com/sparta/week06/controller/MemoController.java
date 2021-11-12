package com.sparta.week06.controller;

import com.sparta.week06.domain.Memo;
import com.sparta.week06.dto.MemoDto;
import com.sparta.week06.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class MemoController {

    private final MemoRepository memoRepository;

    @PostMapping("/memo")
    public String saveMemo(@RequestBody MemoDto dto)  {
        memoRepository.save(new Memo(dto.getContents()));

        return "작성완료";
    }

    @GetMapping("/memo")
    public List<String> getMemo() {
        return memoRepository.findAll().stream()
                .map(m -> new String(m.getContents()))
                .collect(Collectors.toList());
    }
}