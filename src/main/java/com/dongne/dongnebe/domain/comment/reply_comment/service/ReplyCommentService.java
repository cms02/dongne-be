package com.dongne.dongnebe.domain.comment.reply_comment.service;


import com.dongne.dongnebe.domain.comment.board_comment.entity.BoardComment;
import com.dongne.dongnebe.domain.comment.reply_comment.dto.*;
import com.dongne.dongnebe.domain.comment.reply_comment.entity.ReplyComment;
import com.dongne.dongnebe.domain.comment.reply_comment.repository.ReplyCommentQueryRepository;
import com.dongne.dongnebe.domain.comment.reply_comment.repository.ReplyCommentRepository;
import com.dongne.dongnebe.domain.likes.reply_comment_likes.repository.ReplyCommentLikesQueryRepository;
import com.dongne.dongnebe.domain.user.entity.User;
import com.dongne.dongnebe.global.dto.ResponseDto;
import com.dongne.dongnebe.global.exception.common.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.dongne.dongnebe.global.service.GlobalService.validatePermission;


@Service
@RequiredArgsConstructor
public class ReplyCommentService {
    private final ReplyCommentRepository replyCommentRepository;
    private final ReplyCommentQueryRepository replyCommentQueryRepository;
    private final ReplyCommentLikesQueryRepository replyCommentLikesQueryRepository;

    @Transactional
    public ResponseDto writeReplyComment(WriteReplyCommentRequestDto writeReplyCommentRequestDto,
                                         Authentication authentication) {
        replyCommentRepository.save(ReplyComment.builder()
                .content(writeReplyCommentRequestDto.getContent())
                .user(User.builder().userId(authentication.getName()).build())
                .boardComment(BoardComment.builder().boardCommentId(writeReplyCommentRequestDto.getReplyCommentId()).build())
                .build());
        return ResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .responseMessage("Write Reply Comment")
                .build();
    }

    @Transactional
    public ResponseDto updateReplyComment(Long replyCommentId, UpdateReplyCommentRequestDto updateReplyCommentRequestDto, Authentication authentication) {
        validatePermission(updateReplyCommentRequestDto.getUserId(), authentication);
        ReplyComment replyComment = replyCommentRepository.findById(replyCommentId)
                .orElseThrow(() -> new ResourceNotFoundException("Board Comment Id Not Found"));
        replyComment.update(updateReplyCommentRequestDto);
        return ResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .responseMessage("Update Reply Comment")
                .build();
    }

    @Transactional
    public ResponseDto deleteReplyComment(Long replyCommentId, DeleteReplyCommentRequestDto deleteReplyCommentRequestDto, Authentication authentication) {
        validatePermission(deleteReplyCommentRequestDto.getUserId(), authentication);
        ReplyComment replyComment = replyCommentRepository.findById(replyCommentId)
                .orElseThrow(() -> new ResourceNotFoundException("Board Comment Id Not Found"));
        replyComment.delete();
        return ResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .responseMessage("Delete Reply Comment")
                .build();
    }


    @Transactional(readOnly = true)
    public FindReplyCommentsResponseDto findReplyComments(Long boardCommentId, Pageable pageable, Authentication authentication) {
        List<ReplyComment> replyComments = replyCommentQueryRepository.findReplyComments(boardCommentId, pageable);
        List<FindReplyCommentDto> replyCommentDtos = replyComments.stream()
                .map(c -> FindReplyCommentDto.builder()
                        .replyComment(c)
                        .isLiked(checkUserReplyCommentLikes(authentication, c))
                        .build())
                .collect(Collectors.toList());

        return new FindReplyCommentsResponseDto(replyCommentDtos);
    }

    private boolean checkUserReplyCommentLikes(Authentication authentication, ReplyComment replyComment) {
        return replyCommentLikesQueryRepository.findBoardCommentLikesByBoardCommentIdAndUserId(replyComment.getReplyCommentId(), authentication.getName()).isPresent();
    }
}
