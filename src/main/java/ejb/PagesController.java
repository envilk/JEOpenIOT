package ejb;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named(value = "pagesController")
@RequestScoped
public class PagesController {

	public String fromUserProfileToUserSettings() {
		return "UserSettings";
	}
	
	public String fromUserProfileToDeviceSettings() {
		return "DeviceSettings";
	}
	
	public String from_ToSearch() {
		return "Search";
	}
	
}
