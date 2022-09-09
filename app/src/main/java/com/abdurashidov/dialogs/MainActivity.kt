package com.abdurashidov.dialogs

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.abdurashidov.dialogs.databinding.ActivityMainBinding
import com.abdurashidov.dialogs.databinding.ItemCustomDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @SuppressLint("InflateParams", "ShowToast")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {


            //Alert Dialog
            alertDialog.setOnClickListener {
                val dialog = AlertDialog.Builder(this@MainActivity)

                dialog.setTitle("Open Alert Dialog")
                dialog.setCancelable(false)
                dialog.setPositiveButton("Ok"
                ) { dialog, which ->
                    Toast.makeText(this@MainActivity, "Ok", Toast.LENGTH_SHORT).show()
                }

                dialog.setNegativeButton("Cancel", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        Toast.makeText(this@MainActivity, "Cancacel", Toast.LENGTH_SHORT).show()
                    }
                })

                dialog.setNeutralButton("Bekor qilish", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        Toast.makeText(this@MainActivity, "Bekor qilish", Toast.LENGTH_SHORT).show()
                    }
                })
                dialog.show()
            }


            //Custom Dialog

            customDialog.setOnClickListener {

                val alertDialog = AlertDialog.Builder(this@MainActivity)
                val dialog = alertDialog.create()

                dialog.setTitle("Title Custom dialog")
                val dialogView = layoutInflater.inflate(R.layout.item_custom_dialog, null, false)
                dialog.setView(dialogView)
                dialogView.findViewById<TextView>(R.id.close).setOnClickListener {

                    Toast.makeText(this@MainActivity, "Close Custom Dialog", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                dialog.show()
            }


            //Fragment Dialog
            fragmentDialog.setOnClickListener {
                val dialogFragment=DialogFragment.newInstance("Fist param", "Second param")
                dialogFragment.show(supportFragmentManager.beginTransaction(), "tag")
            }


            //DatePickerDialog
            datePickerDialog.setOnClickListener {
                val datePickerDialog=DatePickerDialog(this@MainActivity)
                datePickerDialog.setOnDateSetListener { view, year, month, dayOfMonth ->
                    Toast.makeText(this@MainActivity, "${dayOfMonth}.${month+1}", Toast.LENGTH_SHORT).show()
                }
                datePickerDialog.show()
            }

            timePickerDialog.setOnClickListener {
                val timePickerDialog = TimePickerDialog(
                    this@MainActivity,
                    object : TimePickerDialog.OnTimeSetListener {
                        override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                            Toast.makeText(this@MainActivity, "$hourOfDay:$minute", Toast.LENGTH_SHORT).show()
                        }

                    },
                    24,
                    60,
                    true
                )
                timePickerDialog.updateTime(17, 6)
                timePickerDialog.show()
            }


            //BottomSheet Dialog
            bottomsheetDialog.setOnClickListener {
                val bottomSheetDialog = BottomSheetDialog(this@MainActivity)
                bottomSheetDialog.setContentView(layoutInflater.inflate(R.layout.bottom_sheet_dialog, null, false))
                bottomSheetDialog.show()
            }

            //Snackbar
            snackbar.setOnClickListener {
                val snackbar=Snackbar.make(it, "It is snackbar", Snackbar.LENGTH_LONG)

                snackbar.setAction("Click", object : View.OnClickListener{
                    override fun onClick(v: View?) {
                        Toast.makeText(this@MainActivity, "Snackbar", Toast.LENGTH_SHORT).show()
                    }
                })
                snackbar.show()
            }
        }

    }
}