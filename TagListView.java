/**
 * 
 */
package com.huahan.qimen.view.tag;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.huahan.qimen.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kince
 * @category ģ������Ӧ�õײ�tagview
 * 
 */
public class TagListView extends FlowLayout implements OnClickListener {

	private boolean mIsDeleteMode;
	private OnTagCheckedChangedListener mOnTagCheckedChangedListener;
	private OnTagClickListener mOnTagClickListener;
	private int mTagViewBackgroundResId;
	private int mTagViewTextColorResId;
	private final List<Tag> mTags = new ArrayList<Tag>();
	private final List<View> views = new ArrayList<View>();
//	private int last=-1;
	private Context context;
	private int black, white;

	/**
	 * @param context
	 */
	public TagListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context = context;
		init();
	}

	/**
	 * @param context
	 * @param attributeSet
	 */
	public TagListView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		// TODO Auto-generated constructor stub
		this.context = context;
		init();
	}

	/**
	 * @param context
	 * @param attributeSet
	 * @param defStyle
	 */
	public TagListView(Context context, AttributeSet attributeSet, int defStyle) {
		super(context, attributeSet, defStyle);
		// TODO Auto-generated constructor stub
		this.context = context;
		init();
	}

	@Override
	public void onClick(View v) {
		Tag localTag = (Tag) v.getTag();
		TextView textView = (TextView) v.findViewById(R.id.tv_tag);
		if (!localTag.isChecked()) {
			localTag.setChecked(true);
			textView.setTextColor(white);
			textView.setBackgroundResource(R.drawable.shape_yuan_btn_bg);
//			if(last!=-1){
//				View view=views.get(last);
//				Tag tag=(Tag)view.getTag();
//				tag.setChecked(false);
//				TextView tv = (TextView) view.findViewById(R.id.tv_tag);
//				tv.setTextColor(black);
//				tv.setBackgroundResource(R.drawable.shape_yuan_jiao_hui_kuang);
//			}
//			last = localTag.getId();
			if (this.mOnTagClickListener != null) {
				this.mOnTagClickListener.onTagClick(v, localTag,true);
			}
		} else {
//			last=-1;
			localTag.setChecked(false);
			textView.setTextColor(black);
			textView.setBackgroundResource(R.drawable.shape_yuan_jiao_hui_kuang_wu);
			if (this.mOnTagClickListener != null) {
				this.mOnTagClickListener.onTagClick(v, localTag,false);
			}
		}
		
	}
	
//	public void chongZhi(){
//		if(last!=-1){
//			View view=views.get(last);
//			Tag tag=(Tag)view.getTag();
//			tag.setChecked(false);
//			TextView tv = (TextView) view.findViewById(R.id.tv_tag);
//			tv.setTextColor(black);
//			tv.setBackgroundResource(R.drawable.shape_yuan_jiao_hui_kuang);
//			last=-1;
//		}
//	}

	public View getView(int position) {
		return views.get(position);
	}

	private void init() {

	}

	private void inflateTagView(final Tag t, boolean b) {
		View view = View.inflate(getContext(), R.layout.item_tag, null);
		TextView textView = (TextView) view.findViewById(R.id.tv_tag);

		textView.setText(t.getTitle());
		view.setTag(t);
		if(t.isChecked()){
			textView.setTextColor(white);
			textView.setBackgroundResource(R.drawable.shape_yuan_btn_bg);
//			last=t.getId();
		}

		view.setOnClickListener(this);
		views.add(view);
		addView(view);
	}

	public void addTag(int i, String s) {
		addTag(i, s, false);
	}

	public void addTag(int i, String s, boolean b) {
		addTag(new Tag(i, s), b);
	}

	public void addTag(Tag tag) {
		addTag(tag, false);
	}

	public void addTag(Tag tag, boolean b) {
		mTags.add(tag);
		inflateTagView(tag, b);
	}

	public void addTags(List<Tag> lists) {
		addTags(lists, false);
	}

	public void addTags(List<Tag> lists, boolean b) {
		for (int i = 0; i < lists.size(); i++) {
			addTag((Tag) lists.get(i), b);
		}
	}

	public List<Tag> getTags() {
		return mTags;
	}

	public View getViewByTag(Tag tag) {
		return findViewWithTag(tag);
	}

	public void removeTag(Tag tag) {
		mTags.remove(tag);
		removeView(getViewByTag(tag));
	}

	public void setDeleteMode(boolean b) {
		mIsDeleteMode = b;
	}

	public void setOnTagCheckedChangedListener(
			OnTagCheckedChangedListener onTagCheckedChangedListener) {
		mOnTagCheckedChangedListener = onTagCheckedChangedListener;
	}

	public void setOnTagClickListener(OnTagClickListener onTagClickListener) {
		mOnTagClickListener = onTagClickListener;
	}

	public void setTagViewBackgroundRes(int res) {
		mTagViewBackgroundResId = res;
	}

	public void setTagViewTextColorRes(int res) {
		mTagViewTextColorResId = res;
	}

	public void setTags(List<? extends Tag> lists) {
		black = ContextCompat.getColor(context, R.color.black_text);
		white = ContextCompat.getColor(context, R.color.white);
		setTags(lists, false);
	}

	public void setTags(List<? extends Tag> lists, boolean b) {
		removeAllViews();
		mTags.clear();
		for (int i = 0; i < lists.size(); i++) {
			addTag((Tag) lists.get(i), b);
		}
	}

	public static abstract interface OnTagCheckedChangedListener {
		public abstract void onTagCheckedChanged(Tag tag);
	}

	public static abstract interface OnTagClickListener {
		public abstract void onTagClick(View v, Tag tag, boolean xuan);
	}

}
