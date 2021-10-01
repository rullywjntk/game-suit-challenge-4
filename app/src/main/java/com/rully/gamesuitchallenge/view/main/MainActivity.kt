package com.rully.gamesuitchallenge.view.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.rully.gamesuitchallenge.R
import com.rully.gamesuitchallenge.databinding.ActivityMainBinding
import com.rully.gamesuitchallenge.view.game.ComActivity
import com.rully.gamesuitchallenge.view.game.PlayerActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val vsPlayer = binding.ivMain1
        val vsCpu = binding.ivMain2

        vsPlayer.setOnClickListener(this)
        vsCpu.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id) {
            R.id.ivMain1 -> {
                val playerIntent = Intent(this, PlayerActivity::class.java)
                startActivity(playerIntent)
            }
            R.id.ivMain2 -> {
                val comIntent = Intent(this, ComActivity::class.java)
                startActivity(comIntent)
            }
        }
    }
}