package com.dongne.dongnebe.domain.likes.board_likes.repository;

import com.dongne.dongnebe.domain.likes.board_comment_likes.entity.BoardCommentLikes;
import com.dongne.dongnebe.domain.likes.board_likes.entity.BoardLikes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardLikesRepository extends JpaRepository<BoardLikes, Long> {
}
