package com.example.testingrobolectric

import android.R
import android.content.Intent
import android.os.Build
import android.os.Parcel
import android.view.View
import android.widget.Button
import android.widget.TextView
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.android.synthetic.main.activity_main.*
import org.hamcrest.MatcherAssert
import org.hamcrest.core.Is
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config


// Static imports for assertion methods

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(RobolectricTestRunner::class)
class MainActivityTest {
    private var activity: MainActivity? = null
    //private lateinit var activity: MainActivity


    // @Before => JUnit 4 annotation that specifies this method should run before each test is run
    // Useful to do setup for objects that are needed in the test
    @Before
    fun setup() {
        // Convenience method to run MainActivity through the Activity Lifecycle methods:
        // onCreate(...) => onStart() => onPostCreate(...) => onResume()
        activity = Robolectric.setupActivity(MainActivity::class.java)
    }

    // @Test => JUnit 4 annotation specifying this is a test to be run
    // The test simply checks that our TextView exists and has the text "Hello world!"
    @Test
    fun validateTextViewContent() {
        //val tvHelloWorld = activity!!.tvHelloWorld
        val employeeID = activity!!.employeeID
        val name = activity!!.name
        val address = activity!!.address
        val age = activity!!.age
        val dob = activity!!.dob
        val country = activity!!.country
        val language = activity!!.language
        /*Assert.assertNotNull("TextView could not be found", tvHelloWorld)
        Assert.assertTrue(
            "TextView contains incorrect text",
            "Hello world!" == tvHelloWorld.text.toString()
        )*/
        Assert.assertNotNull("TextView could not be found", employeeID)
        Assert.assertTrue(
            "TextView contains incorrect text",
            "001" == employeeID.text.toString()
        )
        Assert.assertNotNull("TextView could not be found", name)
        Assert.assertTrue(
            "TextView contains incorrect text",
            "ABC" == name.text.toString()
        )
        Assert.assertNotNull("TextView could not be found", address)
        Assert.assertTrue(
            "TextView contains incorrect text",
            "1500 Street" == address.text.toString()
        )
        Assert.assertNotNull("TextView could not be found", age)
        Assert.assertTrue(
            "TextView contains incorrect text",
            "25" == age.text.toString()
        )
        Assert.assertNotNull("TextView could not be found", dob)
        Assert.assertTrue(
            "TextView contains incorrect text",
            "02/29" == dob.text.toString()
        )
        Assert.assertNotNull("TextView could not be found", country)
        Assert.assertTrue(
            "TextView contains incorrect text",
            "USA" == country.text.toString()
        )
        Assert.assertNotNull("TextView could not be found", language)
        Assert.assertTrue(
            "TextView contains incorrect text",
            "English" == language.text.toString()
        )
        /*Assert.assertNotNull("TextView could not be found", profile)
        Assert.assertTrue(
            "TextView contains incorrect text",
            "Haha" == profile.text.toString()
        )*/
    }

    @Test
    fun secondActivityStartedOnClick() {
        activity!!.btnLaunchNextActivity.performClick()

        // The intent we expect to be launched when a user clicks on the button
        val expectedIntent = Intent(activity, SecondActivity::class.java)

        // An Android "Activity" doesn't expose a way to find out about activities it launches
        // Robolectric's "ShadowActivity" keeps track of all launched activities and exposes this information
        // through the "getNextStartedActivity" method.
        val shadowActivity = Shadows.shadowOf(activity)
        val actualIntent = shadowActivity.nextStartedActivity

        // Determine if two intents are the same for the purposes of intent resolution (filtering).
        // That is, if their action, data, type, class, and categories are the same. This does
        // not compare any extra data included in the intents
        assertTrue(actualIntent.filterEquals(expectedIntent))
    }

    @Test
    fun changeAge(){
        val textViewAge = activity!!.age
        val buttonChangeAge = activity!!.button_changeAge
        if (textViewAge.text.toString() == "25"){
            buttonChangeAge.performClick()
            assertTrue("TextView contains incorrect text", "50" == textViewAge.text.toString())
        }
        if (textViewAge.text.toString() != "25") {
            buttonChangeAge.performClick()
            assertTrue("TextView contains incorrect text", "25" == textViewAge.text.toString())
        }

    }
    /*
    @Test
    @Config(qualifiers = "es")
    fun localizedSpanishHelloWorld() {
        val tvHelloWorld = activity!!.tvHelloWorld
        val employeeID = activity!!.employeeID
        val name = activity!!.name
        val address = activity!!.address
        val age = activity!!.age
        val dob = activity!!.dob
        val country = activity!!.country
        val language = activity!!.language
        assertEquals(tvHelloWorld.text.toString(), "Hola Mundo!")
    }

    @Test
    @Config(qualifiers = "fr")
    fun localizedFrenchHelloWorld() {
        val tvHelloWorld = activity!!.tvHelloWorld
        val employeeID = activity!!.employeeID
        val name = activity!!.name
        val address = activity!!.address
        val age = activity!!.age
        val dob = activity!!.dob
        val country = activity!!.country
        val language = activity!!.language
        assertEquals(tvHelloWorld.text.toString(), "Bonjour le monde!")
    }*/
}