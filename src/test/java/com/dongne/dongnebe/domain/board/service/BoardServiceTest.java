package com.dongne.dongnebe.domain.board.service;

import com.dongne.dongnebe.domain.likes.board_likes.entity.BoardLikes;
import com.dongne.dongnebe.domain.likes.board_likes.repository.BoardLikesQueryRepository;
import com.dongne.dongnebe.domain.likes.board_likes.repository.BoardLikesRepository;
import com.dongne.dongnebe.domain.likes.board_likes.service.BoardLikesService;
import com.dongne.dongnebe.domain.user.entity.User;
import com.dongne.dongnebe.domain.user.repository.UserRepository;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import com.dongne.dongnebe.global.exception.common.ResourceAlreadyExistException;
import com.dongne.dongnebe.global.exception.common.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BoardServiceTest {
    @InjectMocks
    private BoardLikesService boardLikesService;

    @Mock
    private BoardLikesQueryRepository boardLikesQueryRepository;

    @Mock
    private BoardLikesRepository boardLikesRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private Authentication authentication;

//    @BeforeEach
//    public void setup() {
//        ReflectionTestUtils.setField(boardLikesService, "userService", userService);
//    }

    @Test
    public void testCheckBoardLikes_UserHasNotLikedYet() {
        // Given
        Long boardId = 1L;
        when(authentication.getName()).thenReturn("testUser");
        when(boardLikesQueryRepository.findByBoardIdAndUserId(boardId, "testUser")).thenReturn(Optional.empty());
        when(userRepository.findByUserId("testUser")).thenReturn(Optional.of(User.builder().userId("testUser").build()));

        // When
        ResponseDto responseDto = boardLikesService.checkBoardLikes(boardId, authentication);

        // Then
        assertNotNull(responseDto);
        assertEquals(HttpStatus.OK.value(), responseDto.getStatusCode());
        assertEquals("Check Board Likes", responseDto.getResponseMessage());
        verify(boardLikesRepository, times(1)).save(any(BoardLikes.class));
        verify(userRepository, times(1)).findByUserId("testUser");
    }

    @Test
    public void testCheckBoardLikes_UserAlreadyLiked() {
        // Given
        Long boardId = 1L;
        when(authentication.getName()).thenReturn("testUser");
        when(boardLikesQueryRepository.findByBoardIdAndUserId(boardId, "testUser")).thenReturn(Optional.of(BoardLikes.builder().build()));

        // When / Then
        assertThrows(ResourceAlreadyExistException.class, () -> boardLikesService.checkBoardLikes(boardId, authentication));
        verify(boardLikesRepository, never()).save(any());
        verify(userRepository, never()).findByUserId(any());
    }

    @Test
    public void testCheckBoardLikes_UserNotFound() {
        // Given
        Long boardId = 1L;
        when(authentication.getName()).thenReturn("testUser");
        when(boardLikesQueryRepository.findByBoardIdAndUserId(boardId, "testUser")).thenReturn(Optional.empty());
        when(userRepository.findByUserId("testUser")).thenReturn(Optional.empty());

        // When / Then
        assertThrows(ResourceNotFoundException.class, () -> boardLikesService.checkBoardLikes(boardId, authentication));
        verify(boardLikesRepository, never()).save(any());
    }

    @Test
    public void testCheckBoardLikes_NullParameters() {
        // When / Then
        assertThrows(IllegalArgumentException.class, () -> boardLikesService.checkBoardLikes(null, authentication));
        assertThrows(IllegalArgumentException.class, () -> boardLikesService.checkBoardLikes(1L, null));
        verify(boardLikesRepository, never()).save(any());
        verify(userRepository, never()).findByUserId(any());
    }
}
