package net.unitr.unitr.Meeting;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class GeneralFragment extends MeetingFragment {

	TextView lblName, lblTimestamp, lblAddress;

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

		lblTimestamp.setText(Api.dateFormat.format(meeting.Timestamp));
		lblName.setText(meeting.Location.Name);
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

		return v;
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
