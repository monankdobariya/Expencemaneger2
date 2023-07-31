package com.demo.expencemanager

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.expensemanagerproject.R
import com.example.expensemanagerproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initview()


    }


    private fun initview() {
        binding.imgmenu.setOnClickListener {
            binding.drawerlayout.openDrawer(binding.navView)

        }

        binding.navView.setOnClickListener {

            binding.drawerlayout.openDrawer(binding.navView)
        }


        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId)
            {
                R.id.calender -> {
                    val i = Intent(this, Calender_Activity::class.java)
                    startActivity(i)
                }

                R.id.Home -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }

                R.id.Category -> {
                    val intent = Intent(this, Category_Activity::class.java)
                    startActivity(intent)
                }

                R.id.payment -> {
                    val intent = Intent(this, PaymentModeActivity::class.java)
                    startActivity(intent)
                }

                R.id.reports -> {
                    val intent = Intent(this, ReportActivity::class.java)
                    startActivity(intent)
                }

                R.id.Share -> {
                    val shareIntent = Intent()
                    shareIntent.action = Intent.ACTION_SEND
                    shareIntent.type = "text/plain"
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                    startActivity(Intent.createChooser(shareIntent, getString(R.string.send_to)))

//                    val sendIntent = Intent()
//                    sendIntent.action = Intent.ACTION_SEND
//                    sendIntent.type = "text/plain"
//                    startActivity(sendIntent)
                }


                R.id.rate -> {
                    val dialogView = LayoutInflater.from(this).inflate(R.layout.rate_item, null)
                    val moodImageView = dialogView.findViewById<ImageView>(R.id.moodImageView)
                    val titleTextView = dialogView.findViewById<TextView>(R.id.titleTextView)
                    val messageTextView = dialogView.findViewById<TextView>(R.id.messageTextView)
                    val ratingBar = dialogView.findViewById<RatingBar>(R.id.ratingBar)
                    val rateButton = dialogView.findViewById<Button>(R.id.rateButton)
                    val cancelButton = dialogView.findViewById<Button>(R.id.cancelButton)

                    titleTextView.text = "Rate Us"
                    messageTextView.text = "Enjoying the app? Please take a moment to rate it."

                    val dialogBuilder = AlertDialog.Builder(this)
                    dialogBuilder.setView(dialogView)
                    dialogBuilder.setCancelable(false)

                    val dialog = dialogBuilder.create()

                    rateButton.setOnClickListener {
                        val rating = ratingBar.rating
                        if (rating > 3) {
                            moodImageView.setImageResource(R.drawable.smile)
                        } else {
                            moodImageView.setImageResource(R.drawable.smile)
                        }
                        dialog.dismiss()
                    }

                    cancelButton.setOnClickListener {
                        moodImageView.setImageResource(R.drawable.smile)
                        dialog.dismiss()
                    }

                    dialog.show()

                }

                R.id.termsofservice -> {
                    val openURL = Intent(Intent.ACTION_VIEW)
                    openURL.data = Uri.parse("https://www.websitepolicies.com/blog/sample-terms-service-template")
                    startActivity(openURL)
                }

                R.id.privacy -> {
                    val openURL = Intent(Intent.ACTION_VIEW)
                    openURL.data = Uri.parse("https://www.privacypolicyonline.com/")
                    startActivity(openURL)
                }

            }
            true
//        binding.calender.setOnClickListener {
//            val intent = Intent(this, Calender_Activity::class.java)
//            startActivity(intent)
//        }
//        binding.addCategory.setOnClickListener {
//            val intent = Intent(this, AddCategory_Activity::class.java)
//            startActivity(intent)
//        }
//        binding.home.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }
//        binding.payment.setOnClickListener {
//            val intent = Intent(this, PaymentModeActivity::class.java)
//            startActivity(intent)
//        }

        }
        binding.drawerlayout.setOnClickListener {
            binding.drawerlayout.closeDrawer(binding.navView)
        }

        var title_income = "Add Income"
        binding.layoutincome.setOnClickListener {
            val income = Intent(this, IncomeExpense_Activity::class.java)
            income.putExtra("page", "income")
            income.putExtra("title", title_income)
            startActivity(income)
        }
        var title_expense = "Add Expense"
        binding.layoutexpense.setOnClickListener {
            val expense = Intent(this, IncomeExpense_Activity::class.java)
            expense.putExtra("page", "expense")
            expense.putExtra("title", title_expense)
            startActivity(expense)
        }
        binding.layoutcategory.setOnClickListener {
            val intent = Intent(this, Category_Activity::class.java)
            startActivity(intent)
        }
        binding.layouttrans.setOnClickListener {
            val intent = Intent(this, Transactions_Activity::class.java)
            startActivity(intent)
        }
        binding.imgcalender.setOnClickListener {
            val intent = Intent(this, Calender_Activity::class.java)
            startActivity(intent)
        }

        binding.imgcrown.setOnClickListener {
//            val i = Intent(this, PremiumActivity::class.java)
//            startActivity(i)

            var i = Intent(this, PremiumActivity::class.java)
            startActivity(i)
//            Toast.makeText(this, "Premium Activty", Toast.LENGTH_SHORT).show()
        }
        binding.layoutcategory.setOnClickListener {
            val intent = Intent(this, Category_Activity::class.java)
            startActivity(intent)
        }
    }
}