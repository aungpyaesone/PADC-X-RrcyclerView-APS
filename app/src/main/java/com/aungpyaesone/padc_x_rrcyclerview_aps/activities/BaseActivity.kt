package com.aungpyaesone.padc_x_rrcyclerview_aps.activities

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.aungpyaesone.padc_x_rrcyclerview_aps.R
import com.google.android.material.snackbar.Snackbar
import com.yarolegovich.lovelydialog.LovelyInfoDialog

abstract class BaseActivity : AppCompatActivity() {

    fun showSnackBar(message:String){
        Snackbar.make(window.decorView,message,Snackbar.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // showSnackBar("Hello")
     //   customDialog()
    }

    fun showAlertDialog(message:String){
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Title")
        alertDialog.setMessage(message)
        alertDialog.setPositiveButton("Ok"){dialog, which ->
            dialog.dismiss()
        }
        alertDialog.setNegativeButton("Cancel"){dialog, which ->
            dialog.dismiss()
        }.show()
    }

    fun customDialog(){
        val dialog = LovelyInfoDialog(this)
        dialog.setTopColorRes(R.color.colorAccent)
        dialog.setIcon(R.drawable.ic_launcher_background)
       // dialog.setNotShowAgainOptionEnabled(1)
       // dialog.setNotShowAgainOptionChecked(false)
        dialog.setTitle("Title")
        dialog.setMessage("ကၽြန္ေတာ္တို႔ Capital Taiyo Life Insurance တြင္ အာမခံထားရွိေပးသည့္အတြက္ ေက်းဇူးတင္ရွိပါသည္။ လက္ရွိအခ်ိန္တြင္ လူၾကီးမင္းတို႔ ပရီမီယံေၾကးေပးသြင္း ျပီးျပီးခ်င္း App တြင္ ခ်က္ခ်င္း ၾကည့္ရႈလို႔မရႏိုင္ေသးပါ။ ရံုးပိုင္းဆုိင္ရာလုပ္ငန္းေဆာင္တာမ်ား လုပ္ေဆာင္ရမည္ျဖစ္ပါသျဖင့္ ရက္အနည္းငယ္ ေနာက္က်ျပီးမွသာလွ်င္ ၾကည့္ရႈလို႔ရႏိုင္မည္ျဖစ္ပါေၾကာင္း ၾကိဳတင္ေမတၱာရပ္ခံ ေတာင္းပန္အပ္ပါသည္။(အထူးသျဖင့္ ဘဏ္မွေပးသြင္းပါက ရက္အနည္းငယ္ပိုမိုၾကာျမင့္ႏိုင္ပါသည္။)")
        dialog.show()
    }
}