package com.wenlong.hope.MainActivityFragment;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.wenlong.hope.HighlightModel;
import com.wenlong.hope.R;

import java.util.ArrayList;

public class HighlightFragment extends Fragment {
    ArrayList<HighlightModel>listitems = new ArrayList<>();
    RecyclerView MyRecyclerView;
    String cards[] = {"This is LISTRA","RootTop Activities","Hydrotherapy Session","StrongMan in the house!","New Mail Confirmation Features"};
    int img[] = {R.mipmap.photo1,R.mipmap.photo2,R.mipmap.photo3,R.mipmap.photo4,R.mipmap.photo5};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_highlight,container,false);
        MyRecyclerView = root.findViewById(R.id.cardView);
        MyRecyclerView.setHasFixedSize(true);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
        if(listitems.size() > 0 & MyRecyclerView != null) {
            MyRecyclerView.setAdapter(new MyAdapter(listitems));
        }
        MyRecyclerView.setLayoutManager(MyLayoutManager);
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeList();
    }
    public void initializeList() {
        listitems.clear();

        for(int i=0;i<5;i++) {
            HighlightModel item = new HighlightModel();
            item.setCardName(cards[i]);
            item.setImageID(img[i]);
            item.setIsFav(0);
            item.setIsTurned(0);
            listitems.add(item);
        }
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTextView;
        public ImageView coverImageView,likeImageView,shareImageView;


        public MyViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            coverImageView = itemView.findViewById(R.id.coverImageView);
            likeImageView = itemView.findViewById(R.id.likeImageView);
            shareImageView = itemView.findViewById(R.id.shareImageView);
            likeImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = (int)likeImageView.getTag();
                    if(id == R.drawable.ic_like) {
                        likeImageView.setTag(R.drawable.ic_liked);
                        likeImageView.setImageResource(R.drawable.ic_liked);
                        Toast.makeText(getActivity(),"You had liked" + titleTextView.getText(),Toast.LENGTH_SHORT).show();
                    } else {
                        likeImageView.setTag(R.drawable.ic_like);
                        likeImageView.setImageResource(R.drawable.ic_like);
                        Toast.makeText(getActivity(),"You had unliked" + titleTextView.getText(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
            shareImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                            "://" + getResources().getResourcePackageName(coverImageView.getId())
                            + '/' + "drawable" + '/' + getResources().getResourceEntryName((int)coverImageView.getTag()));

                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_STREAM,imageUri);
                    shareIntent.setType("image/jpeg");
                    startActivity(Intent.createChooser(shareIntent,"Send to"));
                }
            });
        }
    }
    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        private ArrayList<HighlightModel> list;

        public MyAdapter(ArrayList<HighlightModel>Data) {
            list = Data;
        }
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycle_item,parent,false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.titleTextView.setText(list.get(position).getCardName());
            holder.coverImageView.setImageResource(list.get(position).getImageID());
            holder.coverImageView.setTag(list.get(position).getImageID());
            holder.likeImageView.setTag(R.drawable.ic_like);

        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }
}
