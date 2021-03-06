package com.example.ethan.loyaltycards;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by ethan on 2018/02/12.
 */

public class LoyaltyCardAdapter extends RecyclerView.Adapter<LoyaltyCardAdapter.MyViewHolder>{

    private DatabaseReference mDatabaseRef;
    private Context mContext;
    private List<LoyaltyCards> cardList;
    private CoordinatorLayout activityMain;

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView store,programme,id, barcode;
        public ImageView thumbnail, overflow;
        public CheckBox favourite;

        public MyViewHolder(View view){
            super(view);
            id = view.findViewById(R.id.id);
            store = view.findViewById(R.id.title);
            programme = view.findViewById(R.id.count);
            thumbnail = view.findViewById(R.id.thumbnail);
            overflow = view.findViewById(R.id.overflow);
            favourite = view.findViewById(R.id.favourite);
            barcode = view.findViewById(R.id.barcode);
        }
    }
    public LoyaltyCardAdapter(Context mContext, List<LoyaltyCards> cardList) {
        this.mContext = mContext;
        this.cardList = cardList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.loyaltycard,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final LoyaltyCards card = cardList.get(position);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Stores");

        //holder.setIsRecyclable(false);
        holder.store.setText(card.getStore());
        holder.programme.setText(card.getProgramme());
        holder.favourite.setChecked(card.getFavourite());
        holder.barcode.setText(card.getBarcode() != null ? card.getBarcode() : "Barcode");

        Glide.with(mContext).load(GetImage(mContext,card.getThumbnail())).into(holder.thumbnail);
        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);
            }
        });

        holder.favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.favourite.isChecked()){
                    holder.favourite.setChecked(true);
                    mDatabaseRef.child(card.getIdString()).child("Favourite").setValue(true);
                }
                else if(!holder.favourite.isChecked()){
                    holder.favourite.setChecked(false);
                    mDatabaseRef.child(card.getIdString()).child("Favourite").setValue(false);
                }
                notifyDataSetChanged();
            }
        });
    }

    public static int GetImage(Context c, String ImageName) {
        return c.getResources().getIdentifier(ImageName, "drawable", c.getPackageName());
    }

    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_loyalty, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Added to favourites", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_delete:
                    Toast.makeText(mContext, "Removed", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }
}




