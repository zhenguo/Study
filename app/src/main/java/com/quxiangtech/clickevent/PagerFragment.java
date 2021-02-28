package com.quxiangtech.clickevent;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.quxiangtech.myapplication.R;

public class PagerFragment extends Fragment {
    private ListView mListView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_pager_fragment, container);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mListView = view.findViewById(R.id.list);
        mListView.setAdapter(new Adapter(view.getContext()));
    }

    private static class Adapter extends ArrayAdapter<Adapter.ViewHolder> {

        public Adapter(@NonNull Context context) {
            super(context, R.layout.layout_list_item);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return super.getView(position, convertView, parent);
        }

        @Nullable
        @Override
        public ViewHolder getItem(int position) {
            return super.getItem(position);
        }

        private static class ViewHolder {
            ImageView imageView;
            TextView textView;
            public ViewHolder() {

            }
        }
    }
}
