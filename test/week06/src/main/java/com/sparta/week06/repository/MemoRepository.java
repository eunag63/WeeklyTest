package com.sparta.week06.repository;

import com.sparta.week06.domain.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<Memo, Long> {
}
