package com.exs.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.exs.appexssynergy.ActivitySafetyPrecautions;
import com.exs.appexssynergy.Dashboard;
import com.exs.appexssynergy.R;

import java.util.List;

public class ListViewItemCheckboxBaseAdapter extends BaseAdapter {

    private List<ListViewItemDTO> listViewItemDtoList = null;

    private Context ctx = null;
    private int count = 0;
    private ActivitySafetyPrecautions ActivitySafetyPrecautions;

    public ListViewItemCheckboxBaseAdapter(Context ctx, List<ListViewItemDTO> listViewItemDtoList) {
        this.ctx = ctx;
        this.listViewItemDtoList = listViewItemDtoList;
        ActivitySafetyPrecautions = new ActivitySafetyPrecautions();
    }

    @Override
    public int getCount() {
        int ret = 0;
        if(listViewItemDtoList!=null)
        {
            ret = listViewItemDtoList.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int itemIndex) {
        Object ret = null;
        if(listViewItemDtoList!=null) {
            ret = listViewItemDtoList.get(itemIndex);
        }
        return ret;
    }

    @Override
    public long getItemId(int itemIndex) {
        return itemIndex;
    }

    @Override
    public View getView(int itemIndex, View convertView, ViewGroup viewGroup) {

        ListViewItemViewHolder viewHolder = null;

        if(convertView!=null)
        {
            viewHolder = (ListViewItemViewHolder) convertView.getTag();
        }else
        {
            convertView = View.inflate(ctx, R.layout.activity_safety_precautions_row, null);

            CheckBox listItemCheckbox = (CheckBox) convertView.findViewById(R.id.list_view_item_checkbox);

            TextView listItemText = (TextView) convertView.findViewById(R.id.list_view_item_text);
            final ImageView imgBox = (ImageView)convertView.findViewById(R.id.img_check);

            viewHolder = new ListViewItemViewHolder(convertView);

            viewHolder.setItemCheckbox(listItemCheckbox);

            viewHolder.setItemTextView(listItemText);

            convertView.setTag(viewHolder);

            imgBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(v.getTag() != "1") {
                        v.setTag("1");
                        imgBox.setImageResource(R.drawable.ic_check_box);
                        count += 1;
                        Log.d("Count", "count = " + count);
                        if (count == 9) {

                            Intent yourIntent = new Intent(ctx, Dashboard.class);
                            yourIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                            v.getContext().startActivity(yourIntent);

                        }
                    }
                }
            });
        }

        ListViewItemDTO listViewItemDto = listViewItemDtoList.get(itemIndex);
        viewHolder.getItemCheckbox().setChecked(listViewItemDto.isChecked());
        viewHolder.getItemTextView().setText(listViewItemDto.getItemText());

        return convertView;
    }

    public int getCurrentCount(){
        return count;
    }
}
