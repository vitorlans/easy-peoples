package com.easy.gpessoal.adapters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.easy.gpessoal.DetalhesContatosActivity;
import com.easy.gpessoal.R;
import com.easy.gpessoal.models.Usuarios;
import com.easy.gpessoal.views.CircularContact;
import com.easy.gpessoal.views.PinnedHeaderListView;
import com.easy.gpessoal.views.PinnedHeaderListView.PinnedHeaderAdapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.SectionIndexer;
import android.widget.TextView;

public class ContatosAdapter extends BaseAdapter implements Filterable,
		SectionIndexer, PinnedHeaderAdapter, OnScrollListener {
	private int mLocationPosition = -1;
	private List<String> mFriendsSections;
	private List<String> mFriendsSections2;

	private List<Integer> mFriendsPositions;
	private LayoutInflater mInflater;
	private Context mContext;

	private List<Usuarios> mUsuariosFilterList;
	private ValueFilter valueFilter;

	private List<Usuarios> mList;

	public ContatosAdapter(Context context, List<Usuarios> lu,
			List<String> friendsSections, List<Integer> friendsPositions) {

		mInflater = LayoutInflater.from(context);

		this.mContext = context;
		this.mList = lu;
		this.mUsuariosFilterList = lu;
		this.mFriendsSections = friendsSections;

		this.mFriendsSections2 = new ArrayList<String>();
		this.mFriendsSections2.addAll(friendsSections);

		this.mFriendsPositions = friendsPositions;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	class ViewHolder {
		public CircularContact friendProfileCircularContactView;
		public TextView friendName, headerView;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder holder;
		final View rootView;
		if (convertView == null) {
			holder = new ViewHolder();
			rootView = mInflater.inflate(R.layout.frg_contat_listview_item, parent, false);
			holder.friendProfileCircularContactView = (CircularContact) rootView
					.findViewById(R.id.listview_item__friendPhotoImageView);
			holder.friendProfileCircularContactView.getTextView().setTextColor(
					0xFFffffff);

			holder.friendName = (TextView) rootView
					.findViewById(R.id.listview_item__friendNameTextView);
			rootView.setTag(holder);
		} else {
			rootView = convertView;
			holder = (ViewHolder) rootView.getTag();
		}
		final Usuarios contact = mList.get(position);
		final String displayName = contact.getNome();

		holder.friendProfileCircularContactView.setTextAndBackgroundColor(
				displayName.substring(0, 1), Color.parseColor(contact.getImagem()));

		holder.friendName.setText(displayName);

		rootView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent n = new Intent(mContext, DetalhesContatosActivity.class);
				n.putExtra("_id", contact.getId());
				mContext.startActivity(n);
			}
		});

		return rootView;
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		if (view instanceof PinnedHeaderListView) {
			((PinnedHeaderListView) view).configureHeaderView(firstVisibleItem);
		}
	}

	@Override
	public int getPinnedHeaderState(int position) {
		int realPosition = position;
		if (realPosition < 0
				|| (mLocationPosition != -1 && mLocationPosition == realPosition)) {
			return PINNED_HEADER_GONE;
		}
		mLocationPosition = -1;
		int section = getSectionForPosition(realPosition);
		int nextSectionPosition = getPositionForSection(section + 1);
		if (nextSectionPosition != -1
				&& realPosition == nextSectionPosition - 1) {
			return PINNED_HEADER_PUSHED_UP;
		}
		return PINNED_HEADER_VISIBLE;
	}

	@Override
	public void configurePinnedHeader(View header, int position, int alpha) {
		// TODO Auto-generated method stub
		int realPosition = position;
		int section = getSectionForPosition(realPosition);
		String title = (String) getSections()[section];

		((TextView) header.findViewById(R.id.header_text)).setText(title);
	}

	@Override
	public Object[] getSections() {
		// TODO Auto-generated method stub
		return mFriendsSections.toArray();
	}

	@Override
	public int getPositionForSection(int section) {
		if (section < 0 || section >= mFriendsSections.size()) {
			return -1;
		}
		return mFriendsPositions.get(section);
	}

	@Override
	public int getSectionForPosition(int position) {
		// TODO Auto-generated method stub
		if (position < 0 || position >= getCount()) {
			return -1;
		}
		int index = Arrays.binarySearch(mFriendsPositions.toArray(), position);
		return index >= 0 ? index : -index - 2;
	}

	private int select = 0;
	private Random s = new Random();

	public int RandomColor() {
		int[] op = mContext.getResources().getIntArray(
				R.array.contacts_text_background_colors);

		select = (s.nextInt(op.length));

		return op[select];
	}

	@Override
	public Filter getFilter() {

		if (valueFilter == null) {
			valueFilter = new ValueFilter();
		}
		return valueFilter;
	}

	private class ValueFilter extends Filter {
		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults results = new FilterResults();

			if (constraint != null && constraint.length() > 0) {
				List<Usuarios> filterList = new ArrayList<Usuarios>();
				for (int i = 0; i < mUsuariosFilterList.size(); i++) {
					if ((mUsuariosFilterList.get(i).getNome().toUpperCase())
							.contains(constraint.toString().toUpperCase())|| (mUsuariosFilterList.get(i).getEmail().toUpperCase())
							.contains(constraint.toString().toUpperCase())) {

						Usuarios u = new Usuarios();
						u.setId(mUsuariosFilterList.get(i).getId());
						u.setNome(mUsuariosFilterList.get(i).getNome());
						u.setImagem(mUsuariosFilterList.get(i).getImagem());

						filterList.add(u);
					}
				}
				results.count = filterList.size();
				results.values = filterList;
			} else {
				results.count = mUsuariosFilterList.size();
				results.values = mUsuariosFilterList;
			}
			return results;

		}

		@SuppressWarnings("unchecked")
		@Override
		protected void publishResults(CharSequence constraint,
				FilterResults results) {

			mFriendsSections.clear();
			mFriendsSections.add("");

			mList = (ArrayList<Usuarios>) results.values;
			notifyDataSetChanged();
		}

	}

	public void clearFilter() {

		mFriendsSections.clear();
		mFriendsSections.addAll(mFriendsSections2);
		mList = mUsuariosFilterList;
		notifyDataSetChanged();

	}

}
