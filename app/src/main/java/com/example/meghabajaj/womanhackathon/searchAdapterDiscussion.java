package com.example.meghabajaj.womanhackathon;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by MEGHA BAJAJ on 11-03-2018.
 */

public class searchAdapterDiscussion extends RecyclerView.Adapter<searchAdapterDiscussion.viewHolder> {
    Context context;
    ArrayList<String> Question;
    ArrayList<String> Answer;
    class viewHolder extends RecyclerView.ViewHolder{
        TextView question,answer;
        public viewHolder(View itemView) {
            super(itemView);
            question=(TextView)itemView.findViewById(R.id.txtQuestionDiscussion);
            answer=(TextView)itemView.findViewById(R.id.txtAnswerDiscussion);
        }
    }
    public searchAdapterDiscussion(Context context,ArrayList<String> Question,ArrayList<String> Answer){

        this.context=context;
        this.Question=Question;
        this.Answer=Answer;

    }

    @Override
    public searchAdapterDiscussion.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.activity_tab1_news_feed,parent,false);
        return new searchAdapterDiscussion.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(searchAdapterDiscussion.viewHolder holder, int position) {
        holder.question.setText(Question.get(position));
        holder.answer.setText(Answer.get(position));
    }


    @Override
    public int getItemCount() {
        return Question.size();
    }
}
