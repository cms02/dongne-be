package com.dongne.dongnebe.domain.likes.board_comment_likes.service;

import com.dongne.dongnebe.domain.comment.board_comment.entity.BoardComment;
import com.dongne.dongnebe.domain.likes.board_comment_likes.entity.BoardCommentLikes;
import com.dongne.dongnebe.domain.likes.board_comment_likes.repository.BoardCommentLikesQueryRepository;
import com.dongne.dongnebe.domain.likes.board_comment_likes.repository.BoardCommentLikesRepository;
import com.dongne.dongnebe.domain.user.entity.User;
import com.dongne.dongnebe.domain.user.repository.UserRepository;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import com.dongne.dongnebe.global.exception.common.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.dongne.dongnebe.global.service.GlobalService.validatePermission;

@Service
@RequiredArgsConstructor
public class BoardCommentLikesService {
    private final BoardCommentLikesRepository boardCommentLikesRepository;
    private final BoardCommentLikesQueryRepository boardCommentLikesQueryRepository;
    private final UserRepository userRepository;

    @Transactional
    public ResponseDto checkBoardCommentLikes(Long boardCommentId,
                                              Authentication authentication) {
        boardCommentLikesRepository.save(BoardCommentLikes.builder()
                .user(User.builder().userId(authentication.getName()).build())
                .boardComment(BoardComment.builder().boardCommentId(boardCommentId).build())
                .build());

        User user = userRepository.findByUserId(authentication.getName()).orElseThrow(
                () -> new ResourceNotFoundException("User Not Found")
        );
        user.plusPointByLike();

        return ResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .responseMessage("Check Board Comment Likes")
                .build();
    }

    @Transactional
    public ResponseDto cancelBoardCommentLikes(Long boardCommentId, Authentication authentication) {
        BoardCommentLikes boardCommentLikes = boardCommentLikesQueryRepository.findBoardCommentLikesByBoardCommentIdAndUserId(boardCommentId, authentication.getName())
                .orElseThrow(() -> new ResourceNotFoundException("Board Comment Id, UserId Not Found"));
        validatePermission(boardCommentLikes.getUser().getUserId(), authentication);
        boardCommentLikesRepository.deleteById(boardCommentLikes.getBoardCommentLikesId());

        User user = userRepository.findByUserId(authentication.getName()).orElseThrow(
                () -> new ResourceNotFoundException("User Not Found")
        );
        user.minusPointByLike();

        return ResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .responseMessage("Cancel Board Comment Likes")
                .build();
    }
}
