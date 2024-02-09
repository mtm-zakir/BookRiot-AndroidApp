package com.riffna.bookriot.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.riffna.bookriot.R;
import com.riffna.bookriot.model.BookModel;

import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.MyViewHolder> {

    private List<BookModel> bookModelList;
    private BookListClickListener clickListener;

    public BookListAdapter(List<BookModel> bookModelList, BookListClickListener clickListener) {
        this.bookModelList = bookModelList;
        this.clickListener = clickListener;
    }

    public void updateData(List<BookModel> bookModelList) {
        this.bookModelList = bookModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list_recycler_row, parent, false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.bookName.setText(bookModelList.get(position).getName());
        holder.bookDelivery.setText("Delivery: "+bookModelList.get(position).getDelivery());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(bookModelList.get(position));
            }
        });
        Glide.with(holder.thumbImage)
                .load(bookModelList.get(position).getImage())
                .into(holder.thumbImage);

    }

    @Override
    public int getItemCount() {
        return bookModelList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView  bookName;
        TextView  bookDelivery;
        ImageView thumbImage;

        public MyViewHolder(View view) {
            super(view);
            bookName = view.findViewById(R.id.bookName);
            bookDelivery = view.findViewById(R.id.bookDelivery);
            thumbImage = view.findViewById(R.id.thumbImage);

        }
    }

    public interface BookListClickListener {
        public void onItemClick(BookModel bookModel);
    }
}
