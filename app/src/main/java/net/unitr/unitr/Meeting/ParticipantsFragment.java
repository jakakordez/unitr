package net.unitr.unitr.Meeting;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import net.unitr.unitr.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ParticipantsFragment extends MeetingFragment {
	LinearLayout lstParticipants;

	public ParticipantsFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_participants, container, false);
		lstParticipants = v.findViewById(R.id.lstParticipants);
		for(ParticipantItem p : meeting.getParticipantList(getContext())){
			lstParticipants.addView(p);
		}

		return v;
	}

}
