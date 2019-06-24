package model;

public class Projeto {
	
	private String numProj;
	private String nomeCliente;
	private String cnpjCliente;
	private String status;
	private String dataInicio;
	private String dataTermino;
	private double valProj;
	private int qtdGer;
	private int qtdCoord;
	private int qtdArq;
	private int qtdProgSr;
	private int qtdProgPl;
	private int qtdProgJr;
	private int qtdDba;
	
	
	
	public Projeto(String numProj, String nomeCliente, String cnpjCliente, String status, String dataInicio, String dataTermino,
			float valProj, int qtdGer, int qtdCoord, int qtdArq, int qtdProgSr, int qtdProgPl, int qtdProgJr, int qtdDba) {
		super();
		this.numProj = numProj;
		this.nomeCliente = nomeCliente;
		this.cnpjCliente = cnpjCliente;
		this.status = status;
		this.dataInicio = dataInicio;
		this.dataTermino = dataTermino;
		this.valProj = valProj;
		this.qtdGer = qtdGer;
		this.qtdCoord = qtdCoord;
		this.qtdArq = qtdArq;
		this.qtdProgSr = qtdProgSr;
		this.qtdProgPl = qtdProgPl;
		this.qtdProgJr = qtdProgJr;
		this.qtdDba = qtdDba;
		
	}
	
	
	public int getQtdGer() {
		return qtdGer;
	}
	public void setQtdGer(int qtdGer) {
		this.qtdGer = qtdGer;
	}
	public int getQtdCoord() {
		return qtdCoord;
	}
	public void setQtdCoord(int qtdCoord) {
		this.qtdCoord = qtdCoord;
	}
	public int getQtdArq() {
		return qtdArq;
	}
	public void setQtdArq(int qtdArq) {
		this.qtdArq = qtdArq;
	}
	public int getQtdProgSr() {
		return qtdProgSr;
	}
	public void setQtdProgSr(int qtdProgSr) {
		this.qtdProgSr = qtdProgSr;
	}
	public int getQtdProgPl() {
		return qtdProgPl;
	}
	public void setQtdProgPl(int qtdProgPl) {
		this.qtdProgPl = qtdProgPl;
	}
	public int getQtdProgJr() {
		return qtdProgJr;
	}
	public void setQtdProgJr(int qtdProgJr) {
		this.qtdProgJr = qtdProgJr;
	}
	public int getQtdDba() {
		return qtdDba;
	}
	public void setQtdDba(int qtdDba) {
		this.qtdDba = qtdDba;
	}
	public Projeto() {
		
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNumProj() {
		return numProj;
	}
	public void setNumProj(String numProj) {
		this.numProj = numProj;
	}
	public double getValProj() {
		return valProj;
	}
	public void setValProj(double valProj) {
		this.valProj = valProj;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}
	public String getDataTermino() {
		return dataTermino;
	}
	public void setDataTermino(String dataTermino) {
		this.dataTermino = dataTermino;
	}


	public String getCnpjCliente() {
		return cnpjCliente;
	}


	public void setCnpjCliente(String cnpjCliente) {
		this.cnpjCliente = cnpjCliente;
	}
	
	

}
