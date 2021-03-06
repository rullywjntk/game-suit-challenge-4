package com.rully.gamesuitchallenge.view.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
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

        showPlayerName()
        vsPlayer.setOnClickListener(this)
        vsCpu.setOnClickListener(this)

    }

    private fun showPlayerName() {
        val name = intent.getStringExtra(EXTRA_NAME)
        binding.tvPlayer.text = name
        binding.tvPlayer1.text = name
        val snackBar = Snackbar.make(binding.root, """Selamat Datang $name""", Snackbar.LENGTH_LONG)
            snackBar.setAction(getString(R.string.close)){
                snackBar.dismiss()
            }
        snackBar.show()
    }

    override fun onClick(p0: View?) {
        when(p0?.id) {
            R.id.ivMain1 -> {
                val name = intent.getStringExtra(EXTRA_NAME)
                val playerIntent = Intent(this, PlayerActivity::class.java)
                playerIntent.putExtra(PlayerActivity.EXTRA_NAME, name)
                startActivity(playerIntent)
            }
            R.id.ivMain2 -> {
                val name = intent.getStringExtra(EXTRA_NAME)
                val comIntent = Intent(this, ComActivity::class.java)
                comIntent.putExtra(ComActivity.EXTRA_NAME, name)
                startActivity(comIntent)
            }
        }
    }

    companion object {
        const val EXTRA_NAME = "extra_name"
    }
}