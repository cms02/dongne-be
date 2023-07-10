package com.dongne.dongnebe.domain.likes.reply_comment_likes.repository;

import com.dongne.dongnebe.domain.comment.reply_comment.entity.ReplyComment;
import com.dongne.dongnebe.domain.likes.board_likes.entity.BoardLikes;
import com.dongne.dongnebe.domain.likes.reply_comment_likes.entity.ReplyCommentLikes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyCommentLikesRepository extends JpaRepository<ReplyCommentLikes, Long> {
}
