package com.jian.project1.board;

import org.apache.ibatis.annotations.Mapper;

import com.jian.project1.model.BoardEntity;

@Mapper
public interface BoardMapper {
	int insBoard(BoardEntity p);
}
