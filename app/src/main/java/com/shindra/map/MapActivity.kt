package com.shindra.map

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shindra.R

/* Kotlin remember:
 *
 * Kotlin is a language that pays close attention to "NullPointerException"
 *
 * No need to put ";" at the end of an instruction except if we want multiple instructions on an
 * unique line. Each instruction will be seperate by ";".
 *
 * "var" says the value is muable
 * "val" is the equivalent of "final" in JAVA: value is immuable
 * "lateinit": value can't be null. We expect the value to be assigned later with a non-null value
 * With "?" at the end of type we allow the object to be null
 *
 *
 *
 * For instance, here I have:
 * private val TAG = "MapActivity" -> Because this value is immuable. It represents the name of
 * my class which will never change
 *
 * private lateinit var mFragment: MapLineFragment -> Because I know my fragment will be assigned
 * with a non-null value later in "onCreate" method.
 *
 * private var tramLineLetter: String? = null -> Because the function "getStringExtra" can return
 * a null value. Note: Here, I know I won't have a null value because I send the tramLineLetter.
 * So, it's possible to declare as "private lateinit var tramLineLetter: String". Then tell the
 * result of the function will be a non-null value. Add "!!" tells the compiler we're sur the value
 * won't be null. So we should have : (intent.getStringExtra("tramLineLetter"))!!
 * Be careful, in some case, add "!!" let us the opportunity to get a "NullPointerException" */

class MapActivity : AppCompatActivity() {

    // Attributes
    private lateinit var tramLineKey: String
    private lateinit var mFragment: MapLineFragment
    private lateinit var mTramLineLetter: String

    // Methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.av_map)
        tramLineKey = getString(R.string.TramLineLetterGen)

        // Retrieve intent data
        val intent = intent
        mTramLineLetter = intent?.getStringExtra(tramLineKey).orEmpty()

        // Create, pass args & call fragment
        mFragment = MapLineFragment.onInstance(mTramLineLetter, tramLineKey)

        //MapLineFragment.onInstance(mTramLineLetter , tramLineKey)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.av_map_fragmentHolder, mFragment).commit()
    }
}