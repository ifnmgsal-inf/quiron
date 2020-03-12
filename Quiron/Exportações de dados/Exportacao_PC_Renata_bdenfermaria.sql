-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 11-Mar-2020 às 20:24
-- Versão do servidor: 10.4.10-MariaDB
-- versão do PHP: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `bdenfermaria`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `cartaovacina`
--

CREATE TABLE `cartaovacina` (
  `idPaciente` int(11) NOT NULL,
  `cpf` varchar(15) DEFAULT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `dn` varchar(45) DEFAULT NULL,
  `tipoSanguineo` varchar(5) DEFAULT NULL,
  `rua` varchar(100) DEFAULT NULL,
  `numero` varchar(5) DEFAULT NULL,
  `bairro` varchar(45) DEFAULT NULL,
  `municipio` varchar(100) DEFAULT NULL,
  `uf` varchar(3) DEFAULT NULL,
  `telefone` varchar(15) DEFAULT NULL,
  `grs` varchar(45) DEFAULT NULL,
  `dose1` varchar(11) DEFAULT NULL,
  `lote1` varchar(11) DEFAULT NULL,
  `validade1` varchar(11) DEFAULT NULL,
  `dose2` varchar(11) DEFAULT NULL,
  `lote2` varchar(11) DEFAULT NULL,
  `validade2` varchar(11) DEFAULT NULL,
  `dose3` varchar(11) DEFAULT NULL,
  `lote3` varchar(11) DEFAULT NULL,
  `validade3` varchar(11) DEFAULT NULL,
  `reforco` varchar(11) DEFAULT NULL,
  `loteReforco` varchar(11) DEFAULT NULL,
  `validadeReforco` varchar(11) DEFAULT NULL,
  `influenza1` varchar(11) DEFAULT NULL,
  `loteInfluenza1` varchar(11) DEFAULT NULL,
  `valInfluenza1` varchar(11) DEFAULT NULL,
  `influenza2` varchar(11) DEFAULT NULL,
  `loteInfluenza2` varchar(11) DEFAULT NULL,
  `valInfluenza2` varchar(11) DEFAULT NULL,
  `influenza3` varchar(11) DEFAULT NULL,
  `loteInfluenza3` varchar(11) DEFAULT NULL,
  `valInfluenza3` varchar(11) DEFAULT NULL,
  `influenza4` varchar(11) DEFAULT NULL,
  `loteInfluenza4` varchar(11) DEFAULT NULL,
  `valInfluenza4` varchar(11) DEFAULT NULL,
  `influenza5` varchar(11) DEFAULT NULL,
  `loteInfluenza5` varchar(11) DEFAULT NULL,
  `valInfluenza5` varchar(11) DEFAULT NULL,
  `influenza6` varchar(11) DEFAULT NULL,
  `loteInfluenza6` varchar(11) DEFAULT NULL,
  `valInfluenza6` varchar(11) DEFAULT NULL,
  `influenza7` varchar(11) DEFAULT NULL,
  `loteInfluenza7` varchar(11) DEFAULT NULL,
  `valInfluenza7` varchar(11) DEFAULT NULL,
  `influenza8` varchar(11) DEFAULT NULL,
  `loteInfluenza8` varchar(11) DEFAULT NULL,
  `valInfluenza8` varchar(11) DEFAULT NULL,
  `hepatite1` varchar(11) DEFAULT NULL,
  `loteHepatite1` varchar(11) DEFAULT NULL,
  `valHepatite1` varchar(11) DEFAULT NULL,
  `hepatite2` varchar(11) DEFAULT NULL,
  `loteHepatite2` varchar(11) DEFAULT NULL,
  `valHepatite2` varchar(11) DEFAULT NULL,
  `hepatite3` varchar(11) DEFAULT NULL,
  `loteHepatite3` varchar(11) DEFAULT NULL,
  `valHepatite3` varchar(11) DEFAULT NULL,
  `hepatite4` varchar(11) DEFAULT NULL,
  `loteHepatite4` varchar(11) DEFAULT NULL,
  `valHepatite4` varchar(11) DEFAULT NULL,
  `hepatite5` varchar(11) DEFAULT NULL,
  `loteHepatite5` varchar(11) DEFAULT NULL,
  `valHepatite5` varchar(11) DEFAULT NULL,
  `hepatite6` varchar(11) DEFAULT NULL,
  `loteHepatite6` varchar(11) DEFAULT NULL,
  `valHepatite6` varchar(11) DEFAULT NULL,
  `hepatite7` varchar(11) DEFAULT NULL,
  `loteHepatite7` varchar(11) DEFAULT NULL,
  `valHepatite7` varchar(11) DEFAULT NULL,
  `hepatite8` varchar(11) DEFAULT NULL,
  `loteHepatite8` varchar(11) DEFAULT NULL,
  `valHepatite8` varchar(11) DEFAULT NULL,
  `doseInicial` varchar(11) DEFAULT NULL,
  `loteFebre` varchar(11) DEFAULT NULL,
  `valFebre` varchar(11) DEFAULT NULL,
  `revacinacao` varchar(11) DEFAULT NULL,
  `loteRevacinacao` varchar(11) DEFAULT NULL,
  `valRevacinacao` varchar(11) DEFAULT NULL,
  `triplice1` varchar(11) DEFAULT NULL,
  `loteTriplice1` varchar(11) DEFAULT NULL,
  `valTriplice1` varchar(11) DEFAULT NULL,
  `triplice2` varchar(11) DEFAULT NULL,
  `loteTriplice2` varchar(11) DEFAULT NULL,
  `valTriplice2` varchar(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `cartaovacina`
--

INSERT INTO `cartaovacina` (`idPaciente`, `cpf`, `nome`, `dn`, `tipoSanguineo`, `rua`, `numero`, `bairro`, `municipio`, `uf`, `telefone`, `grs`, `dose1`, `lote1`, `validade1`, `dose2`, `lote2`, `validade2`, `dose3`, `lote3`, `validade3`, `reforco`, `loteReforco`, `validadeReforco`, `influenza1`, `loteInfluenza1`, `valInfluenza1`, `influenza2`, `loteInfluenza2`, `valInfluenza2`, `influenza3`, `loteInfluenza3`, `valInfluenza3`, `influenza4`, `loteInfluenza4`, `valInfluenza4`, `influenza5`, `loteInfluenza5`, `valInfluenza5`, `influenza6`, `loteInfluenza6`, `valInfluenza6`, `influenza7`, `loteInfluenza7`, `valInfluenza7`, `influenza8`, `loteInfluenza8`, `valInfluenza8`, `hepatite1`, `loteHepatite1`, `valHepatite1`, `hepatite2`, `loteHepatite2`, `valHepatite2`, `hepatite3`, `loteHepatite3`, `valHepatite3`, `hepatite4`, `loteHepatite4`, `valHepatite4`, `hepatite5`, `loteHepatite5`, `valHepatite5`, `hepatite6`, `loteHepatite6`, `valHepatite6`, `hepatite7`, `loteHepatite7`, `valHepatite7`, `hepatite8`, `loteHepatite8`, `valHepatite8`, `doseInicial`, `loteFebre`, `valFebre`, `revacinacao`, `loteRevacinacao`, `valRevacinacao`, `triplice1`, `loteTriplice1`, `valTriplice1`, `triplice2`, `loteTriplice2`, `valTriplice2`) VALUES
(1, '   .   .   -  ', 'Rogério Costa', '08/10/1998', '', '', '', '', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(2, '141.020.126-05', 'GABRIEL RIBEIRO SANTOS', '11/12/2005', '', 'RUA PORFÍRIO SOUTO', '367', '', 'NOVORIZONTE', 'MG', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(3, '079.870.081-50', 'WEVERTON CLEIDSON RIBEIRO', '05/09/2004', '', 'SANTA ISABEL', '12', '', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(4, '176.698.416-99', 'VICTOR GABRIEL OLIVEIRA LOPES', '22/12/2003', '', 'AVENIDA DO CONTORNO', '1695', '', 'TAIOBEIRAS', 'MG', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(5, '146.062.646-33', 'PEDRO EMANUEL FERREIRA SOUZA', '10/07/2004', '', 'SÃO SEBASTIÃO', '141', '', 'BERIZAL', 'MG', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(6, '150.150.286-79', 'VITÓRIA APARECIDA SILIA SANTANA', '25/09/2004', '', 'AVENIDA MAROTO FERREIRA', '86', '', 'MONTEZUMA', 'MG', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(7, '153.199.176-94', 'PRISCILLA ALMEIDA DOS SANTOS', '09/04/2005', '', 'ADÃO RUFINO', '1442', '', 'SALINAS', 'MG', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(8, '153.225.266-84', 'ELLEN CAROLINE MENDES DE ALMEIDA0812', '08/12/2004', '', 'LADISLAU G. DA SILVA', '232', '', 'NOVORIZONTE', 'MG', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(9, '116.082.476-27', 'LORRANY COSTA DOS SANTOS', '01/07/2005', 'O+', 'RUA MENDO CORREA', '961', '', 'PADRE CARVALHO', 'MG', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(10, '106.882.746-76', 'MARIA LUÍSA RIBEIRO ROCHA', '12/03/2005', 'O-', 'ANTÔNIO SOARES SOBRINHO', '361', '', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `cursos`
--

CREATE TABLE `cursos` (
  `idCursos` int(11) NOT NULL,
  `curso` varchar(45) NOT NULL,
  `nivel` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `cursos`
--

INSERT INTO `cursos` (`idCursos`, `curso`, `nivel`) VALUES
(1, 'Bacharelado em Engenharia Florestal', 'Superior'),
(2, 'Bacharelado em Engenharia de Alimentos', 'Superior'),
(3, 'Bacharelado em Medicina Veterinária', 'Superior'),
(4, 'Tecnologia em Produção de Cachaça', 'Superior'),
(5, 'Bacharelado em Sistemas de Informação', 'Superior'),
(6, 'Licenciatura em Ciências Biológicas', 'Superior'),
(7, 'Licenciatura em Física', 'Superior'),
(8, 'Licenciatura em Matemática', 'Superior'),
(9, 'Licenciatura em Pedagogia', 'Superior'),
(10, 'Licenciatura em Química', 'Superior'),
(11, 'Técnico em Agroindústria - Integrado', 'Técnico Integrado'),
(12, 'Técnico em Agropecuária - Integrado', 'Técnico Integrado'),
(13, 'Técnico em Informática - Integrado', 'Técnico Integrado');

-- --------------------------------------------------------

--
-- Estrutura da tabela `encaminhamentoshospital`
--

CREATE TABLE `encaminhamentoshospital` (
  `idEncaminhamentos` int(11) NOT NULL,
  `idPaciente` int(11) NOT NULL,
  `data` date DEFAULT NULL,
  `nomePaciente` varchar(45) DEFAULT NULL,
  `cpfPaciente` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `fichas`
--

CREATE TABLE `fichas` (
  `idFichas` int(11) NOT NULL,
  `idPaciente` int(11) NOT NULL,
  `data` date DEFAULT NULL,
  `anamnese` text DEFAULT NULL,
  `prescricao` text DEFAULT NULL,
  `atendente` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `fichas`
--

INSERT INTO `fichas` (`idFichas`, `idPaciente`, `data`, `anamnese`, `prescricao`, `atendente`) VALUES
(3, 1, '2020-03-11', 'x', 'y', 'Maria');

-- --------------------------------------------------------

--
-- Estrutura da tabela `pacientes`
--

CREATE TABLE `pacientes` (
  `idPaciente` int(11) NOT NULL,
  `cpf` varchar(15) DEFAULT NULL,
  `nome` varchar(100) DEFAULT NULL,
  `dtNascimento` tinytext DEFAULT NULL,
  `matricula` tinytext DEFAULT NULL,
  `curso` tinytext DEFAULT NULL,
  `turma` tinytext DEFAULT NULL,
  `rua` text DEFAULT NULL,
  `numero` tinytext DEFAULT NULL,
  `bairro` text DEFAULT NULL,
  `municipio` text DEFAULT NULL,
  `uf` tinytext DEFAULT NULL,
  `telefone` tinytext DEFAULT NULL,
  `nomeMae` tinytext DEFAULT NULL,
  `telefoneMae` tinytext DEFAULT NULL,
  `nomePai` tinytext DEFAULT NULL,
  `telefonePai` tinytext DEFAULT NULL,
  `nomeResponsavel` tinytext DEFAULT NULL,
  `telefoneResponsavel` tinytext DEFAULT NULL,
  `sexo` tinytext DEFAULT NULL,
  `peso` tinytext DEFAULT NULL,
  `altura` tinytext DEFAULT NULL,
  `tipoSanguineo` tinytext DEFAULT NULL,
  `mora` tinytext DEFAULT NULL,
  `regime` tinytext DEFAULT NULL,
  `planoSaude` tinytext DEFAULT NULL,
  `cartaoSus` tinytext DEFAULT NULL,
  `vacinasInfancia` tinytext DEFAULT NULL,
  `vacinasAdolescencia` tinytext DEFAULT NULL,
  `vacinaFaltando` tinytext DEFAULT NULL,
  `doencaInfanciaAdolescencia` tinytext DEFAULT NULL,
  `catapora` tinytext DEFAULT NULL,
  `caxumba` tinytext DEFAULT NULL,
  `dengue` tinytext DEFAULT NULL,
  `hepatite` tinytext DEFAULT NULL,
  `meningite` tinytext DEFAULT NULL,
  `pneumonia` tinytext DEFAULT NULL,
  `rubeola` tinytext DEFAULT NULL,
  `sarampo` tinytext DEFAULT NULL,
  `cirurgiaRealizada` tinytext DEFAULT NULL,
  `alergiaMedicamentosa` tinytext DEFAULT NULL,
  `alergiaAlimentar` tinytext DEFAULT NULL,
  `cancer` tinytext DEFAULT NULL,
  `cardiopatias` tinytext DEFAULT NULL,
  `diabetes` tinytext DEFAULT NULL,
  `hipertensaoArterial` tinytext DEFAULT NULL,
  `oftalmologico` tinytext DEFAULT NULL,
  `renal` tinytext DEFAULT NULL,
  `mental` tinytext DEFAULT NULL,
  `doencaEspecifica` tinytext DEFAULT NULL,
  `nenhumaDificuldadeRegistrada` tinytext DEFAULT NULL,
  `doencaCronica` tinytext DEFAULT NULL,
  `deficienciaAuditiva` tinytext DEFAULT NULL,
  `deficienciaFisica` tinytext DEFAULT NULL,
  `deficienciaVisual` tinytext DEFAULT NULL,
  `dificuldadeConcentracao` tinytext DEFAULT NULL,
  `dificuldadeEscrita` tinytext DEFAULT NULL,
  `dificuldadeLeitura` tinytext DEFAULT NULL,
  `superdotacao` tinytext DEFAULT NULL,
  `transtornoDesenvolvimento` tinytext DEFAULT NULL,
  `nenhumaEspecifica` tinytext DEFAULT NULL,
  `protese` tinytext DEFAULT NULL,
  `asma` tinytext DEFAULT NULL,
  `bronquite` tinytext DEFAULT NULL,
  `cronicaDiabetes` tinytext DEFAULT NULL,
  `pressaoAlta` tinytext DEFAULT NULL,
  `problemaCardiaco` tinytext DEFAULT NULL,
  `problemaRenal` tinytext DEFAULT NULL,
  `rinite` tinytext DEFAULT NULL,
  `doencaCronicaOutros` tinytext DEFAULT NULL,
  `acompanhamentoProblema` tinytext DEFAULT NULL,
  `medicamentoContinuo` tinytext DEFAULT NULL,
  `desmaios` tinytext DEFAULT NULL,
  `epistaxe` tinytext DEFAULT NULL,
  `pressaoArterial` tinytext DEFAULT NULL,
  `cefaleia` tinytext DEFAULT NULL,
  `diarreia` tinytext DEFAULT NULL,
  `colica` tinytext DEFAULT NULL,
  `psicologico` tinytext DEFAULT NULL,
  `fonaudiologo` tinytext DEFAULT NULL,
  `fisioterapia` tinytext DEFAULT NULL,
  `terapiaOcupacional` tinytext DEFAULT NULL,
  `acompanhamentoEspecializadoOutro` tinytext DEFAULT NULL,
  `anotacaoRelevante` tinytext DEFAULT NULL,
  `contatoEmergencia` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `pacientes`
--

INSERT INTO `pacientes` (`idPaciente`, `cpf`, `nome`, `dtNascimento`, `matricula`, `curso`, `turma`, `rua`, `numero`, `bairro`, `municipio`, `uf`, `telefone`, `nomeMae`, `telefoneMae`, `nomePai`, `telefonePai`, `nomeResponsavel`, `telefoneResponsavel`, `sexo`, `peso`, `altura`, `tipoSanguineo`, `mora`, `regime`, `planoSaude`, `cartaoSus`, `vacinasInfancia`, `vacinasAdolescencia`, `vacinaFaltando`, `doencaInfanciaAdolescencia`, `catapora`, `caxumba`, `dengue`, `hepatite`, `meningite`, `pneumonia`, `rubeola`, `sarampo`, `cirurgiaRealizada`, `alergiaMedicamentosa`, `alergiaAlimentar`, `cancer`, `cardiopatias`, `diabetes`, `hipertensaoArterial`, `oftalmologico`, `renal`, `mental`, `doencaEspecifica`, `nenhumaDificuldadeRegistrada`, `doencaCronica`, `deficienciaAuditiva`, `deficienciaFisica`, `deficienciaVisual`, `dificuldadeConcentracao`, `dificuldadeEscrita`, `dificuldadeLeitura`, `superdotacao`, `transtornoDesenvolvimento`, `nenhumaEspecifica`, `protese`, `asma`, `bronquite`, `cronicaDiabetes`, `pressaoAlta`, `problemaCardiaco`, `problemaRenal`, `rinite`, `doencaCronicaOutros`, `acompanhamentoProblema`, `medicamentoContinuo`, `desmaios`, `epistaxe`, `pressaoArterial`, `cefaleia`, `diarreia`, `colica`, `psicologico`, `fonaudiologo`, `fisioterapia`, `terapiaOcupacional`, `acompanhamentoEspecializadoOutro`, `anotacaoRelevante`, `contatoEmergencia`) VALUES
(1, '   .   .   -  ', 'Rogério Costa', '08/10/1998', '', 'Bacharelado em Sistemas de Informação', '', '', '', '', '', '', '', '', '', '', '', '', '', 'M', '', '', '', 'República', 'Externo', 'Não', 'Não possui', NULL, NULL, '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', NULL, NULL, 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', NULL, 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', '', 'Não', '', '', '', '', '', '', '', 'Não', 'Não', 'Não', 'Não', 'Sim', '', ''),
(2, '141.020.126-05', 'GABRIEL RIBEIRO SANTOS', '11/12/2005', '', 'Técnico em Agropecuária - Integrado', '1º A', 'RUA PORFÍRIO SOUTO', '367', '', 'NOVORIZONTE', 'MG', '', 'JANE GRAZIELE SANTOS', '997492803', 'DARLEY RIBEIRO DOS SANTOS', '999235248', '', '', 'M', '', '', '', 'Escola', 'Interno', 'Não', '705200430260877', 'Completo', 'Completo', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Sim', 'Não', 'Não', 'Não', 'Não', NULL, NULL, 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Sim', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', '', 'NÃO', 'NÃO', '', 'NÃO', 'NÃO', '', 'Não', 'Não', 'Não', 'Não', 'Não', '', 'JANE(MÃE) 997492803'),
(3, '079.870.081-50', 'WEVERTON CLEIDSON RIBEIRO', '05/09/2004', '', 'Técnico em Agropecuária - Integrado', '1º A', 'SANTA ISABEL', '12', '', '', '', '', 'VIRLEIDE VIRGEM ROSÁRIO LIMA', '', 'LAÉRCIO APARECIDO RIBEIRO', '99907474', 'LENICE', '999603919', 'M', '', '', '', 'Escola', 'Interno', 'COPASA E UNIMED', '898000464891754', 'Completo', 'Completo', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', NULL, NULL, 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Sim', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', '', 'NÃO', 'NÃO', '', 'SIM. DIPIRONA', 'NÃO', '', 'Não', 'Não', 'Não', 'Não', 'Não', '', 'PAI - 99907474'),
(4, '176.698.416-99', 'VICTOR GABRIEL OLIVEIRA LOPES', '22/12/2003', '', 'Técnico em Agropecuária - Integrado', '1º A', 'AVENIDA DO CONTORNO', '1695', '', 'TAIOBEIRAS', 'MG', '', 'ANA CLÉIA DE OLIVEIRA', '991031566', 'VLADENIR LOPES', '91146678', 'FÁBIO', '998184322', 'M', '64', '1,60', '', 'Escola', 'Interno', 'Não', '89800310965739', 'Completo', 'Completo', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CORAÇÃO', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', NULL, NULL, 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', '', '', '', '', '', '', '', 'Não', 'Não', 'Não', 'Não', 'Não', '', ''),
(5, '146.062.646-33', 'PEDRO EMANUEL FERREIRA SOUZA', '10/07/2004', '', 'Técnico em Agropecuária - Integrado', '1º A', 'SÃO SEBASTIÃO', '141', '', 'BERIZAL', 'MG', '', 'ALIANE GONÇALVES', '999316290', 'ÍRIS FERREIRA CRUZ', '998662028', '', '', 'M', '', '', '', 'Escola', 'Interno', 'Não', '707605221929399', 'Completo', 'Completo', '', '', 'Sim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', NULL, NULL, 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Sim', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', '', '', '', '', '', '', '', 'Não', 'Não', 'Não', 'Não', 'Não', '', ''),
(6, '150.150.286-79', 'VITÓRIA APARECIDA SILIA SANTANA', '25/09/2004', '', 'Técnico em Agropecuária - Integrado', '1º A', 'AVENIDA MAROTO FERREIRA', '86', '', 'MONTEZUMA', 'MG', '', 'SIMONE SILVA COSTA SANTANA', '999916311', 'ALVIMAR JOSÉ DE SANTANA', '997436818', '', '', 'F', '54', '1,63', '', 'República', 'Semi-Interno', 'Não', '702005849420089', 'Completo', 'Completo', '', '', 'Sim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Não', 'DIPIRONA', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', NULL, NULL, 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Sim', 'Não', 'Não', 'Sim', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'PARACETAMOL ( VERIFICAR)', 'NÃO', 'NÃO', '', 'NÃO', '', 'SIM. ', 'Não', 'Não', 'Não', 'Não', 'Não', '', ''),
(7, '153.199.176-94', 'PRISCILLA ALMEIDA DOS SANTOS', '09/04/2005', '', 'Técnico em Agropecuária - Integrado', '1º A', 'ADÃO RUFINO', '1442', '', 'SALINAS', 'MG', '', 'JUSICLENI SOUTO ALMEIDA', '99825877', 'SILVIO JOSÉ DOS SANTOS', '', 'ELIENE', '998813227', 'F', '50', '1,63', '', 'República', 'Semi-Interno', 'Não', '709003874302413', 'Completo', 'Completo', '', '', 'Sim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Não', 'Não', 'Não', 'Sim', 'Não', 'Sim', 'Sim', 'Sim', 'Sim', 'Não', 'Não', NULL, NULL, 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Sim', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', ' PRESSÃO BAIXA', 'Não', '', 'NÃO', 'SIM. ATÉ OS 12 ANOS COM FREQUÊNCIA', '', 'SIM. NEOSALDINA E DIPIRONA', 'NÃO', 'SIM. BUSCOPAN DUO', 'Sim', 'Não', 'Não', 'Não', 'Não', 'APRESENTA ANSIEDADE', 'ELIENE - 998813227'),
(8, '153.225.266-84', 'ELLEN CAROLINE MENDES DE ALMEIDA0812', '08/12/2004', '', 'Técnico em Agropecuária - Integrado', '1º A', 'LADISLAU G. DA SILVA', '232', '', 'NOVORIZONTE', 'MG', '', 'ELIANA MENDES DE OLIVEIRA', '998813227', 'VLATER ALMEIDA DA SILVA', '', '', '', 'F', '60', '166', '', 'Pais', 'Semi-Interno', 'Não', '15322526684', 'Completo', 'Completo', '', '', 'Sim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', NULL, NULL, 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Sim', 'Não', 'Não', 'Sim', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', '', '', '', '', '', '', '', 'Não', 'Não', 'Não', 'Não', 'Não', '', 'ELIANA - 998813227'),
(9, '116.082.476-27', 'LORRANY COSTA DOS SANTOS', '01/07/2005', '', 'Técnico em Agropecuária - Integrado', '1º A', 'RUA MENDO CORREA', '961', '', 'PADRE CARVALHO', 'MG', '', 'CARMEM SILVA COSTA ', '998340542', 'EVAILTON BATISTA DOS SANTOS', '', 'TIA MARIA ENY', '999306617', 'F', '58', '1,68', 'O+', 'Parentes', 'Semi-Interno', 'Não', '702409022925029', 'Completo', 'Completo', '', '', 'Sim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'QUAL?', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', NULL, NULL, 'Não', 'Sim', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', '', 'NÃO', 'NÃO', '', 'SIM. PARACETAMOL', 'NÃO', 'SIM. BUSCOFEM', 'Não', 'Não', 'Não', 'Não', 'Não', 'ALUNA COM PORTADORA DE PÉ CONGÊNITO', 'CARMEM(MÃE) - 998340542'),
(10, '106.882.746-76', 'MARIA LUÍSA RIBEIRO ROCHA', '12/03/2005', '', 'Técnico em Agropecuária - Integrado', '1º A', 'ANTÔNIO SOARES SOBRINHO', '361', '', '', '', '', 'EDILEUZA RIBEIRO M. ROCHA', '998561759', 'MANOEL CEZARINO DA ROCHA', '999410380', 'BRUNA', '988283537', 'F', '', '', 'O-', 'República', 'Semi-Interno', 'IPSEMG', '705006042261253', 'Completo', 'Completo', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Não', 'Não', 'Não', 'Sim', 'Não', 'Sim', 'Sim', 'Sim', 'Não', 'Não', 'Não', NULL, NULL, 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Sim', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', '', 'NÃO', 'NÃO', 'NÃO SABE', 'SIM. TORAGESIC', 'NÃO', 'SIM. BUSCOPAM', 'Não', 'Não', 'Não', 'Não', 'Não', '', 'MÃE - 998561759');

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuarios`
--

CREATE TABLE `usuarios` (
  `matricula` int(11) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `cpf` varchar(45) NOT NULL,
  `telefone` varchar(45) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `administrador` int(11) DEFAULT NULL,
  `ativado` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `usuarios`
--

INSERT INTO `usuarios` (`matricula`, `nome`, `cpf`, `telefone`, `senha`, `administrador`, `ativado`) VALUES
(123, 'IFNMG', '111.111.111-11', '', '', 1, 1);

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `cartaovacina`
--
ALTER TABLE `cartaovacina`
  ADD PRIMARY KEY (`idPaciente`);

--
-- Índices para tabela `cursos`
--
ALTER TABLE `cursos`
  ADD PRIMARY KEY (`idCursos`);

--
-- Índices para tabela `encaminhamentoshospital`
--
ALTER TABLE `encaminhamentoshospital`
  ADD PRIMARY KEY (`idEncaminhamentos`,`idPaciente`),
  ADD KEY `fk_encaminhamentosHospital_Pacientes1_idx` (`idPaciente`);

--
-- Índices para tabela `fichas`
--
ALTER TABLE `fichas`
  ADD PRIMARY KEY (`idFichas`,`idPaciente`),
  ADD KEY `fk_Fichas_Pacientes1_idx` (`idPaciente`);

--
-- Índices para tabela `pacientes`
--
ALTER TABLE `pacientes`
  ADD PRIMARY KEY (`idPaciente`);

--
-- Índices para tabela `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`matricula`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `cursos`
--
ALTER TABLE `cursos`
  MODIFY `idCursos` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de tabela `encaminhamentoshospital`
--
ALTER TABLE `encaminhamentoshospital`
  MODIFY `idEncaminhamentos` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `fichas`
--
ALTER TABLE `fichas`
  MODIFY `idFichas` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `pacientes`
--
ALTER TABLE `pacientes`
  MODIFY `idPaciente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `cartaovacina`
--
ALTER TABLE `cartaovacina`
  ADD CONSTRAINT `fk_CartaoVacina_Pacientes1` FOREIGN KEY (`idPaciente`) REFERENCES `pacientes` (`idPaciente`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `encaminhamentoshospital`
--
ALTER TABLE `encaminhamentoshospital`
  ADD CONSTRAINT `fk_encaminhamentosHospital_Pacientes1` FOREIGN KEY (`idPaciente`) REFERENCES `pacientes` (`idPaciente`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `fichas`
--
ALTER TABLE `fichas`
  ADD CONSTRAINT `fk_Fichas_Pacientes1` FOREIGN KEY (`idPaciente`) REFERENCES `pacientes` (`idPaciente`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
