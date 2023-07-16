package com.dongne.dongnebe.domain.likes.board_comment_likes.service;

import com.dongne.dongnebe.domain.comment.board_comment.entity.BoardComment;
import com.dongne.dongnebe.domain.likes.board_comment_likes.entity.BoardCommentLikes;
import com.dongne.dongnebe.domain.likes.board_comment_likes.repository.BoardCommentLikesRepository;
import com.dongne.dongnebe.domain.user.entity.User;
import com.dongne.dongnebe.global.dto.ResponseDto;
import com.dongne.dongnebe.global.exception.common.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import static com.dongne.dongnebe.global.service.GlobalService.validatePermission;

@Service
@RequiredArgsConstructor
public class BoardCommentLikesService {
    private final BoardCommentLikesRepository boardCommentLikesRepository;

    public ResponseDto checkBoardCommentLikes(Long boardCommentId,
                                              Authentication authentication) {
        boardCommentLikesRepository.save(BoardCommentLikes.builder()
                .user(User.builder().userId(authentication.getName()).build())
                .boardComment(BoardComment.builder().boardCommentId(boardCommentId).build())
                .build());
        return ResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .responseMessage("Check Board Comment Likes")
                .build();
    }

    public ResponseDto cancelBoardCommentLikes(Long boardCommentLikesId, Authentication authentication) {
        BoardCommentLikes boardCommentLikes = boardCommentLikesRepository.findById(boardCommentLikesId)
                .orElseThrow(() -> new ResourceNotFoundException("Board Comment Likes Id Not Found"));
        validatePermission(boardCommentLikes.getUser().getUserId(), authentication);
        boardCommentLikesRepository.deleteById(boardCommentLikesId);
        return ResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .responseMessage("Cancel Board Comment Likes")
                .build();
    }
}
