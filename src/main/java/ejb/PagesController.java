package ejb;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named(value = "pagesController")
@RequestScoped
public class PagesController {

	public String fromUserProfileToUserSettings() {
		return "UserSettings";
	}
	
	public String fromUserProfileToNotifications() {
		return "Notifications";
	}
	
	public String from_ToSearch() {
		return "Search";
	}
	
}
