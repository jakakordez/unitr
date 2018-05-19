package net.unitr.unitr.Meeting;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import net.unitr.unitr.Model.User;
import net.unitr.unitr.R;

/**
 * Created by jakak on 19. 05. 2018.
 */

public class ParticipantItem extends LinearLayout {
    public View view;
    public User user;

    public ParticipantItem(Context context) {
        super(context);
    }

    public ParticipantItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setUser(User user) {
        this.user = user;
    }

    void init() {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.view_participant, null);


    }
}
