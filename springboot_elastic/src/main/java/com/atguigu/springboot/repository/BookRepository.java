package com.atguigu.springboot.repository;

import com.atguigu.springboot.bean.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface BookRepository extends ElasticsearchRepository<Book,Integer> {
    public List<Book> findByBookNameLike(String bookName);
}
