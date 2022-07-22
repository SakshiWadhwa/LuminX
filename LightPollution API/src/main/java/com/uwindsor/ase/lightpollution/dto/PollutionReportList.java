package com.uwindsor.ase.lightpollution.dto;

import java.util.List;

public class PollutionReportList {

	private List<PollutionReports> pollutionReports;

	public PollutionReportList() {}
	
	public PollutionReportList(List<PollutionReports> pollutionReports) {
		super();
		this.pollutionReports = pollutionReports;
	}

	public List<PollutionReports> getPollutionReports() {
		return pollutionReports;
	}

	public void setPollutionReports(List<PollutionReports> pollutionReports) {
		this.pollutionReports = pollutionReports;
	}
}
