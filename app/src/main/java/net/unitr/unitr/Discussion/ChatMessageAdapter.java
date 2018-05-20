package net.unitr.unitr.Discussion;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.unitr.unitr.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import me.himanshusoni.chatmessageview.ChatMessageView;

/**
 * Created by Anze on 20/05/2018.
 */

public class ChatMessageAdapter extends RecyclerView.Adapter<ChatMessageAdapter.MessageHolder> {
    private final String TAG = "ChatMessageAdapter";
    private static final int MY_MESSAGE = 0, OTHER_MESSAGE = 1;

    private List<ChatMessage> mMessages;
    private Context mContext;

    public ChatMessageAdapter(Context context, List<ChatMessage> data) {
        mContext = context;
        mMessages = data;
    }

    @Override
    public int getItemCount() {
        return mMessages == null ? 0 : mMessages.size();
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage item = mMessages.get(position);

        if (item.isMine()) return MY_MESSAGE;
        else return OTHER_MESSAGE;
    }

    @Override
    public MessageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == MY_MESSAGE) {
            return new MessageHolder(LayoutInflater.from(mContext).inflate(R.layout.item_mine_message, parent, false));
        } else {
            return new MessageHolder(LayoutInflater.from(mContext).inflate(R.layout.item_other_message, parent, false));
        }
    }

    public void add(ChatMessage message) {
        mMessages.add(message);
        notifyItemInserted(mMessages.size() - 1);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final MessageHolder holder, final int position) {
        ChatMessage chatMessage = mMessages.get(position);
        if (chatMessage.isImage()) {
            holder.tvMessage.setVisibility(View.GONE);

            //holder.ivImage.setImageResource(R.drawable.img_sample);
        } else {
            holder.tvMessage.setVisibility(View.VISIBLE);

            holder.tvMessage.setText(chatMessage.getContent());
        }

        String date = new SimpleDateFormat("hh:mm aa", Locale.getDefault()).format(new Date());
        holder.tvTime.setText(date);
        if (!chatMessage.getColor().equals("")) {
            int color = Color.parseColor(chatMessage.getColor());
            holder.chatMessageView.setBackgroundColor(color, manipulateColor(color, 0.8f));
        }

        holder.chatMessageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public static int manipulateColor(int color, float factor) {
        int a = Color.alpha(color);
        int r = Math.round(Color.red(color) * factor);
        int g = Math.round(Color.green(color) * factor);
        int b = Math.round(Color.blue(color) * factor);
        return Color.argb(a,
                Math.min(r,255),
                Math.min(g,255),
                Math.min(b,255));
    }

    class MessageHolder extends RecyclerView.ViewHolder {
        TextView tvMessage, tvTime;
        ChatMessageView chatMessageView;

        MessageHolder(View itemView) {
            super(itemView);
            chatMessageView = (ChatMessageView) itemView.findViewById(R.id.chatMessageView);
            tvMessage = (TextView) itemView.findViewById(R.id.tv_message);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
        }
    }
}