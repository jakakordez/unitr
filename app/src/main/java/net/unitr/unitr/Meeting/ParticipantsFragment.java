package net.unitr.unitr.Meeting;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import net.unitr.unitr.Model.User;
import net.unitr.unitr.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ParticipantsFragment extends MeetingFragment {

	ListView lstParticipants;
	public ParticipantsFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_participants, container, false);
		lstParticipants = (ListView) v.findViewById(R.id.lstParticipants);

		lstParticipants.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, meeting.Users));

		return v;
	}

}
