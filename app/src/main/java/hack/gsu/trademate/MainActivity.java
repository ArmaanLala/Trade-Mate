package hack.gsu.trademate;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private Toolbar toolbar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        ImageButton add = findViewById(R.id.imageButton);
        //initialize recyclerview and FIrebase objects
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        mAuth = FirebaseAuth.getInstance();
        FirebaseAuth mFirebaseAuth;
        mFirebaseAuth = FirebaseAuth.getInstance();

         FirebaseAuth.AuthStateListener mAuthStateListener;
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();

                if( mFirebaseUser != null ){
                    Toast.makeText(MainActivity.this,"You are logged in",Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(MainActivity.this,"Please Login",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(i);
                }
            }
        };
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (mAuth.getCurrentUser() == null) {
                    Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(loginIntent);
                }
            }
        };
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intSignUp = new Intent(MainActivity.this, CreatePostActivity.class);
                startActivity(intSignUp);
            }
        });
    }

    @Override
    protected void onStart() {

        super.onStart();
        FirebaseAuth.AuthStateListener mAuthStateListener;
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseAuth mFirebaseAuth;
                mFirebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();

                if( mFirebaseUser == null ){
                    Toast.makeText(MainActivity.this,"You are logged in",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(MainActivity.this,"Please Login",Toast.LENGTH_SHORT).show();

                }
            }
        };
        DatabaseReference college = FirebaseDatabase.getInstance().getReference().child("users").child(mAuth.getCurrentUser().getDisplayName()).child("college");

        college.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s = dataSnapshot.getValue(String.class);
                final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Posts").child(s);
                mAuth.addAuthStateListener(mAuthListener);
                FirebaseRecyclerAdapter<Post, ViewHolder> FBRA = new FirebaseRecyclerAdapter<Post, ViewHolder>(
                        Post.class,
                        R.layout.card_items,
                        ViewHolder.class,
                        mDatabase
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Post model, int position) {
                        final String post_key = getRef(position).getKey().toString();
                        Button testWant = viewHolder.mView.findViewById(R.id.buttonWantToBuy);
                        viewHolder.setTitle(model.getTitle());
                        viewHolder.setDesc(model.getCost());
                        viewHolder.setImageUrl(getApplicationContext(), model.getImageUrl());
                        viewHolder.setUserName(model.getName());
                        viewHolder.setTakenButton(model.isTaken());
                        if(model!= null && model.getBuyer()!=null) {
                            if (model.getBuyer().isEmpty()) {


                                viewHolder.setBuyerText(model.getBuyer());
                            } else {
                                viewHolder.setBuyerText(model.getBuyer() + " has claimed this item");
                                testWant.setEnabled(false);
                            }
                        }

                        testWant.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DatabaseReference mDatabase;
                                testWant.setEnabled(false);
                                mDatabase = FirebaseDatabase.getInstance().getReference().child("Posts").child(s).child(model.getKey());
                                mDatabase.child("taken").setValue(true);
                                viewHolder.setTakenButton(true);
                                mDatabase.child("buyer").setValue(mAuth.getCurrentUser().getDisplayName());
                                viewHolder.setBuyerText(mAuth.getCurrentUser().getDisplayName()  + " has claimed this item");
                                Toast.makeText(MainActivity.this,"Congrats, it is now yours",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                };
                recyclerView.setAdapter(FBRA);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View mView;


        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setTitle(String title) {
            TextView post_title = mView.findViewById(R.id.post_title_txtview);
            post_title.setText(title);
        }

        public void setDesc(String desc) {
            TextView post_desc = mView.findViewById(R.id.post_desc_txtview);
            post_desc.setText(desc);
        }

        public void setImageUrl(Context ctx, String imageUrl) {
            ImageView post_image = mView.findViewById(R.id.post_image);
            Picasso.with(ctx).load(imageUrl).into(post_image);
//           Picasso.get().load("http://goo.gl/gEgYUd").into(post_image);
            //glideImage(ctx,post_image);
        }

        public void setUserName(String userName) {
            TextView postUserName = mView.findViewById(R.id.post_user);
            postUserName.setText(userName);
        }
        public void setBuyerText(String buy) {
            TextView postBuyer = mView.findViewById(R.id.post_buyer);
            postBuyer.setText(buy);
        }
         public void setTakenButton(boolean take){
              Button want = mView.findViewById(R.id.buttonWantToBuy);
                if (take==true) {
                    want.setText("The Object is taken");
                    want.setEnabled(false);
                    want.setBackgroundColor(Color.GREEN);
                } else {
//                  want.setBackgroundResource(android.R.drawable.btn_default);
                    want.setText("I want it ");
                    want.setEnabled(true);

//                    want.setBackgroundColor(Color.GREEN);

                }

            }

        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            mAuth.signOut();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed(){

    }
}

