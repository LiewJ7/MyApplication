package com.example.myapplication


import android.app.Activity

import android.os.Bundle

import android.text.TextUtils
import android.util.Log

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.webdb.MySingleton

import kotlinx.android.synthetic.main.activity_create_moment.*
import org.json.JSONObject


class CreateMoment : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_moment)

        btnPost.setOnClickListener {

            savePost();
        }
    }
    private fun savePost(){
        if(TextUtils.isEmpty(editTextTopic.text)){
            editTextTopic.setError(getString(R.string.error_value_required))
            return
        }
        if(TextUtils.isEmpty(editTextContent.text)){
            editTextContent.setError(getString(R.string.error_value_required))
            return
        }
        val topic = editTextTopic.text.toString()
        val content = editTextContent.text.toString()

        val url = getString(R.string.url_server) + getString(R.string.url_topic_create) + "?topicTitle=" +topic  +
                "&topicContent=" + content


        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                // Process the JSON
                try{
                    if(response != null){
                        val strResponse = response.toString()
                        val jsonResponse  = JSONObject(strResponse)
                        val success: String = jsonResponse.get("success").toString()

                        if(success.equals("1")){
                            Toast.makeText(applicationContext, "Moment Posted", Toast.LENGTH_LONG).show()
                            //Add record to user list

                        }else{
                            Toast.makeText(applicationContext, "Record not saved", Toast.LENGTH_LONG).show()
                        }

                    }
                }catch (e:Exception){
                    Log.d("Main", "Response: %s".format(e.message.toString()))

                }
            },
            Response.ErrorListener { error ->
                Log.d("Main", "Response: %s".format(error.message.toString()))

            }
        )

        //Volley request policy, only one time request
        jsonObjectRequest.retryPolicy = DefaultRetryPolicy(
            DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
            0, //no retry
            1f
        )

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)


        setResult(Activity.RESULT_OK, intent)

        finish()

    }
    companion object{
        const val EXTRA_TOPIC = "com.example.webdb.TOPIC"
        const val EXTRA_CONTENT = "com.example.webdb.CONTENT"
    }
}
