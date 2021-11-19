package com.sparta.week07.repository;

import com.sparta.week07.domain.Memo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemoRepository extends JpaRepository<Memo, Long> {
}
