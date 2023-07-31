package com.demo.expencemanager.Adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demo.expencemanager.ModelClass.IncomeExpenseModelClass
import com.example.expensemanagerproject.R


class MainAdapter(var incomeExpenselist: ArrayList<IncomeExpenseModelClass>, var invoke:((IncomeExpenseModelClass)->Unit), var delete:((Int)-> Unit)) : RecyclerView.Adapter<MainAdapter.myview>() {
    class myview(view:View):RecyclerView.ViewHolder(view) {
        var txtincomeExpense: TextView = view.findViewById(R.id.txtincomeExpense)
        var txtdate: TextView = view.findViewById(R.id.txtdate)
        var txtamount: TextView = view.findViewById(R.id.txtamount)
        var txtnote: TextView = view.findViewById(R.id.txtnote)
        var txtcategory: TextView = view.findViewById(R.id.txtcategory)
        var txtmode: TextView = view.findViewById(R.id.txtmode)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myview {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.income_expense_item, parent, false)
        val view = myview(v)
        return view
    }

    override fun onBindViewHolder(holder: myview, position: Int) {
        holder.txtdate.setText(incomeExpenselist[position].date)
        holder.txtamount.setText(incomeExpenselist[position].amount)
        holder.txtcategory.setText(incomeExpenselist[position].category)
        holder.txtmode.setText(incomeExpenselist[position].mode)
        holder.txtincomeExpense.setText(incomeExpenselist[position].type.toString())
        holder.txtnote.setText(incomeExpenselist[position].note)

        if (holder.txtincomeExpense.text.toString()=="0")
        {
//            holder.txtincomeExpense.setTextColor(Color.GREEN)


            holder.txtdate.setTextColor(Color.WHITE)
            holder.txtcategory.setTextColor(Color.GREEN)
            holder.txtmode.setTextColor(Color.GREEN)
            holder.txtnote.setTextColor(Color.GREEN)
            holder.txtamount.setTextColor(Color.GREEN)
            Log.e("TAG", "onBindViewHolder: "+holder.txtincomeExpense.text.toString() )
        }
        else{
//            holder.txtincomeExpense.setTextColor(Color.RED)


            holder.txtdate.setTextColor(Color.WHITE)
            holder.txtcategory.setTextColor(Color.RED)
            holder.txtmode.setTextColor(Color.RED)
            holder.txtnote.setTextColor(Color.RED)
            holder.txtamount.setTextColor(Color.RED)
            Log.e("TAG", "onBindViewHolder: "+holder.txtincomeExpense.text.toString() )
        }


    }

    override fun getItemCount(): Int {
      return incomeExpenselist.size
    }

    fun update(incomeExpenselist: ArrayList<IncomeExpenseModelClass>){
        this.incomeExpenselist=ArrayList()
        this.incomeExpenselist.addAll(incomeExpenselist)
        notifyDataSetChanged()

    }
}