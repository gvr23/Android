package dsbmobile.com.app.practicadatabase.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import dsbmobile.com.app.practicadatabase.Model.Credencial;
import dsbmobile.com.app.practicadatabase.R;

/**
 * Created by giova on 11/12/2017.
 */

public class CredencialesAdapter extends ArrayAdapter<Credencial>{
    private ArrayList<Credencial> credencialArrayList;
    private  int resource;

    public CredencialesAdapter(Context context, int resource, ArrayList<Credencial> items) {
        super(context, resource, items);
        this.resource = resource;
        credencialArrayList = items;
    }

    @Override
    public int getCount() {
        return  credencialArrayList.size();
    }

    //writes the translations between the data item and the view to be displayed in
    //method that returns the actual view as a rpw within the listview at a particular position
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(resource, null);
            viewHolder = new ViewHolder(view);

            Credencial credencial = getItem(position);

            viewHolder.tvUser.setText("Usuario: " + credencial.getUsuario());
            viewHolder.tvPass.setText("Contraseña: " + credencial.getContrasena());
        }
        return view;
    }

    static class ViewHolder{
        TextView tvUser;
        TextView tvPass;

        public ViewHolder(View base){
            tvUser = (TextView) base.findViewById(R.id.tvUsuario);
            tvPass = (TextView) base.findViewById(R.id.tvContrasena);
        }
    }
}
