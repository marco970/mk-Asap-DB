use portableu

create table notes(
	note_id INT NOT NULL AUTO_INCREMENT,
	note VARCHAR(2000),
	id_postepowanie INT NOT NULL,
	date_open VARCHAR(12),
	date_modified VARCHAR(12),
	is_open INT NOT NULL,
	PRIMARY KEY (note_id),
	FOREIGN KEY (id_postepowanie) REFERENCES lista(id_postepowanie)
	);
	
	
	

)