package com.example.exercise_04;

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
import com.example.exercise_04.MapNode;

public class MainActivity extends ListActivity {

	private MyAdapter mAdapter = null;
	
	private static ArrayList<MapNode> mapArray = new ArrayList<MapNode>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mAdapter = new MyAdapter(this);
		setListAdapter(mAdapter);
		
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
