package com.dongne.dongnebe.domain.comment.board_comment.repository;

import com.dongne.dongnebe.domain.board.entity.Board;
import com.dongne.dongnebe.domain.comment.board_comment.entity.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Long> {

}
