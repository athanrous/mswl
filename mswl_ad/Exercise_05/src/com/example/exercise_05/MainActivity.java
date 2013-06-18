package com.example.exercise_05;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exercise_04.R;
import com.example.exercise_05.MapNode;

public class MainActivity extends ListActivity {

	private MyAdapter mAdapter = null;
	
	private static ArrayList<MapNode> mapArray = new ArrayList<MapNode>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mAdapter = new MyAdapter(this);
		setListAdapter(mAdapter);
		setData();
		
	}

	 protected void onListItemClick(ListView l, View v, int pos, long id) {

	        boolean spam = ((pos + 1) % 3 == 0);

	        if (spam) {

	            Toast.makeText(this,
	                    this.getResources().getString(R.string.pub),
	                    Toast.LENGTH_SHORT).show();

	        } else {

	            pos = pos - ((pos + 1) / 3);

	            MapNode selectedNode = mapArray.get(pos);
	            Intent intentMapsExercise = new Intent(MainActivity.this,
	                    MapsExtActivity.class);
	            intentMapsExercise.putExtra("node", selectedNode);

	            startActivity(intentMapsExercise);
	        }
	    }
	
	
	
	 private void setData() {

	        mapArray.clear();

	        MapNode myMnode = new MapNode();

	        
	        myMnode.mapTitle = this.getResources().getString(R.string.title1);
	        myMnode.mapDesc = this.getResources().getString(
	                R.string.description1);
	        myMnode.mapImage = R.drawable.athens;
	        
	        // Universidad Rey Juan Carlos
	        myMnode.mapLon = -3.819945;
	        myMnode.mapLat = 40.281523;
	        
		
	        
	        mapArray.add(myMnode);

	        
	        MapNode mynode2 = new MapNode();

	        mynode2.mapTitle = this.getResources().getString(R.string.title2);
	        mynode2.mapDesc = this.getResources().getString(
	                R.string.description2);
	        mynode2.mapImage = R.drawable.madrid;
	        // University of Macedonia
	        mynode2.mapLon = 22.959616;
	        mynode2.mapLat = 40.624995;
	        
		
	        
	        mapArray.add(mynode2);

	        MapNode mynode3 = new MapNode();

	        mynode3.mapTitle = this.getResources().getString(R.string.title3);
	        mynode3.mapDesc = this.getResources().getString(
	                R.string.description3);
	        mynode3.mapImage = R.drawable.nyc;
	        // Democritus University of Thrace
	        mynode3.mapLon = 24.887895;
	        mynode3.mapLat = 41.141995;
	        
		
	        
	        mapArray.add(mynode3);

	        mapArray.addAll(mapArray);

	    } 

	 
	   public static class MyAdapter extends BaseAdapter {

	        private Context mapContext;

	        public MyAdapter(Context context) {
	            // TODO Auto-generated constructor stub
	           
	            mapContext = context;
	        }

	        @Override
	        public int getCount() {
	            return mapArray.size() + (mapArray.size() / 2) + (mapArray.size() % 2);
	        }

	        @Override
	        public Object getItem(int position) {
	            return mapArray.get(position);
	        }

	        @Override
	        public long getItemId(int position) {
	            // TODO Auto-generated method stub
	            
	            return 0;
	        }

	        @Override
	        public View getView(int position, View convertView, ViewGroup parent) {

	            View view;

	            LayoutInflater inflater = (LayoutInflater) mapContext
	                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	            boolean advertise = ((position + 1) % 3 == 0);

	            if (advertise) {

	                view = inflater.inflate(R.layout.pub_item, null);

	                TextView titleTextView = (TextView) view
	                        .findViewById(R.id.pubs);
	                titleTextView.setText(R.string.pub);
	                titleTextView.setBackgroundColor(Color.YELLOW);

	            } else {

	                position = position - ((position + 1) / 3);

	                view = inflater.inflate(R.layout.main_list, null);
	                TextView titleTextView = (TextView) view
	                        .findViewById(R.id.title);
	                titleTextView.setText(mapArray.get(position).mapTitle);

	                TextView descriptionTextView = (TextView) view
	                        .findViewById(R.id.description);
	                descriptionTextView.setText(mapArray.get(position).mapDesc);

	                ImageView imageViewLogo = (ImageView) view
	                        .findViewById(R.id.image);
	                imageViewLogo.setImageDrawable(mapContext.getResources()
	                        .getDrawable(mapArray.get(position).mapImage));

	            }
	            return view;
	        }

	    }
			
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
