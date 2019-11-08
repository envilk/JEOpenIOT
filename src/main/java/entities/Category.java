package entities;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name="category")
public class Category {
	
	public Category() {}
	//Create elements ids automatically, incremented 1 by 1
	@TableGenerator(
			  name = "yourTableGeneratorCat",
			  allocationSize = 1,
			  initialValue = 1)
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="yourTableGeneratorCat")
	private int id;
	
	private String catName;
	private String catDesc;
	
	//TARGET
	@ManyToMany
	@JoinTable(name = "join_iotdev_cat",
		joinColumns = @JoinColumn(name = "category_fk"),
		inverseJoinColumns = @JoinColumn(name = "iotdevice_fk"))
	private ArrayList<IoTDevice> iotdevCategorized;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getCatDesc() {
		return catDesc;
	}
	public void setCatDesc(String catDesc) {
		this.catDesc = catDesc;
	}
	
	

	
}
