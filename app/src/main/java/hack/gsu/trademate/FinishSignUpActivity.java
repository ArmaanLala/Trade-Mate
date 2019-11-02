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

        if (user != null) {

            // User is signed in
        } else {
            Intent i = new Intent(FinishSignUpActivity.this, SignUpActivity.class);
            startActivity(i);
        }

        finishButton.setOnClickListener((v) -> {
            String name = editName.getText().toString();
            String school = editSchool.getText().toString();
            if (!name.isEmpty() && !school.isEmpty()){
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName("Jane Q. User")
                        .setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
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

                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
//                mDatabase.child("users").child(name);
                mDatabase.child("Classes").child(name).setValue(school);
                Intent intToMain = new Intent(FinishSignUpActivity.this, HomeActivity.class);
                startActivity(intToMain);

            }

        });
    }


}
