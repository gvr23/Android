package dsbmobile.com.app.retrowilf.Adapter;

import android.content.ClipData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.LineNumberInputStream;
import java.util.ArrayList;
import java.util.List;

import dsbmobile.com.app.retrowilf.R;
import dsbmobile.com.app.retrowilf.model.Credencial;

/**
 * Created by giova on 11/15/2017.
 */

public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.ViewHolder> {
    private ArrayList<Credencial> credencialsList;
    private Context context;
    private int resource;

    public ShowAdapter(ArrayList<Credencial> credencialsList, Context context, int resource) {
        this.credencialsList = credencialsList;
        this.context = context;
        this.resource = resource;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(resource, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Credencial credencial = credencialsList.get(position);
        holder.tvUser.setText("Usuario: " + credencial.getUsuario());
        holder.tvPass.setText("Contraseña: " + credencial.getContrasena());
    }

    @Override
    public int getItemCount() {
        return credencialsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvUser;
        TextView tvPass;

        public ViewHolder(View itemView) {
            super(itemView);
            tvUser = (TextView) itemView.findViewById(R.id.tvUsuario);
            tvPass = (TextView) itemView.findViewById(R.id.tvContrasena);
        }
    }
}
