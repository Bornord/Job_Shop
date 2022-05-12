package jobShop_WebProject;

import java.util.Date;

public class ProfileComputerScientist extends Profile {
	
	public ProfileComputerScientist(Date startDate, Date endDate, int term, int id) {

		super.startDate = startDate;
		super.endDate = endDate;
		super.domain = "Computer Sciences";
		super.term = term;
		super.id = id;
	}
}
