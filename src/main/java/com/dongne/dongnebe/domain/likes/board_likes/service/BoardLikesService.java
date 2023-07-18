package com.dongne.dongnebe.domain.likes.board_likes.service;

import com.dongne.dongnebe.domain.board.entity.Board;
import com.dongne.dongnebe.domain.likes.board_likes.entity.BoardLikes;
import com.dongne.dongnebe.domain.likes.board_likes.repository.BoardLikesRepository;
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
public class BoardLikesService {
    private final BoardLikesRepository boardLikesRepository;
    private final UserRepository userRepository;

    @Transactional
    public ResponseDto checkBoardLikes(Long boardId, Authentication authentication) {

        boardLikesRepository.save(BoardLikes.builder()
                .user(User.builder().userId(authentication.getName()).build())
                .board(Board.builder().boardId(boardId).build())
                .build());

        User user = userRepository.findByUserId(authentication.getName()).orElseThrow(
                () -> new ResourceNotFoundException("User Not Found")
        );
        user.plusPointByLike();

        return ResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .responseMessage("Check Board Likes")
                .build();
    }

    @Transactional
    public ResponseDto cancelBoardLikes(Long boardLikesId, Authentication authentication) {
        BoardLikes boardLikes = boardLikesRepository.findById(boardLikesId)
                .orElseThrow(() -> new ResourceNotFoundException("Board Likes Id Not Found"));
        validatePermission(boardLikes.getUser().getUserId(), authentication);
        boardLikesRepository.deleteById(boardLikesId);

        User user = userRepository.findByUserId(authentication.getName()).orElseThrow(
                () -> new ResourceNotFoundException("User Not Found")
        );
        user.minusPointByLike();
        return ResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .responseMessage("Cancel Board Likes")
                .build();
    }
}
