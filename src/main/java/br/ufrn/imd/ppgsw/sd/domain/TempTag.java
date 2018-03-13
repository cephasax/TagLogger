package br.ufrn.imd.ppgsw.sd.domain;

public class TempTag {
	
	private String antenna;
	private String tag;
	
	public TempTag(){
		this.antenna = new String();
		this.tag = new String();
	}
	
	public String getAntenna() {
		return antenna;
	}
	
	public void setAntenna(String antenna) {
		this.antenna = antenna;
	}
	
	public String getTag() {
		return tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		return "TempTag [antenna=" + antenna + ", tag=" + tag + "]";
	}
	
	
	
}
