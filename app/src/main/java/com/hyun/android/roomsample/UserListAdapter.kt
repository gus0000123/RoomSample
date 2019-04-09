package com.hyun.android.roomsample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hyun.android.roomsample.database.UserEntity
import com.hyun.android.roomsample.databinding.RvItemUserListBinding

class UserListAdapter(var callback: (user: UserEntity) -> Unit) :
    ListAdapter<UserEntity, UserListAdapter.ViewHolder>(UserEntityListDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListAdapter.ViewHolder {
        return ViewHolder(RvItemUserListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: UserListAdapter.ViewHolder, position: Int) {
        var userEntity = getItem(position)
        holder.bind(View.OnClickListener { callback(userEntity) }, userEntity)

    }

    class ViewHolder(val binding: RvItemUserListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(mOnClickListener: View.OnClickListener, mUserEntity: UserEntity) {
            binding.apply {
                clickListener = mOnClickListener
                user = mUserEntity
                executePendingBindings()
            }
        }
    }
}

private class UserEntityListDiffCallback : DiffUtil.ItemCallback<UserEntity>() {
    override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
        return true
    }

    override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
        return true
    }

}
