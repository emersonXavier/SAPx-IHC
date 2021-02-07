package model;

public class Cargo {
	/*
	-- Criar Tabela Cargos
	CREATE TABLE CARGOS(
		CodCargo	NUMERIC(4)		NOT NULL,
		NomeCargo	VARCHAR(30)		NOT NULL,
		CONSTRAINTS PK_CARGO PRIMARY KEY (CodCargo)
	);
	*/
	private int codCargo;
	private String nomeCargo;
	
	public Cargo() {
		
	}
	
	public Cargo(int codCargo, String nomeCargo) {
		super();
		this.codCargo = codCargo;
		this.nomeCargo = nomeCargo;
	}
	
	public int getCodCargo() {
		return codCargo;
	}
	public void setCodCargo(int codCargo) {
		this.codCargo = codCargo;
	}
	
	public String getNomeCargo() {
		return nomeCargo;
	}
	public void setNomeCargo(String nomeCargo) {
		this.nomeCargo = nomeCargo;
	}

}
