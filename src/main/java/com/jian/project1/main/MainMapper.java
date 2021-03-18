package com.jian.project1.main;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jian.project1.model.BookSlideEntity;

@Mapper
public interface MainMapper {
	int addBook(BookSlideEntity p);
	List<BookSlideEntity> selBookList();
}
