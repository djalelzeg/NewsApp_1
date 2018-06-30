package com.example.android.newsapp_1;

/**
 * Created by djalél on 30/06/2018.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;


/**
 * An {@link FootballAdapter} knows how to create a list item layout for each football news
 * in the data source (a list of {@link Football} objects).
 *
 * These list item layouts will be provided to an adapter view like ListView
 * to be displayed to the user.
 */
public class FootballAdapter extends ArrayAdapter<Football> {
  // list  for football news
    List<Football> mFootballList;
    /**
     * Constructs a new {@link FootballAdapter}.
     *
     * @param context of the app
     * @param footballList is the list of football news, which is the data source of the adapter
     */
    public FootballAdapter( Context context, List<Football> footballList) {
        super(context, 0, footballList);
        mFootballList = footballList;
    }

    /**
     * Returns a list item view that displays information about the football news at the given position
     * in the list of football.
     */
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
// Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View view = convertView;

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.football_list_item, parent, false);
        }
        // Find the football news at the given position in the list of football news
        Football currentFootball = mFootballList.get(position);
      // get the TextView with view timedate
        String timedDate = currentFootball.getDate().substring(0, 10);
        //Find the TextView with view ID date
        TextView tvDate = (TextView) view.findViewById(R.id.date_text_view);
        tvDate.setText(timedDate);
        //Find the TextView with view ID title
        TextView tvTitle = (TextView) view.findViewById(R.id.title_text_view);
        tvTitle.setText(currentFootball.getTitle());
        //Find the TextView with view ID type
        TextView tvType = (TextView) view.findViewById(R.id.type_text_view);
        tvType.setText(currentFootball.getSection());
// return the data for all the  views
        return view;
    }
}
