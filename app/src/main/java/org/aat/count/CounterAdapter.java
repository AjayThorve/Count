package org.aat.count;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.LauncherActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AlertDialogLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;
import java.util.StringTokenizer;

import static android.R.id.input;

/**
 * Created by ajayanilthorve on 6/18/17.
 */

public class CounterAdapter extends RecyclerView.Adapter<CounterAdapter.ViewHolder>{

    private List<items_counter> listItems;
    private Context context;

    public CounterAdapter(List<items_counter> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.counters,parent,false);
        ViewHolder vh=new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        items_counter item=listItems.get(position);
        holder.Category.setText(item.getCategory());
        holder.Count.setText(String.format("%.2f", item.getCount()));


        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.C+=MainActivity.increment;
                holder.Count.setText(String.format("%.2f", holder.C));
            }
        });
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.C>0) {
                    holder.C-=MainActivity.increment;
                    holder.Count.setText(String.format("%.2f", holder.C));
                }
            }
        });
        holder.reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.C=0;
                holder.Count.setText(String.format("%.2f", holder.C));
            }
        });
        holder.Category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText e=new EditText(v.getContext());
                AlertDialog dialog;
                dialog=new AlertDialog.Builder(v.getContext()).create();
                dialog.setTitle(" Edit the text");
                dialog.setView(e, 100 ,50, 100 , 50);
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Save Text", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        holder.Category.setText(e.getText().toString());
                    }
                });

                e.setText(holder.Category.getText());
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView Count;
        private TextView Category;
        private ImageButton plus,minus,reset;

        float C=0;
        public ViewHolder(final View i) {
            super(i);
            View itemView=i;
            Category=(TextView) itemView.findViewById(R.id.Category);
            Count=(TextView) itemView.findViewById(R.id.text);
            plus=(ImageButton) itemView.findViewById(R.id.plus);
            minus=(ImageButton) itemView.findViewById(R.id.minus);
            reset=(ImageButton) itemView.findViewById(R.id.reset);
            final EditText input = new EditText(context);

        }


    }
}
