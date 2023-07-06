package com.dongne.dongnebe.domain.comment.reply_comment.service;


import com.dongne.dongnebe.domain.comment.reply_comment.repository.ReplyCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplyCommentService {
    private final ReplyCommentRepository replyCommentRepository;


}
