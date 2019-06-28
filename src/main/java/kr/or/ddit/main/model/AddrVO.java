package kr.or.ddit.main.model;

public class AddrVO {
	private String addr1;
	private String addr2;
	
	public AddrVO() {

	}
	
	public AddrVO(String addr1, String addr2) {
		super();
		this.addr1 = addr1;
		this.addr2 = addr2;
	}
	
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	@Override
	public String toString() {
		return "AddrVO [addr1=" + addr1 + ", addr2=" + addr2 + "]";
	}
	
	
}
