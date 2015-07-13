package com.androiddao;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.PopupMenu.OnMenuItemClickListener;

import com.dao.CarDAO;
import com.dao.DAOFactory;
import com.model.Car;


public class Tela01 extends Activity implements OnClickListener, OnItemClickListener, OnMenuItemClickListener {

	private ArrayList<Car> listacars;
	private ListView list_allCars;
	//private ArrayAdapter<Car> adaptcars;
	//-----------------------------------------------------------
	private EditText et_carname, et_year, et_factory;
	private Button bt_confirm;
	private Car newCar;
	
	private PopupMenu popupMenu;
	
	private boolean filled;
	
	private Car selectedCar;
	private int seletedCarposition = -1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela01);
		et_carname = (EditText) findViewById(R.id.et_carname); 
		et_year = (EditText) findViewById(R.id.et_year); 
		et_factory = (EditText) findViewById(R.id.et_factory); 
		
		list_allCars = (ListView) findViewById(R.id.list_allCars);
		list_allCars.setOnItemClickListener(this);
		
		bt_confirm = (Button) findViewById(R.id.bt_confirm);
		bt_confirm.setOnClickListener(this);
		
		fillListCars();
	}
	//---------------------------------------------------------------------
	//---------------------------------------------------------------------
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.options, menu);
		return true;
	}
	//---------------------------------------------------------------------
	//---------------------------------------------------------------------
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	//---------------------------------------------------------------------
	//---------------------------------------------------------------------
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		seletedCarposition = position;
		selectedCar = (Car) list_allCars.getItemAtPosition(position);
		
		popupMenu = new PopupMenu(Tela01.this, view); 
		popupMenu.setOnMenuItemClickListener(this);
		popupMenu.inflate(R.menu.options);
		popupMenu.show();
	}
	//---------------------------------------------------------------------
	//---------------------------------------------------------------------
	@Override
	public boolean onMenuItemClick(MenuItem item) {
		
		if(item.getItemId()== R.id.option_delete){
		
			DAOFactory dao = new DAOFactory(this);
			dao.openConnection();
			CarDAO carDAO = dao.createCarDAO();
			carDAO.deleteCar(selectedCar);
			dao.closeConnection();
			
			//listacars.remove(seletedCarposition);
			fillListCars();
		}
		
		return false;
	}
	//---------------------------------------------------------------------
	//---------------------------------------------------------------------	
	@Override
	public void onClick(View v) {
		
		if(v.getId() == R.id.bt_confirm){
			
			newCar = new Car();
			String name, factory;
			int year;
			
			name = et_carname.getText().toString(); 
			factory = et_factory.getText().toString();
			year = Integer.parseInt(et_year.getText().toString());
			
			newCar.setName(name);
			newCar.setYear(year);
			newCar.setFactory(factory);
			
			DAOFactory dao = new DAOFactory(this);
			dao.openConnection();
			CarDAO carDAO = dao.createCarDAO();
			carDAO.insertCar(newCar);
			dao.closeConnection();
			
			cleanFields();
			fillListCars();
		}
	}
	
	public void cleanFields(){
		et_carname.setText(""); 
		et_year.setText(""); 
		et_factory.setText(""); 
	}
	
	public void fillListCars(){
		ArrayAdapter<Car> adaptcars;
		
		//if(filled == false){
			DAOFactory dao = new DAOFactory(this);
			dao.openConnection();
			CarDAO carDAO = dao.createCarDAO();
			listacars = carDAO.selectAllCars();
			dao.closeConnection();
			adaptcars = new ArrayAdapter<Car>(this, android.R.layout.simple_list_item_1, listacars);
			filled = true;
		//}
		list_allCars.setAdapter(adaptcars);
	}
}
