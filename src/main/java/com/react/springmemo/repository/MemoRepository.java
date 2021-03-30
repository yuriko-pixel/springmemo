package com.react.springmemo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.react.springmemo.entity.Memo;

@Repository
public interface MemoRepository extends CrudRepository<Memo, Long>{
	public Memo getMemoByMemoId(int id);
}
