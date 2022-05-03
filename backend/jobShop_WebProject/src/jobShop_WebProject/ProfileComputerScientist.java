package jobShop_WebProject;

import java.util.Date;

public class ProfileComputerScientist extends Profile {
	public ProfileComputerScientist(Date startDate, Date endDate, Domain domain, int term, int id) {

		super.startDate = startDate;
		super.endDate = endDate;
		super.domain = domain;
		super.term = term;
		super.id = id;
	}
}
