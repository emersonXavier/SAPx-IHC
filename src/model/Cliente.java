package model;

public class Cliente {
	/*
	-- Criar Tabela Clientes
	CREATE TABLE CLIENTES(
		CodCliente	NUMERIC(8)		NOT NULL,
		NomeCliente	VARCHAR(20)		NOT NULL,
		CnpjCliente	VARCHAR(14)		NOT NULL,
		CONSTRAINTS PK_CLIENTE PRIMARY KEY (CodCliente)
	);
	*/
	private int codCliente;
	private String nomeCliente;
	private String cnpjCliente;
	
	public Cliente() {
		
	}
	
	public Cliente(int codCliente, String nomeCliente, String cnpjCliente) {
		super();
		this.codCliente = codCliente;
		this.nomeCliente = nomeCliente;
		this.cnpjCliente = cnpjCliente;
	}
	
	public int getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}
	
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	
	public String getCnpjCliente() {
		return cnpjCliente;
	}
	public void setCnpjCliente(String cnpjCliente) {
		this.cnpjCliente = cnpjCliente;
	}
	
}
