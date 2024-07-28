package com.alland.aplikasinotephincon.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.alland.aplikasinotephincon.form.NoteFormActivity
import com.alland.aplikasinotephincon.R
import com.alland.aplikasinotephincon.ViewModelFactory
import com.alland.aplikasinotephincon.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter: MainAdapter = MainAdapter(){ note ->
        val intent = Intent(this, NoteFormActivity::class.java)
        intent.putExtra(ACTION_TYPE, NOTE_EDIT)
        intent.putExtra(NOTE_ID, note.id)
        intent.putExtra(NOTE_ITEM, note)
        startActivity(intent)
    }

    private val viewModel: MainViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.addButtonMain.setOnClickListener {
            val intent = Intent(this, NoteFormActivity::class.java)
            intent.putExtra(ACTION_TYPE, NOTE_ADD)
            startActivity(intent)
        }

        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvNoteMain.layoutManager = layoutManager
        binding.rvNoteMain.adapter = adapter

        viewModel.getAllNote().observe(this){ data ->
            if(data.isEmpty()){
                noDataSign(true)
            }else{
                noDataSign(false)
                adapter.setList(ArrayList(data))
            }
        }
    }

    fun noDataSign(isNoData: Boolean){
        if(isNoData){
            binding.ivNoDataFound.visibility = View.VISIBLE
            binding.tvNoDataMain.visibility = View.VISIBLE
            binding.rvNoteMain.visibility = View.GONE
        }else{
            binding.ivNoDataFound.visibility = View.GONE
            binding.tvNoDataMain.visibility = View.GONE
            binding.rvNoteMain.visibility = View.VISIBLE
        }
    }

    companion object{
        const val ACTION_TYPE = "action_type"
        const val NOTE_ADD = "add"
        const val NOTE_EDIT = "edit"
        const val NOTE_ID = "note_id"
        const val NOTE_ITEM = "note_item"
    }
}