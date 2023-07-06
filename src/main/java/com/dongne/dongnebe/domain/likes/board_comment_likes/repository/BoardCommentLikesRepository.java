package com.dongne.dongnebe.domain.likes.board_comment_likes.repository;

import com.dongne.dongnebe.domain.likes.board_comment_likes.entity.BoardCommentLikes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardCommentLikesRepository extends JpaRepository<BoardCommentLikes, Long> {
}
