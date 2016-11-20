package app.mobiledev.rpm.androidnoteusingrealm.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.mobiledev.rpm.androidnoteusingrealm.R;
import app.mobiledev.rpm.androidnoteusingrealm.model.Note;
import app.mobiledev.rpm.androidnoteusingrealm.util.TimeUtil;
import io.realm.RealmResults;

/**
 * Created by rmanacmol on 11/20/2016.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder>{

    private RealmResults<Note> noteList;
    private RecyclerItemClickListener recyclerItemClickListener;

    public NoteAdapter(RecyclerItemClickListener recyclerItemClickListener) {
        this.recyclerItemClickListener = recyclerItemClickListener;
    }

    public void setNoteList(RealmResults<Note> noteList){
        this.noteList = noteList;
        notifyDataSetChanged();
    }

    public Note getItem(int position){
        return noteList.get(position);
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_note, parent, false);

        final NoteViewHolder noteViewHolder = new NoteViewHolder(view);
        noteViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPos = noteViewHolder.getAdapterPosition();
                if (adapterPos != RecyclerView.NO_POSITION) {
                    if (recyclerItemClickListener != null) {
                        recyclerItemClickListener.onItemClick(adapterPos, noteViewHolder.itemView);
                    }
                }
            }
        });

        return noteViewHolder;
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        final Note note = noteList.get(position);

        holder.note.setText(note.getNote().length() > 50 ? note.getNote().substring(0, 50) : note.getNote());
        holder.date.setText(TimeUtil.unixToTimeAgo(note.getDateModified()));
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView note;
        TextView date;

        public NoteViewHolder(View itemView) {
            super(itemView);

            note = (TextView) itemView.findViewById(R.id.note);
            date = (TextView) itemView.findViewById(R.id.date);
        }
    }
}
