package com.dongne.dongnebe.domain.board.dto.response;

import com.dongne.dongnebe.domain.board.entity.Board;
import com.dongne.dongnebe.domain.board.enums.BoardType;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import static com.dongne.dongnebe.global.service.GlobalService.formatLocalDateTimeToString;

@Getter
@SuperBuilder
public class FindOneBoardResponseDto extends ResponseDto {
    private Long boardId;
    private Long subCategoryId;
    private String subCategoryName;
    private Long channelId;
    private String channelName;
    private String boardType;
    private String title;
    private String content;
    private String createDate;
    private String deadlineAt;
    private String userId;
    private String nickname;
    private Long point;
    private String fileImg;
    private Long viewCnt;
    private Long boardCommentCount;
    private Long boardLikesCount;
    private Boolean isLiked;
    private Long preBoardId;
    private String preBoardTitle;
    private Long nextBoardId;
    private String nextBoardTitle;
    private Long boardLikesId;


    public FindOneBoardResponseDto(Board board, Long boardLikesId , Board preBoard, Board nextBoard) {
        super(HttpStatus.OK.value(), "Find One Board");
        this.boardId = board.getBoardId();
        this.subCategoryId = board.getSubCategory() == null ? null : board.getSubCategory().getSubCategoryId();
        this.subCategoryName = board.getSubCategory() == null ? null : board.getSubCategory().getName();
        this.channelId = board.getChannel() == null ? null : board.getChannel().getChannelId();
        this.channelName = board.getChannel() == null ? null : board.getChannel().getName();
        this.boardType = board.getBoardType().getValue();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.createDate = formatLocalDateTimeToString(board.getCreateDate());
        this.deadlineAt = formatLocalDateTimeToString(board.getDeadlineAt());
        this.userId = board.getUser().getUserId();
        this.nickname = board.getUser().getNickname();
        this.point = board.getUser().getPoint();
        this.fileImg = board.getFileImg();
        this.viewCnt = board.getViewCnt();
        this.boardCommentCount = (long) board.getBoardComments().size();
        this.boardLikesCount = (long) board.getBoardLikes().size();
        this.isLiked = boardLikesId == null? false : true;
        this.boardLikesId = boardLikesId;
        this.preBoardId = preBoard == null ? null : preBoard.getBoardId();
        this.preBoardTitle = preBoard == null ? null : preBoard.getTitle();
        this.nextBoardId = nextBoard == null ? null : nextBoard.getBoardId();
        this.nextBoardTitle = nextBoard == null ? null : nextBoard.getTitle();
    }

}
