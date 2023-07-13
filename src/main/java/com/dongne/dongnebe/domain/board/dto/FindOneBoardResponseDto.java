package com.dongne.dongnebe.domain.board.dto;

import com.dongne.dongnebe.domain.board.entity.Board;
import com.dongne.dongnebe.domain.comment.board_comment.dto.BoardCommentDto;
import com.dongne.dongnebe.global.dto.ResponseDto;
import com.dongne.dongnebe.global.service.GlobalService;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

import static com.dongne.dongnebe.global.service.GlobalService.formatLocalDateTimeToString;

@Getter
@SuperBuilder
public class FindOneBoardResponseDto extends ResponseDto {
    private Long boardId;
    private String title;
    private String content;
    private String createDate;
    private String deadlineAt;
    private String userId;
    private String fileImg;
    private Long viewCnt;
    private Long boardCommentCount;
    private String channelName;
    private List<BoardCommentDto> boardCommentDtos;

    public FindOneBoardResponseDto(Board board) {
        super(HttpStatus.OK.value(), "Find One Board");
        this.boardId = board.getBoardId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.createDate = formatLocalDateTimeToString(board.getCreateDate());
        this.deadlineAt = formatLocalDateTimeToString(board.getDeadlineAt());
        this.userId = board.getUser().getUserId();
        this.fileImg = board.getFileImg();
        this.viewCnt = board.getViewCnt();
        this.boardCommentCount = board.getBoardComments().stream().count();
        this.channelName = board.getChannel() == null ? null : board.getChannel().getName();
        this.boardCommentDtos = board.getBoardComments().stream().map(BoardCommentDto::new).collect(Collectors.toList());

    }
}
