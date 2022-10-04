package com.example.flixster

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flixter2.CastMember
import com.example.flixter2.R

class CastRVA (
    private val members: List<CastMember>
        ) : RecyclerView.Adapter<CastRVA.MemberVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_cast_member, parent, false)
        return MemberVH(view)
    }

    inner class MemberVH(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: CastMember? = null
        val mName: TextView = mView.findViewById<View>(R.id.memberNameTv) as TextView
        val mPhoto = mView.findViewById<ImageView>(R.id.memberPhoto)

        override fun toString(): String {
            return mName.toString()
        }

    }

    override fun onBindViewHolder(holder: MemberVH, position: Int) {
        val member = members[position]

        holder.mItem = member
        holder.mName.text = member.memberName


        Glide.with(holder.mView)
            .load(BASE_URL + member.memberPhoto)
            .centerInside()
            .into(holder.mPhoto)
    }

    override fun getItemCount(): Int {
        return members.size
    }
}


