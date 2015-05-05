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
		dataBase.close();
	}
	
	public void open() {

		db4oFileName = Environment.getExternalStorageDirectory() + "/baseDB4o"
				+ "/BaseVisite.db4o";
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

	
	public ArrayList<Visite> listeVisite() {
		open();
		ArrayList<Visite> listeVisite = new ArrayList<Visite>();
		ObjectSet<Visite> result = dataBase.queryByExample(Visite.class);
		while (result.hasNext()) {
            listeVisite.add(result.next());
		}
		dataBase.close();
		return listeVisite;
	}

	
	public Visite trouveVisite (String idV) {
		open();
        Visite vretour = new Visite();
		vretour.setIdV(idV);
		ObjectSet<Visite> result = dataBase.queryByExample(vretour);
		vretour = (Visite) result.next();
		dataBase.close();
		return vretour;
	}

	
	public void saveVisite(Visite visite) {
		open();
        Visite vretour = new Visite();
	    vretour.setIdV(visite.getIdV());
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
