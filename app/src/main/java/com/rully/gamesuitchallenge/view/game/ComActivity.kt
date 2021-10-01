package com.rully.gamesuitchallenge.view.game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rully.gamesuitchallenge.databinding.ActivityComBinding

class ComActivity : AppCompatActivity() {

    private lateinit var binding: ActivityComBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}