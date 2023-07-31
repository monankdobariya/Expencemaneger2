package com.demo.expencemanager.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.demo.expencemanager.ModelClass.ModeModelclass
import com.example.expensemanagerproject.R


class ModeAdapter(var mode: ArrayList<ModeModelclass>, var invoke:((String)->Unit)) : RecyclerView.Adapter<ModeAdapter.myview>() {
    var pos = -1

    class myview(view: View) : RecyclerView.ViewHolder(view) {
        var btnrbmode: RadioButton = view.findViewById(R.id.btnrbmode)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myview {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.mode_item, parent, false)
        val adapter = myview(view)
        return adapter
    }

    override fun onBindViewHolder(holder: myview, position: Int) {
        holder.btnrbmode.setText(mode[position].name)

        invoke.invoke(mode[position].name)

        holder.btnrbmode.setOnClickListener {
            pos=position
            notifyDataSetChanged()
        }
        if (position==pos){
            holder.btnrbmode.isChecked=true
        }
        else{
            holder.btnrbmode.isChecked=false
        }

    }

    override fun getItemCount(): Int {
        return mode.size
    }
}