package com.dongne.dongnebe.domain.likes.reply_comment_likes.repository;

import com.dongne.dongnebe.domain.comment.reply_comment.entity.ReplyComment;
import com.dongne.dongnebe.domain.likes.board_likes.entity.BoardLikes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyCommentLikesRepository extends JpaRepository<ReplyComment, Long> {
}
