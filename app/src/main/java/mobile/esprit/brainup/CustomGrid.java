package mobile.esprit.brainup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by macbookpro on 11/12/2016.
 */

public class CustomGrid extends BaseAdapter {
    private Context mContext;
    private final String[] AppName;
    private final int[] Logoid;

    public CustomGrid(String[] appName, int[] logoid, Context mContext) {
        this.AppName = appName;
        this.Logoid = logoid;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return AppName.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.grid_single, null);
            TextView textView = (TextView) grid.findViewById(R.id.grid_appName);
            ImageView imageView = (ImageView)grid.findViewById(R.id.grid_logo);
            textView.setText(AppName[position]);
            imageView.setImageResource(Logoid[position]);
        } else {
            grid = (View) convertView;
        }

        return grid;
    }
}
