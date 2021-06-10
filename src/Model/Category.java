package Model;

import org.json.simple.JSONObject;

public class Category extends Model{
	private String name;
	private String description;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Category() {
		
	}
	public Category(int id) {
		super(id);
	}
	public Category(int id, String name, String description) {
		super(id);
		this.name = name;
		this.description = description;
	}
	public Category(String name, String description) {
		this.name = name;
		this.description = description;
	}
	@Override
	public String toString() {
		return "Category [name=" + name + ", description=" + description + ", id=" + id + "]";
	}
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSON() {
		JSONObject element = super.toJSON();
		element.put("name", this.name);
		element.put("description", this.description);
		return element;
	}
	

}
