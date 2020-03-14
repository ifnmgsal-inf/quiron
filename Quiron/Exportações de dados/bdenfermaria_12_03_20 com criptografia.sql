-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 13-Mar-2020 às 02:28
-- Versão do servidor: 10.4.11-MariaDB
-- versão do PHP: 7.4.1

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
  `febre1` varchar(11) DEFAULT NULL,
  `loteFebre1` varchar(11) DEFAULT NULL,
  `valFebre1` varchar(11) DEFAULT NULL,
  `febre2` varchar(11) DEFAULT NULL,
  `loteFebre2` varchar(11) DEFAULT NULL,
  `valFebre2` varchar(11) DEFAULT NULL,
  `febre3` varchar(11) DEFAULT NULL,
  `loteFebre3` varchar(11) DEFAULT NULL,
  `valFebre3` varchar(11) DEFAULT NULL,
  `febre4` varchar(11) DEFAULT NULL,
  `loteFebre4` varchar(11) DEFAULT NULL,
  `valFebre4` varchar(11) DEFAULT NULL,
  `febre5` varchar(11) DEFAULT NULL,
  `loteFebre5` varchar(11) DEFAULT NULL,
  `valFebre5` varchar(11) DEFAULT NULL,
  `febre6` varchar(11) DEFAULT NULL,
  `loteFebre6` varchar(11) DEFAULT NULL,
  `valFebre6` varchar(11) DEFAULT NULL,
  `febre7` varchar(11) DEFAULT NULL,
  `loteFebre7` varchar(11) DEFAULT NULL,
  `valFebre7` varchar(11) DEFAULT NULL,
  `febre8` varchar(11) DEFAULT NULL,
  `loteFebre8` varchar(11) DEFAULT NULL,
  `valFebre8` varchar(11) DEFAULT NULL,
  `duplaViral1` varchar(11) DEFAULT NULL,
  `loteDupla1` varchar(11) DEFAULT NULL,
  `valDupla1` varchar(11) DEFAULT NULL,
  `duplaViral2` varchar(11) DEFAULT NULL,
  `loteDupla2` varchar(11) DEFAULT NULL,
  `valDupla2` varchar(11) DEFAULT NULL,
  `triplice1` varchar(11) DEFAULT NULL,
  `loteTriplice1` varchar(11) DEFAULT NULL,
  `valTriplice1` varchar(11) DEFAULT NULL,
  `triplice2` varchar(11) DEFAULT NULL,
  `loteTriplice2` varchar(11) DEFAULT NULL,
  `valTriplice2` varchar(11) DEFAULT NULL,
  `outra1` varchar(45) DEFAULT NULL,
  `dtOutra1` varchar(11) DEFAULT NULL,
  `loteOutra1` varchar(11) DEFAULT NULL,
  `valOutra1` varchar(11) DEFAULT NULL,
  `outra1d2` varchar(45) DEFAULT NULL,
  `dtOutra1d2` varchar(11) DEFAULT NULL,
  `loteOutra1d2` varchar(11) DEFAULT NULL,
  `valOutra1d2` varchar(11) DEFAULT NULL,
  `outra2` varchar(45) DEFAULT NULL,
  `dtOutra2` varchar(11) DEFAULT NULL,
  `loteOutra2` varchar(11) DEFAULT NULL,
  `valOutra2` varchar(11) DEFAULT NULL,
  `outra2d2` varchar(45) DEFAULT NULL,
  `dtOutra2d2` varchar(11) DEFAULT NULL,
  `loteOutra2d2` varchar(11) DEFAULT NULL,
  `valOutra2d2` varchar(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `cartaovacina`
--

INSERT INTO `cartaovacina` (`idPaciente`, `cpf`, `nome`, `dn`, `tipoSanguineo`, `rua`, `numero`, `bairro`, `municipio`, `uf`, `telefone`, `grs`, `dose1`, `lote1`, `validade1`, `dose2`, `lote2`, `validade2`, `dose3`, `lote3`, `validade3`, `reforco`, `loteReforco`, `validadeReforco`, `influenza1`, `loteInfluenza1`, `valInfluenza1`, `influenza2`, `loteInfluenza2`, `valInfluenza2`, `influenza3`, `loteInfluenza3`, `valInfluenza3`, `influenza4`, `loteInfluenza4`, `valInfluenza4`, `influenza5`, `loteInfluenza5`, `valInfluenza5`, `influenza6`, `loteInfluenza6`, `valInfluenza6`, `influenza7`, `loteInfluenza7`, `valInfluenza7`, `influenza8`, `loteInfluenza8`, `valInfluenza8`, `febre1`, `loteFebre1`, `valFebre1`, `febre2`, `loteFebre2`, `valFebre2`, `febre3`, `loteFebre3`, `valFebre3`, `febre4`, `loteFebre4`, `valFebre4`, `febre5`, `loteFebre5`, `valFebre5`, `febre6`, `loteFebre6`, `valFebre6`, `febre7`, `loteFebre7`, `valFebre7`, `febre8`, `loteFebre8`, `valFebre8`, `duplaViral1`, `loteDupla1`, `valDupla1`, `duplaViral2`, `loteDupla2`, `valDupla2`, `triplice1`, `loteTriplice1`, `valTriplice1`, `triplice2`, `loteTriplice2`, `valTriplice2`, `outra1`, `dtOutra1`, `loteOutra1`, `valOutra1`, `outra1d2`, `dtOutra1d2`, `loteOutra1d2`, `valOutra1d2`, `outra2`, `dtOutra2`, `loteOutra2`, `valOutra2`, `outra2d2`, `dtOutra2d2`, `loteOutra2d2`, `valOutra2d2`) VALUES
(1, '070.935.286-78', 'Rogério Costa Negro Rocha', '08/10/1998', 'B+', 'Minas Gerais', '730', 'São Jorge', 'Francisco Sá', 'MG', '(38)9 9122-4430', '', '01/01/2000', '1', '11/11/1112', '02/01/2000', '2', '22/22/2221', '03/01/2000', '3', '33/33/3331', '04/01/2000', '4', '33/33/3331', '11/11/1111', '1', '11/11/1111', '11/11/1111', '1', '11/11/1111', '11/11/1111', '1', '11/11/1111', '11/11/1111', '1', '11/11/1111', '11/11/1111', '1', '11/11/1111', '11/11/1111', '1', '11/11/1111', '11/11/1111', '1', '11/11/1111', '11/11/1111', '1', '11/11/1111', '11/11/1111', '1', '11/11/1111', '11/11/1111', '1', '11/11/1111', '11/11/1111', '1', '11/11/1111', '11/11/1111', '1', '11/11/1111', '11/11/1111', '23', '22/22/2222', '22/22/2222', '232', '76/54/3213', '22/22/2222', '2', '33/33/3333', '22/22/2222', '213', '22/22/2222', '22/22/2222', '2', '22/22/2222', '22/22/2222', '2', '22/22/2222', '22/22/2222', '2', '22/22/2222', '22/22/2222', '2', '22/22/2222', 'P', '11/11/1111', '2', '34/55/5555', 'q', '13/33/3333', '2', '11/11/1111', 'nxkjn', '34/22/2222', '2', '11/11/1111', 'skn', '35/32/2222', '2', '11/11/1111'),
(2, '   .   .   -  ', 'Franciele Alves', '12/12/1996', '', '', '', '', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(7, '   .   .   -  ', 'Jurema', '05/07/2010', '', '', '', '', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

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
(13, 'Técnico em Informática - Integrado', 'Técnico Integrado'),
(14, 'Servidor', '');

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

--
-- Extraindo dados da tabela `encaminhamentoshospital`
--

INSERT INTO `encaminhamentoshospital` (`idEncaminhamentos`, `idPaciente`, `data`, `nomePaciente`, `cpfPaciente`) VALUES
(2, 1, '2020-03-10', 'Rogério Costa Negro Rocha', '070.935.286-78');

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
(15, 1, '2020-03-01', 'Problemas', 'Remédio', 'ROGER'),
(16, 1, '2020-03-02', 'Mais Problemas', 'Rivotril', 'Fran'),
(17, 1, '2019-11-02', 'x', 'x', 'x');

-- --------------------------------------------------------

--
-- Estrutura da tabela `pacientes`
--

CREATE TABLE `pacientes` (
  `idPaciente` int(11) NOT NULL,
  `cpf` varchar(15) DEFAULT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `dtNascimento` varchar(11) DEFAULT NULL,
  `matricula` varchar(15) DEFAULT NULL,
  `curso` varchar(45) DEFAULT NULL,
  `turma` varchar(45) DEFAULT NULL,
  `rua` varchar(45) DEFAULT NULL,
  `numero` varchar(5) DEFAULT NULL,
  `bairro` varchar(45) DEFAULT NULL,
  `municipio` varchar(45) DEFAULT NULL,
  `uf` varchar(3) DEFAULT NULL,
  `telefone` varchar(15) DEFAULT NULL,
  `nomeMae` varchar(45) DEFAULT NULL,
  `telefoneMae` varchar(15) DEFAULT NULL,
  `nomePai` varchar(45) DEFAULT NULL,
  `telefonePai` varchar(15) DEFAULT NULL,
  `nomeResponsavel` varchar(45) DEFAULT NULL,
  `telefoneResponsavel` varchar(15) DEFAULT NULL,
  `sexo` varchar(2) DEFAULT NULL,
  `peso` varchar(5) DEFAULT NULL,
  `altura` varchar(5) DEFAULT NULL,
  `tipoSanguineo` varchar(5) DEFAULT NULL,
  `mora` varchar(15) DEFAULT NULL,
  `regime` varchar(15) DEFAULT NULL,
  `planoSaude` varchar(45) DEFAULT NULL,
  `cartaoSus` varchar(16) DEFAULT NULL,
  `vacinasInfancia` varchar(15) DEFAULT NULL,
  `vacinasAdolescencia` varchar(15) DEFAULT NULL,
  `vacinaFaltando` text DEFAULT NULL,
  `doencaInfanciaAdolescencia` text DEFAULT NULL,
  `catapora` varchar(5) DEFAULT NULL,
  `caxumba` varchar(5) DEFAULT NULL,
  `dengue` varchar(5) DEFAULT NULL,
  `hepatite` varchar(5) DEFAULT NULL,
  `meningite` varchar(5) DEFAULT NULL,
  `pneumonia` varchar(5) DEFAULT NULL,
  `rubeola` varchar(5) DEFAULT NULL,
  `sarampo` varchar(5) DEFAULT NULL,
  `cirurgiaRealizada` text DEFAULT NULL,
  `alergiaMedicamentosa` text DEFAULT NULL,
  `alergiaAlimentar` text DEFAULT NULL,
  `cancer` varchar(5) DEFAULT NULL,
  `cardiopatias` varchar(5) DEFAULT NULL,
  `diabetes` varchar(5) DEFAULT NULL,
  `hipertensaoArterial` varchar(5) DEFAULT NULL,
  `oftalmologico` varchar(5) DEFAULT NULL,
  `renal` varchar(5) DEFAULT NULL,
  `mental` varchar(5) DEFAULT NULL,
  `doencaEspecifica` text DEFAULT NULL,
  `nenhumaDificuldadeRegistrada` varchar(5) DEFAULT NULL,
  `doencaCronica` varchar(5) DEFAULT NULL,
  `deficienciaAuditiva` varchar(5) DEFAULT NULL,
  `deficienciaFisica` varchar(5) DEFAULT NULL,
  `deficienciaVisual` varchar(5) DEFAULT NULL,
  `dificuldadeConcentracao` varchar(5) DEFAULT NULL,
  `dificuldadeEscrita` varchar(5) DEFAULT NULL,
  `dificuldadeLeitura` varchar(5) DEFAULT NULL,
  `superdotacao` varchar(5) DEFAULT NULL,
  `transtornoDesenvolvimento` varchar(5) DEFAULT NULL,
  `nenhumaEspecifica` varchar(5) DEFAULT NULL,
  `protese` varchar(5) DEFAULT NULL,
  `asma` varchar(5) DEFAULT NULL,
  `bronquite` varchar(5) DEFAULT NULL,
  `cronicaDiabetes` varchar(5) DEFAULT NULL,
  `pressaoAlta` varchar(5) DEFAULT NULL,
  `problemaCardiaco` varchar(5) DEFAULT NULL,
  `problemaRenal` varchar(5) DEFAULT NULL,
  `rinite` varchar(5) DEFAULT NULL,
  `doencaCronicaOutros` text DEFAULT NULL,
  `acompanhamentoProblema` varchar(5) DEFAULT NULL,
  `medicamentoContinuo` text DEFAULT NULL,
  `desmaios` text DEFAULT NULL,
  `epistaxe` text DEFAULT NULL,
  `pressaoArterial` text DEFAULT NULL,
  `cefaleia` text DEFAULT NULL,
  `diarreia` text DEFAULT NULL,
  `colica` text DEFAULT NULL,
  `psicologico` varchar(5) DEFAULT NULL,
  `fonaudiologo` varchar(5) DEFAULT NULL,
  `fisioterapia` varchar(5) DEFAULT NULL,
  `terapiaOcupacional` varchar(5) DEFAULT NULL,
  `acompanhamentoEspecializadoOutro` text DEFAULT NULL,
  `anotacaoRelevante` text DEFAULT NULL,
  `contatoEmergencia` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `pacientes`
--

INSERT INTO `pacientes` (`idPaciente`, `cpf`, `nome`, `dtNascimento`, `matricula`, `curso`, `turma`, `rua`, `numero`, `bairro`, `municipio`, `uf`, `telefone`, `nomeMae`, `telefoneMae`, `nomePai`, `telefonePai`, `nomeResponsavel`, `telefoneResponsavel`, `sexo`, `peso`, `altura`, `tipoSanguineo`, `mora`, `regime`, `planoSaude`, `cartaoSus`, `vacinasInfancia`, `vacinasAdolescencia`, `vacinaFaltando`, `doencaInfanciaAdolescencia`, `catapora`, `caxumba`, `dengue`, `hepatite`, `meningite`, `pneumonia`, `rubeola`, `sarampo`, `cirurgiaRealizada`, `alergiaMedicamentosa`, `alergiaAlimentar`, `cancer`, `cardiopatias`, `diabetes`, `hipertensaoArterial`, `oftalmologico`, `renal`, `mental`, `doencaEspecifica`, `nenhumaDificuldadeRegistrada`, `doencaCronica`, `deficienciaAuditiva`, `deficienciaFisica`, `deficienciaVisual`, `dificuldadeConcentracao`, `dificuldadeEscrita`, `dificuldadeLeitura`, `superdotacao`, `transtornoDesenvolvimento`, `nenhumaEspecifica`, `protese`, `asma`, `bronquite`, `cronicaDiabetes`, `pressaoAlta`, `problemaCardiaco`, `problemaRenal`, `rinite`, `doencaCronicaOutros`, `acompanhamentoProblema`, `medicamentoContinuo`, `desmaios`, `epistaxe`, `pressaoArterial`, `cefaleia`, `diarreia`, `colica`, `psicologico`, `fonaudiologo`, `fisioterapia`, `terapiaOcupacional`, `acompanhamentoEspecializadoOutro`, `anotacaoRelevante`, `contatoEmergencia`) VALUES
(1, '070.935.286-78', 'Rogério Costa Negro Rocha', '08/10/1998', '12345', 'Bacharelado em Sistemas de Informação', '2017', 'Minas Gerais', '730', 'São Jorge', 'Francisco Sá', 'MG', '(38)9 9122-4430', 'Adriana Gonçalves Rocha', '(38)9 9889-5707', 'Geraldo Magella Costa Fernandes', '(38)9 9841-9241', '', '', 'M', '64', '1,78', 'B+', 'República', 'Externo', 'São Lucas', 'Não possui', 'Incompleto', 'Incompleto', '', '', 'Sim', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Não', 'Não', 'Não', 'Sim', 'Sim', 'Sim', 'Sim', 'Sim', 'Sim', 'Não', 'Não', NULL, NULL, 'Não', 'Não', 'Não', 'Sim', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', '', '', '', '', '', '', '', 'Não', 'Não', 'Não', 'Não', 'Não', '', 'Adriana '),
(2, '   .   .   -  ', 'Franciele Alves', '12/12/1996', '', 'Bacharelado em Sistemas de Informação', '', '', '', '', '', '', '', '', '', '', '', '', '', 'F', '', '', '', 'Pais', 'Externo', 'Não', 'Não possui', NULL, NULL, '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', NULL, NULL, 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', NULL, 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', '', 'Não', '', '', '', '', '', '', '', 'Não', 'Não', 'Não', 'Não', 'Sim', '', ''),
(7, '   .   .   -  ', 'Jurema', '05/07/2010', '', 'Técnico em Informática - Integrado', '2020', '', '', '', '', '', '', '', '', '', '', '', '', 'F', '', '', '', 'Pais', 'Externo', 'Não', 'Não possui', 'Completo', 'Completo', '', '', 'Sim', NULL, 'Sim', NULL, NULL, NULL, NULL, NULL, 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', NULL, NULL, 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', 'Não', '', '', '', '', '', '', '', 'Não', 'Não', 'Não', 'Não', 'Não', '', '');

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuarios`
--

CREATE TABLE `usuarios` (
  `matricula` int(11) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `cpf` varchar(15) NOT NULL,
  `telefone` varchar(15) NOT NULL,
  `senha` varbinary(32) NOT NULL,
  `administrador` int(11) DEFAULT NULL,
  `ativado` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `usuarios`
--

INSERT INTO `usuarios` (`matricula`, `nome`, `cpf`, `telefone`, `senha`, `administrador`, `ativado`) VALUES
(142396, 'Rogério Costa Negro Rocha', '070.935.286-78', '(38)9 9122-4430', 0x33f7ef75b23d2cfed555d0d762a8c34f, 1, 1);

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
  MODIFY `idCursos` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de tabela `encaminhamentoshospital`
--
ALTER TABLE `encaminhamentoshospital`
  MODIFY `idEncaminhamentos` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de tabela `fichas`
--
ALTER TABLE `fichas`
  MODIFY `idFichas` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de tabela `pacientes`
--
ALTER TABLE `pacientes`
  MODIFY `idPaciente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

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
