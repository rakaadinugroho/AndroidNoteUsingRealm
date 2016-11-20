package app.mobiledev.rpm.androidnoteusingrealm.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import app.mobiledev.rpm.androidnoteusingrealm.model.Note;
import app.mobiledev.rpm.androidnoteusingrealm.adapter.NoteAdapter;
import app.mobiledev.rpm.androidnoteusingrealm.R;
import app.mobiledev.rpm.androidnoteusingrealm.db.RealmDB;
import app.mobiledev.rpm.androidnoteusingrealm.adapter.RecyclerItemClickListener;
import io.realm.RealmResults;
import io.realm.Sort;

public class MainActivity extends AppCompatActivity implements RecyclerItemClickListener {

    private RecyclerView rvNote;
    private NoteAdapter noteAdapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rvNote = (RecyclerView) findViewById(R.id.rvNote);

        noteAdapter = new NoteAdapter(this);
        linearLayoutManager = new LinearLayoutManager(this);

        rvNote.setLayoutManager(linearLayoutManager);
        rvNote.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rvNote.setAdapter(noteAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveActivity.start(MainActivity.this, 0);
               // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData(){
        if(retrieve() != null)
            noteAdapter.setNoteList(retrieve());
    }

    public RealmResults<Note> retrieve() {
        RealmResults<Note> result = (RealmResults<Note>) new RealmDB(this).getAllData(Note.class);
        result.sort("dateModified", Sort.DESCENDING);
        return result;
    }

    @Override
    public void onItemClick(int position, View view) {
        SaveActivity.start(this, noteAdapter.getItem(position).getId());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
