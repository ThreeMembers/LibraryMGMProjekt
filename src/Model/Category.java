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
	public Category(int id, String name) {
		super(id);
		this.name = name;
	}
	@Override
	public String toString() {
		return "Category [name=" + name + ", description=" + description + ", id=" + id + "]";
	}
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSON() {
		JSONObject element = super.toJSON();
		if(this.name != null)
			element.put("name", this.name);
		if(this.description != null)
			element.put("description", this.description);
		return element;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	

}
