package pl.test.notes;

import pl.asap.entity.Notes;

public class MainStartTest {

	public static void main(String[] args) {
		
		Notes note1 = new Notes("Wielka majówka", "2019.04.21", "2019.04.29", 0);
		Notes note2 = new Notes("Mała majówka", "2019.02.14", "2019.03.16", 0);
		Notes note3 = new Notes("Wielka makówka", "2019.02.14", "2019.03.16", 0);
		new NewNote(10, note1, note2);
		new NewNote(14, note3);
		
	}

}
