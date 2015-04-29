package com.example.ppe4_android;

import java.text.SimpleDateFormat;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class VisiteAdapter extends BaseAdapter {

	private List<Visite> listVisite;
	private LayoutInflater layoutInflater;
	
	public VisiteAdapter(Context context, List<Visite> vListVisite) {
		layoutInflater = LayoutInflater.from(context);
		listVisite = vListVisite;
	} 

	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listVisite.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listVisite.get(position);
	}


	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = layoutInflater.inflate(R.layout.vuevisite, null);
			holder.textViewIdentifiant = (TextView) convertView
					.findViewById(R.id.vueIdentifiant);
			holder.textViewNom = (TextView) convertView
					.findViewById(R.id.vueNom);
            holder.textViewSexe = (TextView) convertView
                    .findViewById(R.id.vueSexe);
			holder.textViewPrenom = (TextView) convertView
					.findViewById(R.id.vuePrenom);
			holder.textViewTelephone = (TextView) convertView
					.findViewById(R.id.vueTl);
			holder.textViewCodePostal = (TextView) convertView
					.findViewById(R.id.vueCp);
			holder.textViewAdresse = (TextView) convertView
					.findViewById(R.id.vueAdresse);
			holder.textViewVille = (TextView) convertView
					.findViewById(R.id.vueVille);
          //  holder.textDatePrevuDeb = (TextView) convertView
          //         .findViewById(R.id.vueDatePrevuDeb);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if (position % 2 == 0) {
			convertView.setBackgroundColor(Color.rgb(187, 210, 225));
		} else {
			convertView.setBackgroundColor(Color.rgb(255, 255, 255));
		}
		holder.textViewIdentifiant.setText(listVisite.get(position).getIdentifiant());
		holder.textViewNom.setText(listVisite.get(position).getNom());
        holder.textViewSexe.setText(listVisite.get(position).getSexe());
		holder.textViewPrenom.setText(listVisite.get(position).getPrenom());
		String s = listVisite.get(position).getTelephone();
		s = String.format("%s.%s.%s.%s.%s", s.substring(0, 2),
				s.substring(2, 4), s.substring(4, 6), s.substring(6, 8),
				s.substring(8, 10));
		holder.textViewTelephone.setText(s);
		holder.textViewAdresse.setText(listVisite.get(position).getAdresse());
		holder.textViewCodePostal.setText(listVisite.get(position).getCodePostal());
		holder.textViewVille.setText(listVisite.get(position).getVille());

      //  holder.textDatePrevuDeb.setText(listVisite.get(position).getDatePrevuDeb());



		return convertView;
	}

	
	private class ViewHolder {
		TextView textViewIdentifiant;
		TextView textViewNom;
		TextView textViewPrenom;
		TextView textViewTelephone;
		TextView textViewAdresse;
		TextView textViewCodePostal;
		TextView textViewVille;
        TextView textViewSexe;
       // TextView textDatePrevuDeb;



	}

	
}
