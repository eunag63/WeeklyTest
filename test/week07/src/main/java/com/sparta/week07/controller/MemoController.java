package com.sparta.week07.controller;

import com.sparta.week07.domain.Memo;
import com.sparta.week07.dto.MemoRequestDto;
import com.sparta.week07.repository.MemoRepository;
import com.sparta.week07.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class MemoController {

    private final MemoRepository memoRepository;
    private final MemoService memoService;

    @PostMapping("/api/memos")
    public Memo createMemo(@RequestBody MemoRequestDto requestDto) {
        memoRepository.save(new Memo(requestDto.getContents()));
        return "작성 성공";
    }
    @GetMapping("/api/memos")
    public List<Memo> getMemos() {
        return memoRepository.findAll().stream()
                .map(m -> new String(m.getContents()))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/api/memos/{id}")
    public Long deleteMemo(@PathVariable Long id) {
        memoRepository.deleteById(id);
        return id;
    }

}
