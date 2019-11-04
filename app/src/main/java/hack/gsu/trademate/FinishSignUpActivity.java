package hack.gsu.trademate;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;

public class FinishSignUpActivity extends AppCompatActivity {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    EditText editName,editSchool;
    Button finishButton;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        FirebaseUser test = FirebaseAuth.getInstance().getCurrentUser();
        if ( test.getDisplayName() != null ){
            Intent num1 = new Intent(FinishSignUpActivity.this, LoadActivity.class);
            startActivity(num1);
        }

        finishButton = findViewById(R.id.buttonFinish);
        editName = findViewById(R.id.editName);
        editSchool = findViewById(R.id.editCollege);


        finishButton.setOnClickListener((v) -> {
            String name = editName.getText().toString();
            String school = editSchool.getText().toString().toUpperCase();
            if (!name.isEmpty() && !school.isEmpty()){
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(name)
                        //.setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
                        .build();

                user.updateProfile(profileUpdates)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Log.d(TAG, "User profile updated.");
                                }
                            }
                        });
                User userTemp = new User(name,school);
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.child("users").child(userTemp.getName()).setValue(userTemp);
//                mDatabase.child("Colleges").child(name).setValue(school);
                Intent intToMain = new Intent(FinishSignUpActivity.this, LoadActivity.class);
                startActivity(intToMain);

            }

        });
    }


}
