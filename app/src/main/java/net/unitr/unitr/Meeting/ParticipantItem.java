package net.unitr.unitr.Meeting;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.unitr.unitr.Model.User;
import net.unitr.unitr.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by jakak on 19. 05. 2018.
 */

public class ParticipantItem extends LinearLayout {
    public View view;
    User user;

    public ParticipantItem(User user, Context context) {
        super(context);
        this.user = user;
        init();
    }

    public ParticipantItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    void init() {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.view_participant, null);

        TextView lblName = view.findViewById(R.id.lblName);
        lblName.setText(user.Name);

        CircleImageView imgProfile = (CircleImageView)view.findViewById(R.id.profile_image);
        if(user.profilePicture != null) imgProfile.setImageBitmap(user.profilePicture);
        addView(view);
    }
}
