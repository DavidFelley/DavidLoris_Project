package com.example.davidloris_project.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.davidloris_project.Model.Answer;
import com.example.davidloris_project.R;
import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageHolder>{
    private List<Answer> messages = new ArrayList<>();

    @NonNull
    @Override
    public MessageAdapter.MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_item, parent, false);
        return new MessageHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.MessageHolder messageHolder, int position) {
        Answer currentMessage = messages.get(position);
        messageHolder.textViewIdMessage.setText(String.valueOf(currentMessage.getIdAnswer()));
        messageHolder.textViewMessageContent.setText(String.valueOf(currentMessage.getTextAnswer()));
        messageHolder.textViewPseudo.setText(String.valueOf(currentMessage.getIdAutor()));
        messageHolder.textViewDateMessage.setText(String.valueOf(currentMessage.getDate()));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void setMessages(List<Answer> messages) {
        this.messages = messages;
        notifyDataSetChanged();
    }

    class MessageHolder extends RecyclerView.ViewHolder {
        private TextView textViewIdMessage;
        private TextView textViewMessageContent;
        private TextView textViewPseudo;
        private TextView textViewDateMessage;


        public MessageHolder(@NonNull View itemView) {
            super(itemView);
            textViewIdMessage = itemView.findViewById(R.id.text_view_idMessage);
            textViewMessageContent = itemView.findViewById(R.id.text_view_message);
            textViewPseudo = itemView.findViewById(R.id.text_view_pseudoInMessage);
            textViewDateMessage = itemView.findViewById(R.id.text_view_dateInMessage);
        }
    }
}
