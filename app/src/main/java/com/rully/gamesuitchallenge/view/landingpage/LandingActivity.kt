package com.rully.gamesuitchallenge.view.landingpage

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro2
import com.github.appintro.AppIntroCustomLayoutFragment.Companion.newInstance
import com.rully.gamesuitchallenge.R
import com.rully.gamesuitchallenge.view.main.MainActivity

class LandingActivity : AppIntro2() {

    private lateinit var etName: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addSlide(newInstance(R.layout.intro_layout_1))
        addSlide(newInstance(R.layout.intro_layout_2))
        addSlide(newInstance(R.layout.intro_layout_3))
        setProgressIndicator()
        isWizardMode = true

    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)

        etName = findViewById(R.id.etName)
        val name = etName.text.toString()
        if (name.isEmpty()) {
            etName.error = getString(R.string.fieldEmpty)
            etName.requestFocus()
        } else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}