package hack.gsu.trademate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
public class LoadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)         {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_activity);
        (new Handler()).postDelayed(this::stopEveything, 5000);


        }

        public void stopEveything(){
            Intent i = new Intent(LoadActivity.this, MainActivity.class);
            startActivity(i);
        }
    }

