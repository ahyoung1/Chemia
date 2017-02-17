package chemia.httpsgithub.comahyoung1.chemia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button tutorialBtn = (Button) findViewById(R.id.tutorial_btn);
        tutorialBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tutorialPage = new Intent(getApplicationContext(), chemia.httpsgithub.comahyoung1.chemia.tutorialPage.class);
                startActivity(tutorialPage);
            }
        });

        Button drawBtn = (Button) findViewById(R.id.draw_btn);
        drawBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent drawPage = new Intent(getApplicationContext(), chemia.httpsgithub.comahyoung1.chemia.drawPage.class);
                startActivity(drawPage);
            }
        });
    }
}
