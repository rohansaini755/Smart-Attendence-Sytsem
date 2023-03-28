package com.example.geut.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.geut.MainActivity;
import com.example.geut.Pmodel.PostModel;
import com.example.geut.QrGen;
import com.example.geut.R;
import com.example.geut.SectionPage;
import com.example.geut.UserPage;
import com.example.geut.network.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private List<String> data;
    private Context context;
    public RecyclerViewAdapter(Context context, List<String> data)
    {
        this.context = context;
        this.data = data;
    }
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row,parent,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        String title = data.get(position);
        holder.section.setText(title);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView section;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            section = itemView.findViewById(R.id.section);
            section.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            Log.d("clicking","clicked");
//            String s = section.getText().toString();
            check1(section.getText().toString());
//            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
//
        }

        private void check1(String n)
        {
            PostModel postModel = new PostModel(n,1);
            Call<PostModel> call = RetrofitClient.getInstance().getMyApi().getsectionpassword(postModel);
            call.enqueue(new Callback<PostModel>() {
                @Override
                public void onResponse(Call<PostModel> call, retrofit2.Response<PostModel> response) {
                    PostModel mydata = response.body();
                    String password = mydata.getName();
                    Toast.makeText(context, password, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, QrGen.class);
                    intent.putExtra("SectionPassword",password);
                    context.startActivity(intent);
                }

                @Override
                public void onFailure(Call<PostModel> call, Throwable t) {
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
