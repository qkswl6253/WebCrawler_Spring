package kr.ac.hansung.cse.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Content {
	private String title;
	private String description;
	private String thumbnail;
	private String hrefString;
}
