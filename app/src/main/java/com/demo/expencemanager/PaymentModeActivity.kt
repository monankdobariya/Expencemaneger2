package com.demo.expencemanager

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.demo.expencemanager.Helper.DataBaseHelper
import com.demo.expencemanager.ModelClass.CategoryModelClass
import com.demo.expencemanager.ModelClass.ModeModelclass
import com.example.expensemanagerproject.databinding.ActivityPaymentModeBinding

class PaymentModeActivity : AppCompatActivity() {
    lateinit var binding: ActivityPaymentModeBinding
    lateinit var db: ModeModelclass
    var datalst = ArrayList<ModeModelclass>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentModeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initview()
    }

    private fun initview() {


        var db = DataBaseHelper(this, "Database.db", null, 1)
        binding.btnApply.setOnClickListener {

        }




        binding.btnApply.setOnClickListener {
            val name = binding.edtPaymentMode.text.toString()
            db.insertMode(name)
            datalst = db.DisplayMode()


            if (name.isEmpty())
            {

                Toast.makeText(this, "Enter Your Payment Mode", Toast.LENGTH_SHORT).show()
            }
            else {
//                db.InsertModeData(name)
                Toast.makeText(this, "Data Added Succesfully", Toast.LENGTH_SHORT).show()
            }

        }




        binding.imgBack.setOnClickListener {

            onBackPressed()
        }

    }
}