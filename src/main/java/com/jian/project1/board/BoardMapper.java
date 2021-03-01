package com.jian.project1.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jian.project1.model.BoardDomain;
import com.jian.project1.model.BoardEntity;

@Mapper
public interface BoardMapper {
	int insBoard(BoardEntity p);
	List<BoardDomain> selBoardList(BoardEntity p);
}
