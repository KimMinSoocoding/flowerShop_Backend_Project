package kr.co.sionms.jsp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Criteria {
	private int pageNum = 1; // 현재페이지 
	private int amount = 10; // 한페이지에 보여질 게시물 갯수
	private int category = 1;
	private String[] type = new String[0];
	private String keyword = "";
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public String getQueryString() {
		String str ="";
		str += "amount=" + amount + "&category=" + category;
		str += getTypeStr();
		return str;
	}
	public String getFullQueryString() {
		String str ="pageNum=" + pageNum + "&";
		str += getQueryString();
		return str;
	}
	
	public String getTypeStr() {
		String str ="";
		if(type != null) {
			for(String s : type) {
				str += "&type=" + s;
			}
			str += "&keyword=" + keyword;
		}
		return str;
	}
	
}
