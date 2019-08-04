use portableu

create table if not exists time_sheet(
	entry_id INT NOT NULL AUTO_INCREMENT,
	sap_nr VARCHAR(2000),
	id_postepowanie INT NOT NULL,
	date_entry VARCHAR(12),
	time_passed INT,
	PRIMARY KEY (entry_id),
	FOREIGN KEY (id_postepowanie) REFERENCES lista(id_postepowanie)
	);
	