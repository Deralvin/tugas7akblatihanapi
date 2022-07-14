package co.id.diulinken
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import co.id.diulinken.adapter.CafeAdapter
import co.id.diulinken.api.ApiEndpoint
import co.id.diulinken.api.RetrofitBuilder
import co.id.diulinken.model.Datum
import co.id.diulinken.model.ResponseCafe

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {

    var cafes: List<Datum> = listOf()
    lateinit var cafeAdapter: CafeAdapter
    private lateinit var editText: AppCompatEditText
    private lateinit var noSearchResultsFoundText: TextView
    private lateinit var cafeRecyclerView: RecyclerView
    private lateinit var clearQueryImageView: ImageView
    private lateinit var voiceSearchImageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.search_edit_text)
        noSearchResultsFoundText = findViewById(R.id.no_search_results_found_text)
        clearQueryImageView = findViewById(R.id.clear_search_query)
        voiceSearchImageView = findViewById(R.id.voice_search_query)
        cafeAdapter = CafeAdapter(cafes)
        cafeRecyclerView = findViewById(R.id.list_cafe)
        cafeRecyclerView.adapter = cafeAdapter


        val api = RetrofitBuilder.getInstance().create(ApiEndpoint::class.java)
        val response: Call<ResponseCafe> = api.getPeople()

        response.enqueue(object : Callback<ResponseCafe> {

            override fun onFailure(call: Call<ResponseCafe>, t: Throwable) {
                Log.e("API-CALL", "ERR", t)
            }

            override fun onResponse(call: Call<ResponseCafe>, response: Response<ResponseCafe>) {
                Log.d("API-CALL", response.body().toString())
                cafes = response.body()?.data!!
                cafeAdapter = CafeAdapter(cafes)
                cafeRecyclerView.adapter = cafeAdapter
            }

        })

        editText.doOnTextChanged { text, _, _, _ ->
            val query = text.toString().toLowerCase(Locale.getDefault())
            filterWithQuery(query)
            toggleImageView(query)
        }

        voiceSearchImageView.setOnClickListener {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                putExtra(
                    RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
                )
            }
            startActivityForResult(intent, SPEECH_REQUEST_CODE)
        }
        clearQueryImageView.setOnClickListener {
            editText.setText("")
            attachAdapter(cafes)
        }
    }

    private fun attachAdapter(list: List<Datum>) {
        cafeAdapter = CafeAdapter(list)
        cafeRecyclerView.adapter = cafeAdapter
    }

    private fun filterWithQuery(query: String) {
        if (query.isNotEmpty()) {
            val filteredList: List<Datum> = onFilterChanged(query)
            attachAdapter(filteredList)
            toggleRecyclerView(filteredList)
        } else if (query.isEmpty()) {
            attachAdapter(cafes)
        }
    }

    private fun onFilterChanged(filterQuery: String): List<Datum> {
        val filteredList = ArrayList<Datum>()
        for (currentSport in cafes) {
            if (currentSport.nama.toLowerCase(Locale.getDefault()).contains(filterQuery)) {
                filteredList.add(currentSport)
            }
        }
        return filteredList
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val spokenText: String? =
                data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).let { results ->
                    results?.get(0)
                }
            // Do something with spokenText
            editText.setText(spokenText)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun toggleRecyclerView(sportsList: List<Datum>) {
        if (sportsList.isEmpty()) {
            cafeRecyclerView.visibility = View.INVISIBLE
            noSearchResultsFoundText.visibility = View.VISIBLE
        } else {
            cafeRecyclerView.visibility = View.VISIBLE
            noSearchResultsFoundText.visibility = View.INVISIBLE
        }
    }

    private fun toggleImageView(query: String) {
        if (query.isNotEmpty()) {
            clearQueryImageView.visibility = View.VISIBLE
            voiceSearchImageView.visibility = View.INVISIBLE
        } else if (query.isEmpty()) {
            clearQueryImageView.visibility = View.INVISIBLE
            voiceSearchImageView.visibility = View.VISIBLE
        }
    }

//    override fun onSportSelected(sports: Sports?) {
//        val intent = Intent(applicationContext, DetailActivity::class.java)
//        intent.putExtra("DETAIL_SPORTS_DATA", sports)
//        startActivity(intent)
//    }

    companion object {
        const val SPEECH_REQUEST_CODE = 0
    }
}