package iit.mahen.calculator;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import iit.mahen.calculator.ctrl.FormulaElement;
import iit.mahen.calculator.util.DoubleUtils;

public class MainActivity extends AppCompatActivity {

    EditText bar;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Log.e("Life Cycle", "create");
        setContentView(R.layout.activity_main);
        bar = (EditText) findViewById(R.id.editText);
        back = (Button) findViewById(R.id.back);
        bar.addTextChangedListener(textWatcher());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("Life Cycle", "stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Life Cycle", "destroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("Life Cycle", "pause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("Life Cycle", "resume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("Life Cycle", "restart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("Life Cycle", "start");
    }

    private TextWatcher textWatcher() {
        return new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!bar.getText().toString().equals("")) {
                    back.setVisibility(View.VISIBLE);
                } else {
                    back.setVisibility(View.GONE);
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }

    public void cal(View v) {
        String ss = bar.getText().toString();
        ss = ss.replace("π", "" + Math.PI);
        bar.setText(ss);
        if (ss.isEmpty()) {
            showMsg("Enter a formula");
        } else {
            try {
                Double.parseDouble(ss);
            } catch (Exception e) {
                cal(ss);
            }
        }
        bar.setSelection(bar.getText().toString().length());
    }

    public void clicked0(View v) {
        addVal("0");
    }

    public void clicked00(View v) {
        addVal("00", 1);
    }

    public void clicked1(View v) {
        addVal("1");
    }

    public void clicked2(View v) {
        addVal("2");
    }

    public void clicked3(View v) {
        addVal("3");
    }

    public void clicked4(View v) {
        addVal("4");
    }

    public void clicked5(View v) {
        addVal("5");
    }

    public void clicked6(View v) {
        addVal("6");
    }

    public void clicked7(View v) {
        addVal("7");
    }

    public void clicked8(View v) {
        addVal("8");
    }

    public void clicked9(View v) {
        addVal("9");
    }

    public void clickedPoint(View v) {
        addVal(".");
    }

    public void clickedPlus(View v) {
        addVal("+");
    }

    public void clickedSub(View v) {
        addVal("-");
    }

    public void clickedMul(View v) {
        addVal("*");
    }

    public void clickedDiv(View v) {
        addVal("/");
    }

    public void clickedSin(View v) {
        addVal("sin[]", 3);
    }

    public void clickedCos(View v) {
        addVal("cos[]", 3);
    }

    public void clickedTan(View v) {
        addVal("tan[]", 3);
    }

    public void clickedLog(View v) {
        addVal("log()", 3);
    }

    public void clickedOpening(View v) {
        addVal("(");
    }

    public void clickedClosing(View v) {
        addVal(")");
    }

    public void clickedPi(View v) {
        addVal("π");
    }

    public void clickedExp2(View v) {
        addVal("^(2)", 3);
    }

    public void clickedExpHalf(View v) {
        addVal("^(0.5)", 5);
    }

    public void clickedExp(View v) {
        addVal("^()", 1);
    }

    public void clear(View v) {
        bar.setText("");
    }

    public void clickedBack(View v) {
        int p = bar.getSelectionEnd();
        if(p>0){
            String ss = bar.getText().toString();
            String s1 = ss.substring(0, p - 1);
            String s2 = ss.substring(p);
            bar.setText(s1 + s2);
            bar.requestFocus();
            bar.setSelection(p - 1);
        }
    }

    private void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void cal(String ss) {
        try {
            FormulaElement fe = FormulaElement.parseFormula(ss, "");
            if (fe.isFullyGrounded()) {
                double d = fe.evaluate();
                bar.setText(DoubleUtils.getFormatted(d) + "");
            } else {
                showMsg("Undefined variables found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showMsg("Invalid formula!");
        }
    }

    private void addVal(String s) {
        int p = bar.getSelectionEnd();
        String ss = bar.getText().toString();
        String s1 = ss.substring(0, p);
        String s2 = ss.substring(p);
        bar.setText(s1 + s + s2);
        bar.requestFocus();
        bar.setSelection(p + 1);
    }

    private void addVal(String s, int pos) {
        addVal(s);
        int p = bar.getSelectionEnd();
        bar.setSelection(p + pos);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
