package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class ProjetoTableModel {
	
	private List<Projeto> dados = new ArrayList<>();
	private String[] colunas = {"Numero Projeto", "Nome Cliente", "Status", "Data Início", "Data término", "Valor - R$"};
	
	/*@Override
	public String getColumnName(int coluna) {
		return colunas[coluna];
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return dados.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		
		switch(coluna) {
			case 0:
				return dados.get(linha).getNumProj();
			case 1:
				return dados.get(linha).getNomeCliente();
			case 2:
				return dados.get(linha).getStatus();
			case 3:
				return dados.get(linha).getDataInicio();
			case 4:
				return dados.get(linha).getDataTermino();
			case 5:
				return dados.get(linha).getValProj();

		}
	
		return null;
	}
	
	public void addRow(Projeto p) {
		this.dados.add(p);
		this.fireTableDataChanged();
	}
*/
}
