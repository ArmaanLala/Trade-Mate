package hack.gsu.trademate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        //initialize recyclerview and FIrebase objects
//        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setHasFixedSize(true);
//        mDatabase = FirebaseDatabase.getInstance().getReference().child("Blogzone");
//        mAuth = FirebaseAuth.getInstance();
//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                if (mAuth.getCurrentUser()==null){
//                    Intent loginIntent = new Intent(HomeActivity.this, SignUpActivity.class);
//                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);startActivity(loginIntent);
//                }
//            }
//        };
//    }
//    @Override
//    protected void onStart() {
//        super.onStart();
//        mAuth.addAuthStateListener(mAuthListener);
//        FirebaseRecyclerAdapter<Post, ViewHolder> FBRA = new FirebaseRecyclerAdapter<Post, ViewHolder>(
//                Post.class,
//                R.layout.card_items,
//                ViewHolder.class,
//                mDatabase
//        ) {
//            @Override
//            protected void populateViewHolder(ViewHolder viewHolder, Post model, int position) {
//                final String post_key = getRef(position).getKey().toString();
//                viewHolder.setTitle(model.getTitle());
//                viewHolder.setDesc(model.getDesc());
//                viewHolder.setImageUrl(getApplicationContext(), model.getImageUrl());
//                viewHolder.setUserName(model.getUsername());
//                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent singleActivity = new Intent(MainActivity.this, SinglePostActivity.class);
//                        singleActivity.putExtra("PostID", post_key);
//                        startActivity(singleActivity);
//                    }
//                });
//            }
//        };
//        recyclerView.setAdapter(FBRA);
//    }
//    public static class ViewHolder extends RecyclerView.ViewHolder{
//        View mView;
//        public ViewHolder(View itemView) {
//            super(itemView);
//            mView = itemView;
//        }
//        public void setTitle(String title){
//            TextView post_title = mView.findViewById(R.id.post_title_txtview);
//            post_title.setText(title);
//        }
//        public void setDesc(String desc){
//            TextView post_desc = mView.findViewById(R.id.post_desc_txtview);
//            post_desc.setText(desc);
//        }
//        public void setImageUrl(Context ctx, String imageUrl){
//            ImageView post_image = mView.findViewById(R.id.post_image);
//            Picasso.with(ctx).load(imageUrl).into(post_image);
//        }
//        public void setUserName(String userName){
//            TextView postUserName = mView.findViewById(R.id.post_user);
//            postUserName.setText(userName);
//        }
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
    }

}
