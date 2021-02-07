package model;

public class Registro {
	/*
	-- Criar Tabela Planejamento de Funcoes
	CREATE TABLE PLANEJARECURSOS(
		CodProj		NUMERIC(8)		NOT NULL,
		CodCargo	NUMERIC(8)		NOT NULL,
		QtdRecs		NUMERIC(3)		NOT NULL,
		HorasTotais	NUMERIC(6)		NOT NULL,
		ObsRecurso	VARCHAR(20)		NOT NULL,
		CONSTRAINTS FK_PROJ FOREIGN KEY (CodProj) REFERENCES (CodProj),
		CONSTRAINTS FK_CARGO FOREIGN KEY (CodCargo) REFERENCES (CodCargo)
	);
	*/
	private int codProj;
	private int codCargo;
	private int qtdRecs;
	private int horasTotais;
	private String obsRecurso;
	
	public Registro() {
		
	}
	
	public Registro(int codProj, int codCargo, int qtdRecs, int horasTotais, String obsRecurso) {
		super();
		this.codProj = codProj;
		this.codCargo = codCargo;
		this.qtdRecs = qtdRecs;
		this.horasTotais = horasTotais;
		this.obsRecurso = obsRecurso;
	}
	
	public int getCodProj() {
		return codProj;
	}
	public void setCodProj(int codProj) {
		this.codProj = codProj;
	}
	
	public int getCodCargo() {
		return codCargo;
	}
	public void setCodCargo(int codCargo) {
		this.codCargo = codCargo;
	}
	
	public int getQtdRecs() {
		return qtdRecs;
	}
	public void setQtdRecs(int qtdRecs) {
		this.qtdRecs = qtdRecs;
	}
	
	public int getHorasTotais() {
		return horasTotais;
	}
	public void setHorasTotais(int horasTotais) {
		this.horasTotais = horasTotais;
	}
	
	public String getObsRecurso() {
		return obsRecurso;
	}
	public void setObsRecurso(String obsRecurso) {
		this.obsRecurso = obsRecurso;
	}
	
}
