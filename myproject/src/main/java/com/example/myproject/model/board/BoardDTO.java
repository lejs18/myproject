package com.example.myproject.model.board;

import java.util.Arrays;
import java.util.Date;

public class BoardDTO {
	private int idx;
	private String title;
	private String contents;
	private String writer;
	private Date regdate;
	private int hit;
	private String name;
	private int cnt;
	private String[] files;

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public String[] getFiles() {
		return files;
	}

	public void setFiles(String[] files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return "BoardDTO [idx=" + idx + ", title=" + title + ", contents=" + contents + ", writer=" + writer + ", regdate="
				+ regdate + ", hit=" + hit + ", name=" + name + ", cnt=" + cnt + ", files=" + Arrays.toString(files) + "]";
	}

}
