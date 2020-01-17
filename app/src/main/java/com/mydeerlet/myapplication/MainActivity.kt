package com.mydeerlet.myapplication

import android.annotation.SuppressLint
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {


    lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        controller = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupActionBarWithNavController(this, controller)
    }


    override fun onSupportNavigateUp(): Boolean {

        if (controller.currentDestination!!.id == R.id.quesitionFragment) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(getString(R.string.quit_dialog_title))
            builder.setPositiveButton(getString(R.string.dialog_positive_message), object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    controller.navigateUp() }
            })
            builder.setNegativeButton(getString(R.string.dialog_negative_message),
                object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                    }
                })
            val dialog = builder.create()
            dialog.show()
        }else{
            if (controller.currentDestination!!.id == R.id.loseFragment|| controller.currentDestination!!.id == R.id.winFragment){
                controller.navigate(R.id.titleFragment)
            }else{
                finish()
            }
        }
        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
//        super.onBackPressed()
        onSupportNavigateUp()
    }
}
