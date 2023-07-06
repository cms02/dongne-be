package com.dongne.dongnebe.domain.comment.board_comment.service;


import com.dongne.dongnebe.domain.comment.board_comment.repository.BoardCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardCommentService {
    private final BoardCommentRepository boardCommentRepository;

}
