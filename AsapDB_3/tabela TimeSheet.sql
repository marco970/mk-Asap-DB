use portableu

create table if not exists time_sheet(
	entry_id INT NOT NULL AUTO_INCREMENT,
	sap_nr VARCHAR(12),
	id_postepowanie INT NOT NULL,
	date_entry VARCHAR(12),
	time_passed INT,
	PRIMARY KEY (`entry_id`),
	FOREIGN KEY (`id_postepowanie`) REFERENCES lista(`id_postepowanie`)
	) ENGINE=INNODB;
	
	ALTER TABLE time_sheet MODIFY entry_id INTEGER NOT NULL AUTO_INCREMENT ;	
	ALTER TABLE time_sheet ADD FOREIGN KEY (`id_postepowanie`) REFERENCES lista(`id_postepowanie`);
	
	CREATE TABLE IF NOT EXISTS `time_sheet` (
  `entry_id` int(11) NOT NULL AUTO_INCREMENT,
  `sap_nr` varchar(12) NOT NULL,
  `id_postepowanie` int(11) NOT NULL,
  `date_entry` varchar(12) NOT NULL,
  `time_passed` int(11) NOT NULL,
  PRIMARY KEY (`entry_id`),
  KEY `id_postepowanie` (`id_postepowanie`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `time_sheet`
--
ALTER TABLE `time_sheet`
  ADD CONSTRAINT `time_sheet_ibfk_1` FOREIGN KEY (`id_postepowanie`) REFERENCES `lista` (`id_postepowanie`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
