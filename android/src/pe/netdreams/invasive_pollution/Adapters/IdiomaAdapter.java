package pe.netdreams.invasive_pollution.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import java.util.List;

import pe.netdreams.invasive_pollution.R;
import pe.netdreams.invasive_pollution.Utils.Idioma;

public class IdiomaAdapter extends BaseAdapter {

    private Context context;
    private List<Idioma> fruitList;

    private int selectedItemPosition = -1;

    public IdiomaAdapter(Context context, List<Idioma> fruitList) {
        this.context = context;
        this.fruitList = fruitList;
    }

    public void setSelectedItemPosition(int selectedItemPosition) {
        this.selectedItemPosition = selectedItemPosition;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return fruitList != null ? fruitList.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return fruitList != null ? fruitList.size() : 0;
    }

    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {

        View rootView = LayoutInflater.from(context)
                .inflate(R.layout.spinner_item_idioma, viewGroup, false);
        TextView txtName = rootView.findViewById(R.id.name);
        //ImageView image = rootView.findViewById(R.id.image);

        txtName.setText(fruitList.get(pos).getName());
        txtName.setAllCaps(true);
        Typeface typeface = ResourcesCompat.getFont(context, R.font.lilitaone_regular);
        txtName.setTypeface(typeface);

        return rootView;
    }
}
