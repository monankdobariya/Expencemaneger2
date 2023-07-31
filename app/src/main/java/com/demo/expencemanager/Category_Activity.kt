package com.demo.expencemanager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.demo.expencemanager.Helper.DataBaseHelper
import com.demo.expencemanager.ModelClass.CategoryModelClass
import com.example.expensemanagerproject.databinding.ActivityCategoryBinding

class Category_Activity : AppCompatActivity() {

    lateinit var binding: ActivityCategoryBinding
    lateinit var db: DataBaseHelper
    var datalist = ArrayList<CategoryModelClass>()
//    var adapter = CategoryAdapter(categorylist)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DataBaseHelper(this, "Database.db", null, 1)

        category()
    }
    private fun category() {


        binding.btnadd.setOnClickListener {
            val name = binding.edtcategory.text.toString()
            db.insertCategory(name)
            datalist = db.displaycategory()
            Toast.makeText(this, "Category Added Sucessfully", Toast.LENGTH_SHORT).show()
//            adapter.update(datalist)
        }



        binding.imgback.setOnClickListener {

            onBackPressed()

        }

            datalist = db.displaycategory()

//            var categoryAdapter= CategoryAdapter()
//            binding.rcvcate.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//            binding.rcvcate.adapter = categoryAdapter

//            adapter.update(datalist)


        }
    }

