package com.example.myproject.model.board;

import java.util.Date;

public class ReplyDTO {
	private int idx;
	private int board_idx;
	private String reply_text;
	private String replyer;
	private String name;
	private Date regdate;
	private String writer;

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getBoard_idx() {
		return board_idx;
	}

	public void setBoard_idx(int board_idx) {
		this.board_idx = board_idx;
	}

	public String getReply_text() {
		return reply_text;
	}

	public void setReply_text(String reply_text) {
		this.reply_text = reply_text;
	}

	public String getReplyer() {
		return replyer;
	}

	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	@Override
	public String toString() {
		return "ReplyDTO [idx=" + idx + ", board_idx=" + board_idx + ", reply_text=" + reply_text + ", replyer=" + replyer
				+ ", name=" + name + ", regdate=" + regdate + ", writer=" + writer + "]";
	}

}
