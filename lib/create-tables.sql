-- Criar Tabela Clientes
CREATE TABLE CLIENTES(
	CodCliente	NUMERIC(8)		NOT NULL AUTO_INCREMENT PRIMARY KEY,
	NomeCliente	VARCHAR(20)		NOT NULL,
	CnpjCliente	VARCHAR(18)		NOT NULL
);
-- Criar Tabela STATUS
CREATE TABLE STATUS(
	CodStatus	NUMERIC(1)		NOT NULL AUTO_INCREMENT PRIMARY KEY,
	ObsStatus	VARCHAR(30)		NOT NULL,
	SubStatus	VARCHAR(30)		NOT NULL
);
-- Criar Tabela Projetos
CREATE TABLE PROJETOS(
	CodProj		NUMERIC(8)		NOT NULL AUTO_INCREMENT PRIMARY KEY,
	NomeProj	VARCHAR(100)	NOT NULL,
	CodCliente	NUMERIC(8),
	CodStatus	NUMERIC(1),
	HorasTotais	NUMERIC(6)		NOT NULL,
	DataIni		DATE			NOT NULL,
	DataFim		DATE			NOT NULL,
	CustoProj	NUMERIC(20)		NOT NULL,
	ObsProj		VARCHAR(60)		NOT NULL,
	FOREIGN KEY (CodCliente) REFERENCES CLIENTES(CodCliente),
	FOREIGN KEY (CodStatus) REFERENCES STATUS(CodStatus)
);
-- Criar Tabela Cargos
CREATE TABLE CARGOS(
	CodCargo	NUMERIC(4)		NOT NULL AUTO_INCREMENT PRIMARY KEY,
	NomeCargo	VARCHAR(30)		NOT NULL
);
-- Criar Tabela Planejamento de Funcoes
CREATE TABLE PLANEJARECURSOS(
	CodProj		NUMERIC(8),
	CodCargo	NUMERIC(8),
	QtdRecs		NUMERIC(3)		NOT NULL,
	HorasTotais	NUMERIC(6)		NOT NULL,
	ObsRecurso	VARCHAR(60)		NOT NULL,
	FOREIGN KEY (CodProj) REFERENCES PROJETOS(CodProj),
	FOREIGN KEY (CodCargo) REFERENCES CARGOS(CodCargo)
);
-- Criar Tabela Contrato de Projetos
CREATE TABLE CONTRATOS(
	CodContrato	NUMERIC(8)		NOT NULL AUTO_INCREMENT PRIMARY KEY,
	CodProj		NUMERIC(8),
	EstimaIni	DATE			NOT NULL,
	EstimaFim	DATE			NOT NULL,
	EstimaHoras	NUMERIC(6)		NOT NULL,
	EstimaCusto	NUMERIC(10)		NOT NULL,
	FOREIGN KEY (CodProj) REFERENCES PROJETOS(CodProj)
);
-- Popular Tabela CLIENTES
INSERT INTO CLIENTES(CodCliente, NomeCliente, CnpjCliente) VALUES('00000001','Brastemp','12.312.312/0001-23');
INSERT INTO CLIENTES(CodCliente, NomeCliente, CnpjCliente) VALUES('00000002','LG','16.534.352/0001-89');
INSERT INTO CLIENTES(CodCliente, NomeCliente, CnpjCliente) VALUES('00000003','Honda','14.313.090/0001-33');
INSERT INTO CLIENTES(CodCliente, NomeCliente, CnpjCliente) VALUES('00000004','Amazon','11.443.190/0001-81');
INSERT INTO CLIENTES(CodCliente, NomeCliente, CnpjCliente) VALUES('00000005','Acer','10.112.881/0001-99');
INSERT INTO CLIENTES(CodCliente, NomeCliente, CnpjCliente) VALUES('00000006','Sony','15.421.872/0002-34');
-- Popular Tabela STATUS
INSERT INTO STATUS(CodStatus, ObsStatus, SubStatus) VALUES('1','Em Negociacao', 'Em Negociacao');
INSERT INTO STATUS(CodStatus, ObsStatus, SubStatus) VALUES('2','Contratado', 'Em Desenvolvimento');
INSERT INTO STATUS(CodStatus, ObsStatus, SubStatus) VALUES('3','Contratado', 'Finalizado');
INSERT INTO STATUS(CodStatus, ObsStatus, SubStatus) VALUES('4','Contratado', 'Cancelado');
INSERT INTO STATUS(CodStatus, ObsStatus, SubStatus) VALUES('5','Removido', 'Removido');
-- Popular Tabela PROJETOS
INSERT INTO PROJETOS(CodProj, NomeProj, CodCliente, CodStatus, HorasTotais, DataIni, DataFim, CustoProj, ObsProj) VALUES('00000001','Integração E-Commerce','00000001','2','3840','2008-09-06','2009-05-06','4000000', '');
INSERT INTO PROJETOS(CodProj, NomeProj, CodCliente, CodStatus, HorasTotais, DataIni, DataFim, CustoProj, ObsProj) VALUES('00000002','Implantação SAP','00000002','2','3840','2010-09-06','2018-05-06','5000000', '');
INSERT INTO PROJETOS(CodProj, NomeProj, CodCliente, CodStatus, HorasTotais, DataIni, DataFim, CustoProj, ObsProj) VALUES('00000003','Migração VB.NET - ASP.NET','00000003','2','3840','2013-09-06','2019-11-06','9600000', '');
INSERT INTO PROJETOS(CodProj, NomeProj, CodCliente, CodStatus, HorasTotais, DataIni, DataFim, CustoProj, ObsProj) VALUES('00000004','Manutenção BD Oracle','00000001','2','3840','2016-03-16','2021-08-21','13400000', '');
INSERT INTO PROJETOS(CodProj, NomeProj, CodCliente, CodStatus, HorasTotais, DataIni, DataFim, CustoProj, ObsProj) VALUES('00000005','Migração de pervidor','00000001','1','3840','2017-04-21','2020-08-21','24200000', '');
INSERT INTO PROJETOS(CodProj, NomeProj, CodCliente, CodStatus, HorasTotais, DataIni, DataFim, CustoProj, ObsProj) VALUES('00000006','Implantação de portal','00000004','2','3840','2018-02-10','2025-09-20','68400000', '');
INSERT INTO PROJETOS(CodProj, NomeProj, CodCliente, CodStatus, HorasTotais, DataIni, DataFim, CustoProj, ObsProj) VALUES('00000007','Automação de Help Desk','00000005','1','3840','2017-02-21','2023-11-30','2800000', '');
INSERT INTO PROJETOS(CodProj, NomeProj, CodCliente, CodStatus, HorasTotais, DataIni, DataFim, CustoProj, ObsProj) VALUES('00000008','Consultoria Mainframe','00000006','2','3840','2015-07-26','2020-04-20','37600000', '');
-- Popular Tabela CARGOS
INSERT INTO CARGOS(CodCargo, NomeCargo) VALUES('0001','Gerente');
INSERT INTO CARGOS(CodCargo, NomeCargo) VALUES('0002','Coordenador');
INSERT INTO CARGOS(CodCargo, NomeCargo) VALUES('0003','Programador Senior');
INSERT INTO CARGOS(CodCargo, NomeCargo) VALUES('0004','Programador Pleno');
INSERT INTO CARGOS(CodCargo, NomeCargo) VALUES('0005','Programador Junior');
INSERT INTO CARGOS(CodCargo, NomeCargo) VALUES('0006','Arquiteto de Rede');
INSERT INTO CARGOS(CodCargo, NomeCargo) VALUES('0007','DBA');
INSERT INTO CARGOS(CodCargo, NomeCargo) VALUES('0008','Estag');
INSERT INTO CARGOS(CodCargo, NomeCargo) VALUES('0009','Analista SOC');
-- Popular Tabela PLANEJARECURSOS
INSERT INTO PLANEJARECURSOS(CodProj, CodCargo, QtdRecs, HorasTotais, ObsRecurso) VALUES('00000001','0001','1','3840','tentar Wilson Gonzalez');
INSERT INTO PLANEJARECURSOS(CodProj, CodCargo, QtdRecs, HorasTotais, ObsRecurso) VALUES('00000001','0003','5','1920','Java, CD/CI');
INSERT INTO PLANEJARECURSOS(CodProj, CodCargo, QtdRecs, HorasTotais, ObsRecurso) VALUES('00000001','0005','10','1920','FrontEnd / FullStack');
INSERT INTO PLANEJARECURSOS(CodProj, CodCargo, QtdRecs, HorasTotais, ObsRecurso) VALUES('00000001','0008','1','1440','Bolsa < R$1300');
INSERT INTO PLANEJARECURSOS(CodProj, CodCargo, QtdRecs, HorasTotais, ObsRecurso) VALUES('00000002','0001','2','3840','Lenira ou Marcus?');
INSERT INTO PLANEJARECURSOS(CodProj, CodCargo, QtdRecs, HorasTotais, ObsRecurso) VALUES('00000002','0007','3','5760','DBA c/ Experiencia');
INSERT INTO PLANEJARECURSOS(CodProj, CodCargo, QtdRecs, HorasTotais, ObsRecurso) VALUES('00000003','0001','1','3840','Danilo se comprometeu');
INSERT INTO PLANEJARECURSOS(CodProj, CodCargo, QtdRecs, HorasTotais, ObsRecurso) VALUES('00000003','0005','5','5760','C# e .NET');
INSERT INTO PLANEJARECURSOS(CodProj, CodCargo, QtdRecs, HorasTotais, ObsRecurso) VALUES('00000004','0001','2','3840','procurar 2');
INSERT INTO PLANEJARECURSOS(CodProj, CodCargo, QtdRecs, HorasTotais, ObsRecurso) VALUES('00000005','0001','1','3840','ideal seria a Cristina');
INSERT INTO PLANEJARECURSOS(CodProj, CodCargo, QtdRecs, HorasTotais, ObsRecurso) VALUES('00000005','0006','3','5760','Especialistas DWDM');
INSERT INTO PLANEJARECURSOS(CodProj, CodCargo, QtdRecs, HorasTotais, ObsRecurso) VALUES('00000006','0001','1','3840','alguem com exp em Parceiros');
INSERT INTO PLANEJARECURSOS(CodProj, CodCargo, QtdRecs, HorasTotais, ObsRecurso) VALUES('00000006','0003','1','1920','DockerizaÃ§Ã£o recursos');
INSERT INTO PLANEJARECURSOS(CodProj, CodCargo, QtdRecs, HorasTotais, ObsRecurso) VALUES('00000006','0009','4','1920','utilizar SIEM');
INSERT INTO PLANEJARECURSOS(CodProj, CodCargo, QtdRecs, HorasTotais, ObsRecurso) VALUES('00000007','0001','2','3840','CD/CI DevOps');
INSERT INTO PLANEJARECURSOS(CodProj, CodCargo, QtdRecs, HorasTotais, ObsRecurso) VALUES('00000007','0004','10','5760','CD/CI DevOps');
INSERT INTO PLANEJARECURSOS(CodProj, CodCargo, QtdRecs, HorasTotais, ObsRecurso) VALUES('00000007','0005','5','3840','CD/CI DevOps');
INSERT INTO PLANEJARECURSOS(CodProj, CodCargo, QtdRecs, HorasTotais, ObsRecurso) VALUES('00000008','0001','3','3840','Exp com AppSec');
INSERT INTO PLANEJARECURSOS(CodProj, CodCargo, QtdRecs, HorasTotais, ObsRecurso) VALUES('00000008','0003','15','1920','Backend Devs/QA');
INSERT INTO PLANEJARECURSOS(CodProj, CodCargo, QtdRecs, HorasTotais, ObsRecurso) VALUES('00000008','0005','10','960','Frontend Devs');
INSERT INTO PLANEJARECURSOS(CodProj, CodCargo, QtdRecs, HorasTotais, ObsRecurso) VALUES('00000008','0008','2','2880','2 estag.s p/ Agosto');
-- Popular Tabela CONTRATOS
INSERT INTO CONTRATOS(CodContrato, CodProj, EstimaIni, EstimaFim, EstimaHoras, EstimaCusto) VALUES('00000001','00000001','2008-09-06','2009-05-06','3840','4000000');
INSERT INTO CONTRATOS(CodContrato, CodProj, EstimaIni, EstimaFim, EstimaHoras, EstimaCusto) VALUES('00000002','00000002','2010-09-06','2018-05-06','3840','5000000');
INSERT INTO CONTRATOS(CodContrato, CodProj, EstimaIni, EstimaFim, EstimaHoras, EstimaCusto) VALUES('00000003','00000003','2013-09-06','2019-11-06','3840','9600000');
INSERT INTO CONTRATOS(CodContrato, CodProj, EstimaIni, EstimaFim, EstimaHoras, EstimaCusto) VALUES('00000004','00000004','2016-03-16','2021-08-21','3840','13400000');
INSERT INTO CONTRATOS(CodContrato, CodProj, EstimaIni, EstimaFim, EstimaHoras, EstimaCusto) VALUES('00000005','00000005','2017-04-21','2020-08-21','3840','24200000');
INSERT INTO CONTRATOS(CodContrato, CodProj, EstimaIni, EstimaFim, EstimaHoras, EstimaCusto) VALUES('00000006','00000006','2018-02-10','2025-09-20','3840','68400000');
INSERT INTO CONTRATOS(CodContrato, CodProj, EstimaIni, EstimaFim, EstimaHoras, EstimaCusto) VALUES('00000007','00000007','2017-02-21','2023-11-30','3840','2800000');
INSERT INTO CONTRATOS(CodContrato, CodProj, EstimaIni, EstimaFim, EstimaHoras, EstimaCusto) VALUES('00000008','00000008','2015-07-26','2020-04-20','3840','37600000');
/* INSERT INTO CONTRATOS(CodContrato, CodProj, EstimaIni, EstimaFim, EstimaHoras, EstimaCusto) VALUES('00000009','00000001','2008-09-06','2009-05-06','3840','400000');
*/