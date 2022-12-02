package model1.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class boardDTO {
	private String num;
	private String title;
	private String content;
	private String id;
	private java.sql.Date postdate;
	private String visitcount;
	private String name;
}