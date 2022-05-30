package com.vishalpal555.myapplication101;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewCustomAdapter extends RecyclerView.Adapter<RecyclerViewCustomAdapter.ViewHolder> {

    private Student[] localDataSet;
    private Button button;
    private TextView textViewId;
    private TextView textViewName;
    private TextView textViewPhone;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View view) {
            super(view);
//             Define click listener for the ViewHolder's View

            textView = (TextView) view.findViewById(R.id.textViewName);
        }

        public TextView getTextView() {
            return textView;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public RecyclerViewCustomAdapter(Student[] dataSet) {
        this.localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view_custom_layout, viewGroup, false);

        ViewHolder holder = new ViewHolder(view);

        button = view.findViewById(R.id.button3);
        textViewId = view.findViewById(R.id.textViewId);
        textViewName = view.findViewById(R.id.textViewName);
        textViewPhone = view.findViewById(R.id.textViewPhone);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int position = holder.getAdapterPosition();
                Toast.makeText(view.getContext(), "You clicked " +localDataSet[position] , Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
//        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        textViewId.setText(localDataSet[position].getId());
        textViewName.setText(localDataSet[position].getName());
        textViewPhone.setText(localDataSet[position].getPhoneNo());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.length;
    }
}

