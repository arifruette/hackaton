package com.example.hackaton.feature.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.hackaton.R;
import com.example.hackaton.model.Banner;

import java.util.List;

public class CustomBannerAdapter extends RecyclerView.Adapter<CustomBannerAdapter.ViewHolder>{
    private List<Banner> localDataSet;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView getName() {
            return name;
        }

        public TextView getCol() {
            return col;
        }

        public TextView getSum() {
            return sum;
        }

        private final TextView name;
        private final TextView col;
        private final TextView sum;




        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            name = (TextView) view.findViewById(R.id.namespace);
            col = (TextView) view.findViewById(R.id.colvo);
            sum = (TextView) view.findViewById(R.id.price);



        }


    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView
     */
    public CustomBannerAdapter(List<Banner> dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card, viewGroup, false);


        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Banner b = localDataSet.get(position);



        /*Glide.with(this)
                .asBitmap()
                .load(path)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        imageView.setImageBitmap(resource);
                    }
                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });*/
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getName().setText(b.getName());
        viewHolder.getCol().setText(b.getDescription());
        viewHolder.getSum().setText(b.getPrice() + "₽");


        //viewHolder.getCardView().setBackground(); // ВСТАВИТЬ ПИКЧУ

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }


}
