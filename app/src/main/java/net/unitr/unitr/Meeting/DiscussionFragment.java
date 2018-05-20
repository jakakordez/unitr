package net.unitr.unitr.Meeting;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import net.unitr.unitr.Discussion.ChatMessage;
import net.unitr.unitr.Discussion.ChatMessageAdapter;
import net.unitr.unitr.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscussionFragment extends MeetingFragment{
	private ChatMessageAdapter mAdapter;
	private RecyclerView mRecyclerView;
	private EditText editMessage;
	private ImageButton sendMessage;

	public DiscussionFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_discussion, container, false);

		mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		editMessage = v.findViewById(R.id.text_message);
		sendMessage = v.findViewById(R.id.send_message);
		sendMessage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String message = editMessage.getText().toString();
				if (message.equals(""))
					message = "\uD83D\uDC4D";
				editMessage.setText("");
				sendMessage(message);
			}
		});

		mAdapter = new ChatMessageAdapter(this.getContext(), new ArrayList<ChatMessage>());
		mRecyclerView.setAdapter(mAdapter);
		sendMessage("Hello first message :) \uD83D\uDC4D");
		return v;
	}

	public void sendMessage(String message) {
		ChatMessage chatMessage = new ChatMessage(message, true, "#4caf50");
		mAdapter.add(chatMessage);

		mRecyclerView.scrollToPosition(mAdapter.getItemCount() - 1);
	}

	private void recieveMessage(String message, String color) {
		ChatMessage chatMessage = new ChatMessage(message, false, color);
		mAdapter.add(chatMessage);

		mRecyclerView.scrollToPosition(mAdapter.getItemCount() - 1);
	}
}
