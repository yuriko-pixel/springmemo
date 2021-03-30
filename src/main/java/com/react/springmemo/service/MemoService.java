package com.react.springmemo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.react.springmemo.entity.Memo;
import com.react.springmemo.repository.MemoRepository;

@Service
public class MemoService {
	@Autowired
	private MemoRepository repository;

	public List<Memo> getAllMemos() {
		List<Memo> memoList = new ArrayList<>();
		repository.findAll().forEach(memoList::add);

		return memoList;
	}

	public Memo getMemoByMemoid(int id) {
		return repository.getMemoByMemoId(id);
	}

	public void saveMemo(Memo memo) {
		repository.save(memo);
	}

	public void removeMemo(int id) {
		repository.deleteById(Long.valueOf(id));
	}
}
