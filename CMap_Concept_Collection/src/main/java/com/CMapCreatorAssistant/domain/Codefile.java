package com.CMapCreatorAssistant.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="codefile")
public class Codefile {
	@Id
	@Column(name = "ID")
	private String id;
	@Column(name = "FILENAME")
	private String fileName;
	@Column(name = "CONTENT")
	private String content;

	public Codefile() {  }

	public Codefile(String id, String fileName, String content) {
		this.setId(id);
		this.setFileName(fileName);
		this.setContent(content);
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "File{" +
				"id=" + id +
				", FileName='" + fileName + '\'' +
				", content='" + content + '\'' +
				'}';
	}
}
