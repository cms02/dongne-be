package com.dongne.dongnebe.domain.likes.reply_comment_likes.service;

import com.dongne.dongnebe.domain.comment.reply_comment.entity.ReplyComment;
import com.dongne.dongnebe.domain.likes.reply_comment_likes.entity.ReplyCommentLikes;
import com.dongne.dongnebe.domain.likes.reply_comment_likes.repository.ReplyCommentLikesRepository;
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
public class ReplyCommentLikesService {
    private final ReplyCommentLikesRepository replyCommentLikesRepository;
    private final UserRepository userRepository;

    @Transactional
    public ResponseDto checkReplyCommentLikes(Long replyCommentId, Authentication authentication) {
        replyCommentLikesRepository.save(ReplyCommentLikes.builder()
                .user(User.builder().userId(authentication.getName()).build())
                .replyComment(ReplyComment.builder().replyCommentId(replyCommentId).build())
                .build());

        User user = userRepository.findByUserId(authentication.getName()).orElseThrow(
                () -> new ResourceNotFoundException("User Not Found")
        );
        user.plusPointByLike();
        return ResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .responseMessage("Check Reply Comment Likes")
                .build();
    }
    @Transactional
    public ResponseDto cancelReplyCommentLikes(Long replyCommentLikesId, Authentication authentication) {
        ReplyCommentLikes replyCommentLikes = replyCommentLikesRepository.findById(replyCommentLikesId)
                .orElseThrow(() -> new ResourceNotFoundException("Reply Comment Likes Id Not Found"));
        validatePermission(replyCommentLikes.getUser().getUserId(), authentication);
        replyCommentLikesRepository.deleteById(replyCommentLikesId);
        User user = userRepository.findByUserId(authentication.getName()).orElseThrow(
                () -> new ResourceNotFoundException("User Not Found")
        );
        user.minusPointByLike();
        return ResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .responseMessage("Cancel Reply Comment Likes")
                .build();
    }
}
