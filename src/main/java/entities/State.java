package entities;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name="state")
public class State {

	public State() {}
	//Create elements ids automatically, incremented 1 by 1
	@TableGenerator(
			name = "yourTableGeneratorState",
			allocationSize = 1,
			initialValue = 1)
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="yourTableGeneratorState")
	private int id;

	//TARGET
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "state")
	private ArrayList<IoTDevice> iotdev;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String stateName;


	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

}
