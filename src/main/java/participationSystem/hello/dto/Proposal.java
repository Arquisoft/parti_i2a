package participationSystem.hello.dto;

import common.persistence.CommonPersistence;

public class Proposal {
	private Integer id;
	private String content;
	private Integer votes;
	private Integer categoryId;
	private Integer userId;
	
	private Category category;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Proposal() {
	}

	public Proposal(String content, Integer votes, Integer categoryId, Integer usedId) {
		super();
		this.content = content;
		this.votes = votes;
		this.categoryId = categoryId;
		this.userId = usedId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getVotes() {
		return votes;
	}

	public void setVotes(Integer votes) {
		this.votes = votes;
	}

	public Integer getCategoryId() {
		return categoryId;
	}
	
	public String getCategoryName(){
		Category cat = CommonPersistence.getCategoryDao().getCategoryById(categoryId);
		return cat.getName();
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String kafkaStringFormat() {
		return String.format("%s;%d;%d;%d",content,votes,userId,categoryId);
	}

	@Override
	public String toString() {
		return "Proposal[Id: " + id + "; Content: " + content + "; Votes: " + votes + "; "
				+ "Category: " + categoryId + "; User: " + userId +"]";

	}

	@Override
	public boolean equals(Object obj) {
		return obj.toString().equals(this.toString());
	}

}
