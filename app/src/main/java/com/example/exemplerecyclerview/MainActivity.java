package com.example.exemplerecyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.exemplerecyclerview.entities.Adherent;
import com.example.exemplerecyclerview.entities.Adherents;

public class MainActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        RecyclerView rcvAdherents = findViewById(R.id.rcvAdherents);
        Adherents adherents = getAdherents();
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(adherents);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

        // version GridView
        //RecyclerView.LayoutManager layoutManager2 = new GridLayoutManager(context, 3);

        // Effet sur le RecyclerView
        //rcvAdherents.setItemAnimator(new DefaultItemAnimator());

        rcvAdherents.setLayoutManager(layoutManager);
        rcvAdherents.setAdapter(recyclerViewAdapter);
    }

    private Adherents getAdherents() {
        Adherents adherents = new Adherents();

        Adherent adherent = null;

        for (int i = 1; i < 15; i++) {
            adherent = new Adherent("toto" + i, "titi" + i, 20 + i);
            adherents.add(adherent);
        }

        return adherents;
    }

    public class RecyclerViewAdapter extends RecyclerView.Adapter<AdherentHolder> {

        Adherents adherents;

        public RecyclerViewAdapter(Adherents adherents) {
            this.adherents = adherents;
        }

        @NonNull
        @Override
        public AdherentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adherent, parent, false);

            return new AdherentHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AdherentHolder adherentHolder, int position) {
            Adherent adherent = this.adherents.get(position);
            adherentHolder.setAdherent(adherent);
        }

        @Override
        public int getItemCount() {
            return adherents.size();
        }
    }

    public class AdherentHolder extends RecyclerView.ViewHolder {

        public final TextView txtNom;
        public final TextView txtPrenom;
        public final TextView txtAge;

        public AdherentHolder(@NonNull View itemView) {
            super(itemView);

            txtNom = itemView.findViewById(R.id.txtNom);
            txtPrenom = itemView.findViewById(R.id.txtPrenom);
            txtAge = itemView.findViewById(R.id.txtAge);
        }

        public void setAdherent(Adherent adherent) {
            txtNom.setText(adherent.getNom());
            txtPrenom.setText(adherent.getPrenom());
            txtAge.setText("" + adherent.getAge());
        }
    }


}
