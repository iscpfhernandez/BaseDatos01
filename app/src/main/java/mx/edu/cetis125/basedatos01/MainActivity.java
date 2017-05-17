package mx.edu.cetis125.basedatos01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btIngresar = (Button) findViewById(R.id.btnIngresar);

        btIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //envía a la pantalla de menú con botones
                Intent i = new Intent(MainActivity.this, MenuBotones.class);
                startActivity(i);
                finish();
            }
        });

    }
}
