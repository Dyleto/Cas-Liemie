package com.example.ppe4_android;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.os.Environment;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class modele {

	private String db4oFileName;
	private ObjectContainer dataBase;
	private File appDir;
	
	public modele() {
		createDirectory();
		open();
		// si partie import non dÃ©veloppÃ©e
		//chargeDataBase();
		dataBase.close();
	}
	
	public void open() {

		db4oFileName = Environment.getExternalStorageDirectory() + "/baseDB4o"
				+ "/BasePatient.db4o";
		dataBase = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
				db4oFileName);
	}

	
	public void createDirectory() {
		appDir = new File(Environment.getExternalStorageDirectory()
				+ "/baseDB4o");
		if (!appDir.exists() && !appDir.isDirectory()) {
			appDir.mkdirs();
		}
	}

	
	public ArrayList<Visite> listVisite() {
		open();
		ArrayList<Visite> listVisite = new ArrayList<Visite>();
		ObjectSet<Visite> result = dataBase.queryByExample(Visite.class);
		while (result.hasNext()) {
            listVisite.add(result.next());
		}
		dataBase.close();
		return listVisite;
	}

	
	public Visite trouveVisite (String id) {
		open();
        Visite vretour = new Visite();
		vretour.setIdentifiant(id);
		ObjectSet<Visite> result = dataBase.queryByExample(vretour);
		vretour = (Visite) result.next();
		dataBase.close();
		return vretour;
	}

	
	public void saveVisite(Visite visite) {
		open();
        Visite vretour = new Visite();
		vretour.setIdentifiant(visite.getIdentifiant());
		ObjectSet<Visite> result = dataBase.queryByExample(vretour);
		if (result.size() == 0) {
			dataBase.store(visite);
		} else {
			vretour = (Visite) result.next();
			vretour.recopieVisite(visite);
			dataBase.store(vretour);
		}
		dataBase.close();
	}
 
/*	public void chargeDataBase() {
		try {
			ObjectSet<Visite> result = dataBase.queryByExample(Visite.class);
			if (result.size() == 0) {
				try {
					dataBase.store(new Visite("30/04/2015 10:20:00","1001", "Dupont", "paul",
							"10 rue Anne Frank", "49000", "angers",
							"0624553212", "0",
							new SimpleDateFormat("dd/MM/yyyy")
									.parse("15/03/1945")
                   ));
					dataBase.commit();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					dataBase.store(new Visite("30/04/2015 10:20:00","1002", "Lulu", "Isabelle",
							"10 Avenue des arts et mÃ©tiers", "49000", "angers",
							"0624553212", "1",
							new SimpleDateFormat("dd/MM/yyyy")
									.parse("15/03/1954")));
					dataBase.commit();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					dataBase.store(new Visite("30/04/2015 10:20:00","1003", "Caolin", "Etienne",
							"10 rue Boisnet", "49000", "angers", "0624553212","1",
							 new SimpleDateFormat(
									"dd/MM/yyyy").parse("15/03/2012")));
					dataBase.commit();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					dataBase.store(new Visite(" 30/04/2015 10:20:00","1004", "Breche", "Alfred",
							"25 rue du quinconce", "49000", "angers",
							"0623553211", "0",
							new SimpleDateFormat("dd/MM/yyyy")
									.parse("15/08/1964")));
					dataBase.commit();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					dataBase.store(new Visite("30/04/2015 10:20:00","1005", "Centaure", "Marie",
							"20 rue des lutins", "49000", "angers",
							"0744553212", "1",
							new SimpleDateFormat("dd/MM/yyyy")
									.parse("15/05/1951")));
					dataBase.commit();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} finally {
			dataBase.close();
		}
	}*/

	public void addVisite(ArrayList<Visite> vVisite) {
		open();
		for (Visite v : vVisite) {
			dataBase.store(v);
		}
		dataBase.close();
	}

	public void deleteVisite() {
		open();
		ObjectSet<Visite> result = dataBase.queryByExample(Visite.class);
		while (result.hasNext()) {
			dataBase.delete(result.next());
		}
		dataBase.close();
	}


	
}
