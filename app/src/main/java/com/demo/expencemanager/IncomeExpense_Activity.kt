package com.demo.expencemanager

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.expencemanager.Adapter.CatAdapter
import com.demo.expencemanager.Adapter.ModeAdapter
import com.demo.expencemanager.Helper.DataBaseHelper
import com.demo.expencemanager.ModelClass.CategoryModelClass
import com.demo.expencemanager.ModelClass.ModeModelclass
import com.example.expensemanagerproject.databinding.ActivityIncomeBinding
import com.example.expensemanagerproject.databinding.CategoryDialogBinding
import com.example.expensemanagerproject.databinding.ModeDialogBinding

import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class IncomeExpense_Activity : AppCompatActivity() {
    lateinit var binding: ActivityIncomeBinding
    var categorylist = ArrayList<CategoryModelClass>()
    var mode = ArrayList<ModeModelclass>()
    var type = -1
    var datevalue = ""
    var selectcategory = ""
    var selectmode = ""
    var id_number = 0
    var f = 0
    lateinit var db: DataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityIncomeBinding.inflate(layoutInflater)
        setContentView(binding.root)



        db = DataBaseHelper(this, "Database.db", null, 1)
        initview()


    }

    private fun initview() {

        if (intent != null && intent.hasExtra("updateRecord")) {
            f = 1
            val newamount: String? = intent.getStringExtra("amount")
            val newnote: String? = intent.getStringExtra("note")
            val newtitle: String? = intent.getStringExtra("title")
            val icon: String? = intent.getStringExtra("key_icon")
            id_number = intent.getIntExtra("id", 0)


            binding.edtamount.setText(newamount)
            binding.edtnote.setText(newnote)
            binding.txttitle.setText(newtitle)
            binding.imgdone.setText(icon)


        }
        binding.layoutcatg.setOnClickListener {

            val dialog = Dialog(this)
            val dialogBinding: CategoryDialogBinding = CategoryDialogBinding.inflate(layoutInflater)
            dialog.setContentView(dialogBinding.root)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window?.setLayout(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
            )


            dialogBinding.btnset.setOnClickListener {

                Toast.makeText(this, "Set", Toast.LENGTH_SHORT).show()

            }
            dialogBinding.btncancel.setOnClickListener {
                Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show()
            }

            categorylist = db.displaycategory()

            val categoryAdapter = CatAdapter(categorylist) { categoryname ->
                Log.e("TAG", "income: " + categoryname)
                selectcategory = categoryname
                binding.txtcategory.text = categoryname
            }

            dialogBinding.rcvcategory.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            dialogBinding.rcvcategory.adapter = categoryAdapter
            dialog.show()
            dialogBinding.btnset.setOnClickListener {
                dialog.dismiss()
            }
            dialogBinding.btncancel.setOnClickListener {
                dialog.dismiss()
            }
        }
        val page = intent.getStringExtra("page")
        val name: String? = intent.getStringExtra("title")

        when (page) {
            "income" -> binding.btnrbincome.isChecked = true
            "expense" -> binding.btnrbexpense.isChecked = true
        }
        binding.txttitle.text = name

        binding.back.setOnClickListener {

            onBackPressed()
        }


        binding.layoutmode.setOnClickListener {
//            mode.add("CASH")
//            mode.add("CREDIT CARD")
//            mode.add("DEBIT CARD")
//            mode.add("UPI")
//            mode.add("NET BANKING")
//            mode.add("CHEQUE")

            mode= db.DisplayMode()

            val dialog = Dialog(this)
            val dialogBinding: ModeDialogBinding = ModeDialogBinding.inflate(layoutInflater)
            dialog.setContentView(dialogBinding.root)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window?.setLayout(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
            )
            dialogBinding.btnset.setOnClickListener {
                Toast.makeText(this, "Set", Toast.LENGTH_SHORT).show()

            }
            dialogBinding.btncancel.setOnClickListener {
                Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show()
            }

//            categorylist = db.DisplayMode()


            val adapter = ModeAdapter(mode) { mode ->
                selectmode = mode
                binding.txtmodee.text = mode
            }
            dialogBinding.rcvmode.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            dialogBinding.rcvmode.adapter = adapter
            dialog.show()
            dialogBinding.btnset.setOnClickListener {
//                var cat = binding.txtcategory.text.toString()

                dialog.dismiss()
            }
            dialogBinding.btncancel.setOnClickListener {
                dialog.dismiss()
            }

        }


        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy")
        val date: String = simpleDateFormat.format(Date())
        binding.txtdate.text = date
        datevalue = date

//        val simpletimeformat = SimpleDateFormat("HH:mm")
//        val time: String = simpletimeformat.format(Date())
//        binding.txttime.text = time

        val myCalendar = Calendar.getInstance()
        val datapicker = DatePickerDialog.OnDateSetListener { view, year, month, dayofmonth ->

            myCalendar.set(Calendar.DAY_OF_MONTH, dayofmonth)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.YEAR, year)
            updateabal(myCalendar)

        }


        binding.txtdate.setOnClickListener {

            DatePickerDialog(
                this,
                datapicker,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        binding.imgdone.setOnClickListener {

            val amount = binding.edtamount.text.toString()
            val note = binding.edtnote.text.toString()
            if (amount.isEmpty()) {
                Toast.makeText(this, "please enter amount", Toast.LENGTH_SHORT).show()
            } else if (amount.length <= 1 || amount.length >= 10) {
                Toast.makeText(this, "please enter a amount", Toast.LENGTH_SHORT).show()
            } else {
                if (binding.rgincomeExpense.checkedRadioButtonId == -1) {

                } else {
                    val selectedId: Int = binding.rgincomeExpense.checkedRadioButtonId
                    val selectedRadioButton: RadioButton = findViewById(selectedId)
                    val text = selectedRadioButton.text.toString()

                    if (text.equals("Income")) {
                        type = 0
                    } else {
                        type = 1
                    }
                }
                if (f == 1) {
                    db.update(amount, note, id_number)
                } else {
                    db.insertIncomeExpense(
                        datevalue, amount, selectcategory, selectmode, type, note
                    )
                }
            }
            val trans = Intent(this, Transactions_Activity::class.java)
            trans.putExtra("amount", amount)
            trans.putExtra("note", note)
            trans.putExtra("date", date)
            startActivity(trans)


        }

    }

    private fun updateabal(myCalendar: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        binding.txtdate.setText(sdf.format(myCalendar.time))
    }

}


