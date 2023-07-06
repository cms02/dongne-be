package com.dongne.dongnebe.domain.comment.reply_comment.repository;

import com.dongne.dongnebe.domain.comment.board_comment.entity.BoardComment;
import com.dongne.dongnebe.domain.comment.reply_comment.entity.ReplyComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyCommentRepository extends JpaRepository<ReplyComment, Long> {

}
