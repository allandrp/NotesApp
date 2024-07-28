package com.alland.aplikasinotephincon.form

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alland.aplikasinotephincon.R
import com.alland.aplikasinotephincon.ViewModelFactory
import com.alland.aplikasinotephincon.databinding.ActivityNoteFormBinding
import com.alland.aplikasinotephincon.main.MainActivity
import com.alland.aplikasinotephincon.room.NoteEntity

class NoteFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteFormBinding
    private var noteData: NoteEntity? = null
    private val viewModel: NoteFormViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNoteFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val type = intent.getStringExtra(MainActivity.ACTION_TYPE)
        noteData = intent.getParcelableExtra("note")

        if (noteData != null) {
            binding.etTitleForm.setText(noteData!!.title)
            binding.etContentForm.setText(noteData!!.content)
        }

        when (type) {
            MainActivity.NOTE_ADD -> {
                binding.btnDeleteForm.visibility = View.GONE
            }
        }

        binding.btnSaveForm.setOnClickListener {
            when (type) {
                MainActivity.NOTE_ADD -> {
                    val title = binding.etTitleForm.text.toString()
                    val content = binding.etContentForm.text.toString()
                    val createdDate = System.currentTimeMillis()

                    val newNote = NoteEntity(
                        title = title,
                        createdDate = createdDate,
                        lastUpdated = createdDate,
                        content = content
                    )

                    viewModel.insertNote(newNote)
                }

                MainActivity.NOTE_EDIT -> {}
            }
        }

        toolbarSetup()
    }

    fun toolbarSetup() {
        setSupportActionBar(binding.toolbarForm)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);
        supportActionBar?.setDisplayShowTitleEnabled(false);
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}