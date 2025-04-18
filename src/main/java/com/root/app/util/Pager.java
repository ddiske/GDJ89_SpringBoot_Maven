package com.root.app.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Pager {
	
	private Long startNum;
	private Long perPage;
	private Long page;
	
	private Long startBlock;
	private Long endBlock;
	
	private boolean startCheck;
	private boolean endCheck;
	
	private String search;
	private String kind;
	
	public void make(Long totalCount) {
		
		Long totalPage = totalCount / this.getPerPage();
		if(totalCount % this.getPerPage() > 0) {
			totalPage++;
		}
		
		Long totalBlock = totalPage / 5;
		if(totalPage % 5 > 0) {
			totalBlock++;
		}
		
		Long curBlock = (this.getPage() + 4) / 5;
		Long startBlock = curBlock * 5 - 4;
		Long endBlock = curBlock * 5;
		
		this.startBlock = startBlock;
		this.endBlock = endBlock;
		
		if(curBlock == 1) {
			this.startCheck = true;
		}
		
		if(curBlock == totalBlock) {
			this.endBlock = totalPage;
			this.endCheck = true;
		}
		
		if(this.getPage() > totalPage) {
			this.page = totalPage;
		}
		
	}
	
	public Long getStartNum() {
		this.startNum = (this.getPage()-1)*this.getPerPage();
		return startNum;
	}

	public Long getPerPage() {
		if(this.perPage == null) {
			this.perPage = 10L;
		}
		return perPage;
	}


	public Long getPage() {
		if(this.page == null || this.page < 1) {
			this.page = 1L;
		}
		return page;
	}
	
	public String getKind() {
		if(this.kind == null || this.kind == "") {
			this.kind = "k1";
		}
		return this.kind;
	}
	
	public String getSearch() {
		if(this.search == null) {
			this.search = "";
		}
		return this.search;
	}

}
