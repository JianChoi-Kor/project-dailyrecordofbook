package com.jian.project1.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jian.project1.model.BoardDomain;
import com.jian.project1.model.BoardEntity;
import com.jian.project1.model.BoardPagingVO;

@Mapper
public interface BoardMapper {
	int insBoard(BoardEntity p);
	BoardDomain selBoard(BoardDomain p);
	List<BoardDomain> selBoardList(BoardPagingVO vo);
	int selTotalCountOfItem(BoardPagingVO vo);
}
