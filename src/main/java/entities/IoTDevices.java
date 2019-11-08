package entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * @Author Enrique Vilchez and Jose Juan Peña
 * Dat250
 * 
 * The persistent class for the IOTDEVICES extension from IOTDEVICES.
 * 
 */

@XmlRootElement
@XmlSeeAlso(IoTDevice.class)
public class IoTDevices extends ArrayList<IoTDevice> {

	private static final long serialVersionUID = 1L;
	
	public IoTDevices() {
		super();
	}

	public IoTDevices(Collection<? extends IoTDevice> c) {
		super(c);
	}

	@XmlElement(name = "iotdevice")
	public List<IoTDevice> getIoTDevices() {
		return this;
	}

	public void setIoTDevices(List<IoTDevice> IoTDevices) {
		this.addAll(IoTDevices);
	}
}
