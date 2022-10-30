package com.miu.walmartlogin

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var userArr = arrayListOf(
        User("Feruzbek","Tukhtavulov","mustafogli@gmail.com","111")
        ,User("Shak","Marifkhonov","shak@gmail.com","222")
        ,User("Timur","Salimov","timur@gmail.com","333")
        ,User("Fazik","Abdullaev","fazik@gmail.com","444")
        ,User("Mez","Ikramov","mez@gmail.com","555"))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //
        btnSignIn.setOnClickListener{
            var email = etEmail.text.toString()
            var password = etPassword.text.toString()
            for(u in userArr){
                if(u.username == email && u.password == password){
                    val intent = Intent(this,ShoppingCategory::class.java)
                    intent.putExtra("username",email)
                    startActivity(intent)
                    break
                }else{
                    Toast.makeText(this,"Unable to login. Please try again!",Toast.LENGTH_LONG).show()
                }
            }

        }

        var resultContracts = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                result ->
            if(result.resultCode == Activity.RESULT_OK)
                Log.i("Reg Act:",result.data?.data.toString())
            else
                Log.i("Reg Act:","Failed to get Result")
        }

        btnCreate.setOnClickListener{
            val intent = Intent(this,CreateAccount::class.java)
            resultContracts.launch(intent)
        }
    }

}