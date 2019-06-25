package model;

public class Contrato {
	/*
	-- Criar Tabela Contrato de Projetos
	CREATE TABLE CONTRATOS(
		CodContrato	NUMERIC(8)		NOT NULL,
		CodProj		NUMERIC(8)		NOT NULL,
		EstimaIni	DATE			NOT NULL,
		EstimaFim	DATE			NOT NULL,
		EstimaHoras	NUMERIC(6)		NOT NULL,
		EstimaCusto	NUMERIC(6)		NOT NULL,
	);
	*/
	private int codContrato;
	private int codProj;
	private String estimaIni;
	private String estimaFim;
	private int estimaHoras;
	private double estimaCusto;
	
	public Contrato() {
		
	}
	
	public Contrato(int codContrato, int codProj, String estimaIni, String estimaFim, int estimaHoras, double estimaCusto) {
		super();
		this.codContrato = codContrato;
		this.codProj = codProj;
		this.estimaIni = estimaIni;
		this.estimaFim = estimaFim;
		this.estimaHoras = estimaHoras;
		this.estimaCusto = estimaCusto;
	}
	
	public int getCodContrato() {
		return codContrato;
	}
	public void setCodContrato(int codContrato) {
		this.codContrato = codContrato;
	}
	
	public int getCodProj() {
		return codProj;
	}
	public void setCodProj(int codProj) {
		this.codProj = codProj;
	}
	
	public String getEstimaIni() {
		return estimaIni;
	}
	public void setEstimaIni(String estimaIni) {
		this.estimaIni = estimaIni;
	}
	
	public String getEstimaFim() {
		return estimaFim;
	}
	public void setEstimaFim(String estimaFim) {
		this.estimaFim = estimaFim;
	}
	
	public int getEstimaHoras() {
		return estimaHoras;
	}
	public void setEstimaHoras(int estimaHoras) {
		this.estimaHoras = estimaHoras;
	}
	
	public double getEstimaCusto() {
		return estimaCusto;
	}
	public void setEstimaCusto(double estimaCusto) {
		this.estimaCusto = estimaCusto;
	}
}
