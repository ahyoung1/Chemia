package chemia.httpsgithub.comahyoung1.chemia;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.TextView;

public class drawPage extends AppCompatActivity {
    private String centerAtomString = "";
    private String attachedAtomString = "";
    private String thirdUniqueAtomString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_page);
        TextView chemFormulaTV = (TextView) findViewById(R.id.input_chem_formula_tv);
        chemFormulaTV.setTextColor(Color.parseColor("#999999"));
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_home:
                startActivity(new Intent(this, MainActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onCenterAtomSet(){
        Spinner centerAtomSpinner = (Spinner)findViewById(R.id.centerAtomSpinner);
        this.centerAtomString = centerAtomSpinner.getSelectedItem().toString();
    }

    public void onAttachedAtomAdded(){
        Spinner attachedAtomSpinner = (Spinner) findViewById(R.id.atchd_Elements_spnr);

    }
}
