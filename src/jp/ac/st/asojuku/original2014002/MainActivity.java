package jp.ac.st.asojuku.original2014002;

import android.os.Bundle;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

public class MainActivity extends Activity implements View.OnClickListener{

	SQLiteDatabase sdb = null;
	MySQLiteOpenHelper helper = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onResume(){
		super.onResume();
	Button btnENTRY =(Button)findViewById(R.id.btnENTRY);
	btnENTRY.setOnClickListener(this);

	Button btnMAINTE =(Button)findViewById(R.id.btnMAINTE);
	btnMAINTE.setOnClickListener(this);

	Button btnCLECK =(Button)findViewById(R.id.btnCLECK);
	btnCLECK.setOnClickListener(this);

	if(sdb == null) {
		helper = new MySQLiteOpenHelper(getApplicationContext());
	}
	try{
		sdb = helper.getWritableDatabase();
	}catch(SQLiteException e){
		return;
	}

	@Override
	public void onClick(View v) {

		Intent intent = null;

		switch(v.getId()){
		case R.id.btnENTRY:
			android.widget.EditText etv = (EditText)findViewById(R.id.edtMsg);
			String inputMsg =etv.getText().toString();
			if(inputMsg!=null && !inputMsg.isEmpty()){

				helper.insertHitokoto(sdb, inputMsg);
			}


			etv.setText("");
			break;
		case R.id.btnMAINTE:
			intent = new Intent(MainActivity.this, MaintenanceActivity.class);
			intent.putExtra("hitokoto", strHitokoto);

			startActivity(intent);
			break;

		}

	}


