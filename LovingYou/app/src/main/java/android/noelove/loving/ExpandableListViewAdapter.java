package android.noelove.loving;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<String> groups;
    private ArrayList<ArrayList<Love>> child;

    // Constructor
    public ExpandableListViewAdapter(Context context, ArrayList<String> groups, ArrayList<ArrayList<Love>> child) {
        this.context = context;
        this.groups = groups;
        this.child = child;
    }

    // Method addItem
    public void addItem(Love lov) {
        if (!groups.contains(lov.getGroup())) {
            groups.add(lov.getGroup());
        }
        int index = groups.indexOf(lov.getGroup());
        if (child.size() < index + 1) {
            child.add(new ArrayList<Love>());
        }
        child.get(index).add(lov);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child.get(groupPosition).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //=============  GET CHILD VIEW
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Love lv = (Love) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.child, null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.tvChild);
        tv.setText("   " + lv.getName());
        tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

        if (lv instanceof Trang) {
            tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.trang, 0, 0, 0);
        } else if (lv instanceof Lan) {
            tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.lan, 0, 0, 0);
        } else if (lv instanceof Huyen) {
            tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.huyen, 0, 0, 0);
        }
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return child.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //============ GET GROUP VIEW
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String group = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.group, null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.tvGroup);
        tv.setText(group);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

}
