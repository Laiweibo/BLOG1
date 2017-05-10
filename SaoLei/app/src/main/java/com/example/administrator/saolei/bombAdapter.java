package com.example.administrator.saolei;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**填充GridView的Adapter，这里主要用来控制显示和传递逻辑*/
public class bombAdapter extends BaseAdapter {

	private int level;
	private MapEntity entity;
	private Context context;
	private GridView gv;

	public bombAdapter(int level, Context context, GridView gv) {
		this.level = level;
		entity = new MapEntity(level);
		this.context = context;
		this.gv = gv;
	}
	@Override
	public int getCount() {
		return level*level;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.item_img, null);
		}
		((ImageView)convertView).setImageResource(getRes(getItem(position)));
		AbsListView.LayoutParams param = new AbsListView.LayoutParams(
				android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				gv.getWidth()/level);   //用传递进来的GridView计算宽度，并设置为单元格高度，保证棋盘是正方形
		convertView.setLayoutParams(param);
		return convertView;
	}

	/**根据单元格状态生成不同的图片*/
	private int getRes(ItemEntity entity){
		int id = 0;
		if(entity.isMark() && !entity.isMarkWrong()){
			id = R.drawable.i_flag;
		}else if(entity.isMark() && entity.isMarkWrong()){
			id = R.drawable.i14;
		}else if(!entity.isShow()){
			id = R.drawable.i00;
		}else if(entity.isbomb() && entity.isAutoShow()){
			id = R.drawable.i12;
		}else if(entity.isbomb() && !entity.isAutoShow()){
			id = R.drawable.i13;
		}else if(entity.getbombCount() == 0){
			id = R.drawable.i09;
		}else{
			id = context.getResources().getIdentifier("i0"+entity.getbombCount(), "drawable", context.getPackageName());
		}
		return id;
	}

	public boolean isWin(){
		return entity.isWin();
	}

	@Override
	public ItemEntity getItem(int position) {
		return entity.getEntity(position);
	}

	public void showbomb(){
		entity.showbomb();
		notifyDataSetChanged();
	}

	public void showsafearea(int position){
		entity.showsafearea(position);
		notifyDataSetChanged();
	}

	public void showMark(){
		entity.showMark();
		notifyDataSetChanged();
	}
}
