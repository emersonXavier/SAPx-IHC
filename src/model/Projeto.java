package model;

public class Projeto {
	/*
	-- Criar Tabela Projetos
	CREATE TABLE PROJETOS(
		CodProj		NUMERIC(8)		NOT NULL,
		NomeProj	VARCHAR(20)		NOT NULL,
		CodCliente	NUMERIC(8)		NOT NULL,
		CodStatus	NUMERIC(1)		NOT NULL,
		HorasTotais	NUMERIC(6)		NOT NULL,
		DataIni		DATE			NOT NULL,
		DataFim		DATE			NOT NULL,
		CustoProj	NUMERIC(10)		NOT NULL,
		ObsProj		VARCHAR(60)		NOT NULL,
		CONSTRAINTS PK_PROJ PRIMARY KEY (CodProj),
		CONSTRAINTS FK_CLI FOREIGN KEY (CodCliente) REFERENCES (CodCliente),
		CONSTRAINTS FK_STATUS FOREIGN KEY (CodStatus) REFERENCES (CodStatus)
	);
	*/
	private int codProj;
	private String nomeProj;
	private int codCliente;
	private int codStatus;
	private int horasTotais;
	private String dataIni;
	private String dataFim;
	private double custoProj;
	private String obsProj;
	
	public Projeto() {
		
	}
	
	public Projeto(int codProj, String nomeProj, int codCliente, int codStatus, int horasTotais,
			String dataIni, String dataFim, double custoProj, String obsProj) {
		super();
		this.codProj = codProj;
		this.nomeProj = nomeProj;
		this.codCliente = codCliente;
		this.codStatus = codStatus;
		this.dataIni = dataIni;
		this.dataFim = dataFim;
		this.custoProj = custoProj;
		this.obsProj = obsProj;
	}
	
	public int getCodProj() {
		return codProj;
	}
	public void setCodProj(int codProj) {
		this.codProj = codProj;
	}
	
	public String getNomeProj() {
		return nomeProj;
	}
	public void setNomeProj(String nomeProj) {
		this.nomeProj = nomeProj;
	}
	
	public int getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}
	
	public int getCodStatus() {
		return codStatus;
	}
	public void setCodStatus(int codStatus) {
		this.codStatus = codStatus;
	}
	
	public int getHorasTotais() {
		return horasTotais;
	}
	public void setHorasTotais(int horasTotais) {
		this.horasTotais = horasTotais;
	}
	
	public String getDataIni() {
		return dataIni;
	}
	public void setDataIni(String dataIni) {
		this.dataIni = dataIni;
	}
	
	public String getDataFim() {
		return dataFim;
	}
	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}
	
	public double getCustoProj() {
		return custoProj;
	}
	public void setCustoProj(double custoProj) {
		this.custoProj = custoProj;
	}
	
	public String getObsProj() {
		return obsProj;
	}
	public void setObsProj(String obsProj) {
		this.obsProj = obsProj;
	}
}
