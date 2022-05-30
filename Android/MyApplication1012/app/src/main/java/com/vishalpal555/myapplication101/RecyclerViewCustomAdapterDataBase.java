package com.vishalpal555.myapplication101;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Field;
import java.util.List;
import java.util.zip.Inflater;

public class RecyclerViewCustomAdapterDataBase extends RecyclerView.Adapter<RecyclerViewCustomAdapterDataBase.ViewHolder> {

    private List<Student> localDataSet;
    private TextView textViewStudentName;
    private TextView textViewStudentPhoneNumber;
    private Button buttonUpdateStudentDetails;
    private ImageView profileImage;
    private Button buttonDeleteStudent;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            textView = (TextView) view.findViewById(R.id.textView);
            textViewStudentName = view.findViewById(R.id.textViewStudentNameShow);
            textViewStudentPhoneNumber = view.findViewById(R.id.textViewStudentPhoneNumberShow);
            buttonUpdateStudentDetails = view.findViewById(R.id.buttonUpdateStudentDetails);
            buttonDeleteStudent = view.findViewById(R.id.buttonDeleteStudent);
            profileImage = view.findViewById(R.id.imageViewStudent);
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
    public RecyclerViewCustomAdapterDataBase(List<Student> dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerViewCustomAdapterDataBase.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.view_data_base_custom_layout, viewGroup, false);
        RecyclerViewCustomAdapterDataBase.ViewHolder holder = new RecyclerViewCustomAdapterDataBase.ViewHolder(view);

        return holder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        textViewStudentName.setText(localDataSet.get(position).getName());
        textViewStudentPhoneNumber.setText(localDataSet.get(position).getPhoneNo());
//        viewHolder.setIsRecyclable(true);
//        Log.d("RecyclerViewCustomAdapterDatBase", "onBindViewHolder: viewHolder.isRecyclable()" +String.valueOf(viewHolder.isRecyclable()));
//        StringBuffer profileImageString = new StringBuffer();
//        profileImageString.append("profile_image_");
//        Field field = null;
//        try {
//            profileImageString.append(localDataSet.get(position).getId());
//            field = R.drawable.class.getDeclaredField(String.valueOf(profileImageString));
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }
//        try {
//            int profileImageID = field.getInt(field); // use this id to set background resource
//            profileImage.setImageResource(profileImageID);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
        profileImage.setImageResource(R.drawable.profile_image_1);
        buttonUpdateStudentDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "you clicked " +localDataSet.get(position).getId(), Toast.LENGTH_SHORT).show();
            }
        });
        buttonDeleteStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StudentHandler studentHandler = new StudentHandler(view.getContext(), "StudentsDataBase", null, 1);
                studentHandler.deleteStudentById(Integer.parseInt(localDataSet.get(position).getId()));
                Log.d("RecyclerViewCustomAdapterDataBAse", "onClick: button delete student : deleted id= " +localDataSet.get(position).getId());
                localDataSet.remove(position);
                notifyItemRemoved(position);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
