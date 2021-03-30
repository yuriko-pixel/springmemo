package com.react.springmemo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Table(name = "memo")
@Getter
@Setter
@NoArgsConstructor
public class Memo {
	@Id
	@Column(name = "memoid")
	private int memoId;

	//memoの内容をユーザIDと紐づける
	@Column(name = "userid")
	private String userId;

	@Column(name = "memotitle")
	private String memoTitle;

	//memoの内容はHTMLで保存する
	@Column(name = "memohtml")
	private String memoHtml;

	//memo作成日付。updateしたときはこれを更新する
	@Column(name = "memodate")
	private Date memoDate;

	@Column(name = "isdeleted")
	private boolean isDeleted;

	@Column(name = "memomarkd")
	private String memomarkd;

	public Memo(@JsonProperty("memoid") int memoId,
				@JsonProperty("userid") String userId,
				@JsonProperty("memotitle") String memoTitle,
				@JsonProperty("memohtml") String memoHtml,
				@JsonProperty("memodate") Date memoDate,
				@JsonProperty("isdeleted") boolean isDeleted,
				@JsonProperty("memomarkd") String memomarkd) {

		this.memoId = memoId;
		this.userId = userId;
		this.memoTitle = memoTitle;
		this.memoHtml = memoHtml;
		this.memoDate = memoDate;
		this.isDeleted = isDeleted;
		this.memomarkd = memomarkd;
	}

}