package com.example.samplejson;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserAdapter{
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView, textView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            textView2=itemView.findViewById(R.id.textView2);
        }

        public void setItem(User user){
            textView.setText(user.id);
            textView2.setText(user.name);
        }
    }
}
