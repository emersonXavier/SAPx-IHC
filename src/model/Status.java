package model;

public class Status {
	/*
	-- Criar Tabela STATUS
	CREATE TABLE STATUS(
		CodStatus	NUMERIC(1)		NOT NULL,
		ObsStatus	VARCHAR(15)		NOT NULL,
		SubStatus	VARCHAR(15)		NOT NULL,
		CONSTRAINTS PK_STATUS PRIMARY KEY (CodStatus)
	);
	*/
	private int codStatus;
	private String obsStatus;
	private String subStatus;
	
	public Status() {
		
	}
	
	public Status(int codStatus, String obsStatus, String subStatus) {
		super();
		this.codStatus = codStatus;
		this.obsStatus = obsStatus;
		this.subStatus = subStatus;	
	}
	
	public int getCodStatus() {
		return codStatus;
	}
	public void setCodStatus(int codStatus) {
		this.codStatus = codStatus;
	}
	
	public String getObsStatus() {
		return obsStatus;
	}
	public void setObsStatus(String obsStatus) {
		this.obsStatus = obsStatus;
	}
	
	public String getSubStatus() {
		return subStatus;
	}
	public void setSubStatus(String subStatus) {
		this.subStatus = subStatus;
	}
		
}
