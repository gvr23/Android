package dsbmobile.com.app.database_login.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import dsbmobile.com.app.database_login.model.Credenciales;
import dsbmobile.com.app.database_login.R;

/**
 * Created by giova on 11/10/2017.
 */

public class CredencialesAdapter extends ArrayAdapter<Credenciales> {
    private ArrayList<Credenciales>  credenciales;
    private int resource;

    public CredencialesAdapter(Activity context,int resource, ArrayList<Credenciales> items) {
        super(context,resource ,items);
        this.resource = resource;
        credenciales = items;
    }

    @Override
    public int getCount() {
        return credenciales.size();
    }

    //escribe the translation between the data item and the View to display.
    // method that returns the actual view used as a row within the ListView at a particular position.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;

        //get the data item for this position
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(resource, null);
            viewHolder = new ViewHolder(view);

            //set the credential that is going to be displayed
            Credenciales credencial = getItem(position);

            //set properties into the views from the row
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
