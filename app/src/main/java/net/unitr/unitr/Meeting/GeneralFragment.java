package net.unitr.unitr.Meeting;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import net.unitr.unitr.Model.Api;
import net.unitr.unitr.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class GeneralFragment extends MeetingFragment {

	TextView lblName, lblTimestamp, lblAddress;

	ArrayList<ImageButton> stars;
	Drawable yellowStar;
	Drawable star;
	LinearLayout lstStars;
	double mark = 1.0;

	public GeneralFragment() {
		// Required empty public constructor
	}
	MapView mMapView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_general, container, false);
		lblName = v.findViewById(R.id.lblName);
		lblTimestamp = v.findViewById(R.id.lblTimestamp);
		lblAddress = v.findViewById(R.id.lblAddress);

		 yellowStar = getActivity().getResources().getDrawable(R.drawable.ic_yellow_star);
		 star= getActivity().getResources().getDrawable(R.drawable.ic_white_star);

		lblTimestamp.setText(Api.outDateFormat.format(meeting.Timestamp));
		//lblName.setText(meeting.Location.Name);
		lblAddress.setText(meeting.Location.Address);

		mMapView = (MapView) v.findViewById(R.id.mapView);
		mMapView.onCreate(savedInstanceState);

		mMapView.getMapAsync(new OnMapReadyCallback() {
			@Override
			public void onMapReady(final GoogleMap mMap) {
				GoogleMap googleMap = mMap;
				googleMap.getUiSettings().setRotateGesturesEnabled(false);
				googleMap.getUiSettings().setTiltGesturesEnabled(false);

				//googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
				googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
					@Override
					public void onMapLoaded() {
						LatLng loc = meeting.Location.GetLatLng();
						mMap.addMarker(new MarkerOptions().position(loc).title(meeting.Location.Name));
						mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 15));
					}
				});
			}
		});

		lstStars = v.findViewById(R.id.lstStars);
		stars = new ArrayList<>();
		final int starsCount = 5;
		for(int i = 0;  i < starsCount; i++) {
			ImageButton img = new ImageButton(getContext());
			float factor = getResources().getDisplayMetrics().density;
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int)(40 * factor), (int)(40 * factor));
			params.setMargins(0, 0, 0, 0);
			img.setLayoutParams(params);
			img.setBackgroundResource(0);
			img.setTag(i+1);
			img.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					ImageButton v = (ImageButton)view;
					mark = (float)(int)v.getTag()/starsCount;
					updateStars();
				}
			});
			lstStars.addView(img);
			stars.add(img);
		}
		updateStars();
		return v;
	}

	public void updateStars(){
		for(int i = 0; i < stars.size(); i++){
			if((float)i/stars.size() < mark){
				stars.get(i).setImageDrawable(yellowStar);
			}
			else{
				stars.get(i).setImageDrawable(star);
			}
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		mMapView.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
		mMapView.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mMapView.onDestroy();
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		mMapView.onLowMemory();
	}
}
