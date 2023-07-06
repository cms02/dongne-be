package com.dongne.dongnebe.domain.board.repository;

import com.dongne.dongnebe.domain.board.entity.Board;
import com.dongne.dongnebe.domain.city.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
