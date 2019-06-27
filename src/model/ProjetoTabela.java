package model;

public class ProjetoTabela {
	private int codProj;
	private String nomeCliente;
	private String cnpjCliente;
	private String obsStatus;
	private int horasTotais;
	private String dataIni;
	private String dataFim;
	private int custoProj;
	private String obsProj;
	
	public ProjetoTabela(int codProj, String nomeCliente, String cnpjCliente, String obsStatus, int horasTotais,
			String dataIni, String dataFim, int custoProj, String obsProj) {
		super();
		this.codProj = codProj;
		this.nomeCliente = nomeCliente;
		this.cnpjCliente = cnpjCliente;
		this.obsStatus = obsStatus;
		this.horasTotais = horasTotais;
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

	public String getObsStatus() {
		return obsStatus;
	}

	public void setObsStatus(String obsStatus) {
		this.obsStatus = obsStatus;
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

	public int getCustoProj() {
		return custoProj;
	}

	public void setCustoProj(int custoProj) {
		this.custoProj = custoProj;
	}

	public String getObsProj() {
		return obsProj;
	}

	public void setObsProj(String obsProj) {
		this.obsProj = obsProj;
	}
		
}
