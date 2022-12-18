package com.example.codingpracticesqlite;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GroceryAdapter extends RecyclerView.Adapter<GroceryAdapter.GroceryViewHolder>{
    private final Context mContext;
    private Cursor mCursor;

    public GroceryAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }

    @NonNull
    @Override
    public GroceryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.grocery_item, parent, false);
        return new GroceryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroceryViewHolder holder, int position) {
        if(!mCursor.moveToPosition(position)){//to make sure that cursor can move to this position
            return;
        }
        @SuppressLint("Range") String name = mCursor.getString(mCursor.getColumnIndex(GroceryContract.GroceryEntry.COLUMN_NAME));
        @SuppressLint("Range") int amount = mCursor.getInt(mCursor.getColumnIndex(GroceryContract.GroceryEntry.COLUMN_AMOUNT));
        @SuppressLint("Range") long id = mCursor.getLong(mCursor.getColumnIndex(GroceryContract.GroceryEntry._ID));

        holder.textViewName.setText(name);
        holder.textViewAmount.setText(String.valueOf(amount));
        holder.itemView.setTag(id);//we can use this tag to uniquely identify the item in our MainActivity
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    //for everytime we want to update our database we pass a new cursor
    public void swapCursor(Cursor newCursor) {
        if(mCursor != null){
            mCursor.close();
        }
        mCursor = newCursor;
        if(newCursor != null){
            notifyDataSetChanged();
        }
    }

    public static class GroceryViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewAmount;
        public TextView textViewName;

        public GroceryViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textview_name_item);
            textViewAmount = itemView.findViewById(R.id.textview_amount_item);
        }
    }
}
