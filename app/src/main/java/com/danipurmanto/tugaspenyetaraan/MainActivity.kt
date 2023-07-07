package com.danipurmanto.tugaspenyetaraan

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.danipurmanto.tugaspenyetaraan.databinding.ActivityMainBinding
import retrofit2.Call

class MainActivity : AppCompatActivity() {
    private val retrofitClient = RetrofitClient
    private lateinit var binding : ActivityMainBinding
    private lateinit var myProgressDialog: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
        binding.btnRefresh.setOnClickListener {
            loadData()
        }
    }

    private fun showProgressDialog(text: String) {
        myProgressDialog = Dialog(this)
        myProgressDialog.setContentView(R.layout.progress_dialog)
        myProgressDialog.findViewById<TextView>(R.id.tv_progress_text).text = text
        myProgressDialog.setCancelable(false)
        myProgressDialog.setCanceledOnTouchOutside(false)
        myProgressDialog.show()
    }
    private fun hideProgressDialog() {
        myProgressDialog.dismiss()
    }

    private fun loadData (){
        showProgressDialog("Loading...")
        retrofitClient.apiNih.getData()
            .enqueue(object : retrofit2.Callback<Responses>{
                override fun onResponse(
                    call: Call<Responses>,
                    response: retrofit2.Response<Responses>

                ) { hideProgressDialog()
                    if (response.isSuccessful){
                    val responseApi = response.body()!!
                        if (responseApi.status == "Succes"){
                            Toast.makeText(this@MainActivity, getString(R.string.succes), Toast.LENGTH_LONG).show()
                    binding.date.text=responseApi.data.date
                    binding.co2.text=responseApi.data.co2.toString()
                    binding.hum.text=responseApi.data.humidity.toString()
                    binding.tds.text=responseApi.data.tds.toString()
                    binding.ph.text=responseApi.data.ph.toString()
                    binding.wind.text=responseApi.data.wind.toString()
                }}
                else {
                    Toast.makeText(this@MainActivity, getString(R.string.something_went_wrong), Toast.LENGTH_LONG).show()
                    Log.d("statusError", "error fetching api data")
                }}

                override fun onFailure(call: Call<Responses>, t: Throwable) {
                    Log.d("responseFailure", t.message.toString())
                    Toast.makeText(this@MainActivity, t.message.toString(), Toast.LENGTH_LONG).show()
                }

            })
    }
}