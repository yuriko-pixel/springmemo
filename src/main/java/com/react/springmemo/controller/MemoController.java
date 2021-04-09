package com.react.springmemo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.react.springmemo.entity.Memo;
import com.react.springmemo.service.MemoService;

@RestController
public class MemoController {

	@Autowired
	private MemoService memoService;

	@GetMapping
	public String getSample() {
		return "This is just the sample";
	}

	@GetMapping("/api/v1/memo/all")
	public List<Memo> getAllMemos(@RequestHeader("Authorization") String authorization) {
		System.out.println(authorization);
		List<Memo> memoList = new ArrayList<>();
		memoList = memoService.getAllMemos();
		return memoList;
	}

	@GetMapping("/api/v1/memo/{id}")
	public Memo getMemoById(@PathVariable("id") int id) {
		return memoService.getMemoByMemoid(id);
	}

	@PostMapping("/api/v1/memo")
	public void postNewMemo(@RequestBody Memo memo) {
		memoService.saveMemo(memo);
	}

	@PutMapping("/api/v1/memo")
	public void changeMemo(@RequestBody Memo memo) {
		memoService.saveMemo(memo);
	}

	@DeleteMapping("/api/v1/memo/{id}")
	public void deleteMemo(@PathVariable("id") int id) {
		memoService.removeMemo(id);
	}
}
