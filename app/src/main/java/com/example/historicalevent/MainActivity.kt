package com.example.historicalevent

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity() : AppCompatActivity(), Parcelable {


    enum class HistoricalEvent(val age: Int, val description: String) {
        PERSON_1(19, "Arthur Miller: was an American playwright. His most popular plays that he wrote are All My Sons, Death Of A Salesman, The Crucible and A View From The Bridge"),
        PERSON_2(14, "Howard Zinn: A American playwright, philosopher, socialist and WWII veteran. He was chair of the history and social sciences department at Spelman College, and a political science professor at Boston University."),
        PERSON_3(13, "Anne Francis: was an American actress. Known for her ground-breaking roles in the science-fiction film Forbidden Planet and the television action-drama series Honey West."),
        PERSON_4(25, "Brion James: American actor. He portrayed Leon Kowalski in Blade Runner "),
        PERSON_5(26, "Frank Sinatra: American singer and actor. Sinatra is among the world's best-selling music artists with an estimated 150 million record sales. "),
        PERSON_6(27, "Melvin Calvin: American biochemist known for discovery of Calvin cycle"),
        PERSON_7(28, "Bill Monroe: American mandolinist"),
        PERSON_8(29, "Dean Martin: American comedian and singer.  One of the most popular entertainers of the mid-20th century, he was nicknamed The King of Cool."),
        PERSON_9(30, "Ben Enwonwu: Nigerian artist"),
        PERSON_10(31, "Jan Gies: Dutch resistance member that helped Anne Frank"),
        PERSON_11(32, "Grace Hopper: American Mathematician"),
        PERSON_12(33, "Freddie Mercury: British singer and songwriter. He was the lead vocalist and pianist of the rock band Queen. Regarded as one of the greatest singers in the history of rock music "),
        PERSON_13(34, "Ken Hill: American actor. He was an actor, known for Cobra, Hill Street Blues and Mathnet."),
        PERSON_14(11, "Michael Jackson: American singer-songwriter. Known as the King of Pop, he is regarded as one of the most significant figures of the 20th century"),
        PERSON_15(
            16,
            "Christopher Reeve:  American actor, film director, author, and activist, best known for playing the title character in the film Superman"
        ),
        PERSON_16(14, "James Brown: American musician. He is referred to by various nicknames, among them the Hardest-Working Man in Show Business"),
        PERSON_17(23, "George Harrison: Guitarist for the Beetles"),
        PERSON_18(17, "Anna Nicole Smith: American model and actress"),
        PERSON_19(21, "Johnny Cash: American country singer. Known for music that contains themes of sorrow, moral tribulation, and redemption, especially songs from the later stages of his career."),
        PERSON_20(20, "Ray Charles: singer-songwriter and pianist. He was often referred to by contemporaries as The Genius."),
        PERSON_21(10, "Bernie Mac: American actor. Born and raised on Chicago's South Side, Mac gained popularity as a stand-up comedian. "),
        PERSON_22(24, "Walter Matthau: American actor and comedian. He won the Academy Award for Best Supporting Actor for his performance in the Billy Wilder film The Fortune Cookie"),
    }

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainActivity> {
        override fun createFromParcel(parcel: Parcel): MainActivity {
            return MainActivity(parcel)
        }

        override fun newArray(size: Int): Array<MainActivity?> {
            return arrayOfNulls(size)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtAge = findViewById<EditText>(R.id.edtAge)
        val btnResult = findViewById<Button>(R.id.DisplayResultBtn)
        val btnClrResult = findViewById<Button>(R.id.ClearResultBtn)
        val edtResult = findViewById<TextView>(R.id.result1)

        btnResult.setOnClickListener {
            val ageText = edtAge.text.toString()

            if (ageText.isNotEmpty()) {
                val age = ageText.toIntOrNull()

                age?.let { personAge ->
                    if (personAge in 0..150) { // Adjust the range of valid ages as needed
                        val events = when {
                            HistoricalEvent.values().any { it.age == personAge } -> {
                                val event = HistoricalEvent.values().find { it.age == personAge }
                                listOf("At the age of $personAge: ${event?.description ?: "event"}")
                            }

                            HistoricalEvent.values().any { it.age == personAge + 1 } -> {
                                val event =
                                    HistoricalEvent.values().find { it.age == personAge + 1 }
                                listOf("At the age of $personAge, you were one year before the historical event of ${event?.description ?: "event"}")
                            }

                            HistoricalEvent.values().any { it.age == personAge - 1 } -> {
                                val event =
                                    HistoricalEvent.values().find { it.age == personAge - 1 }
                                listOf("At the age of $personAge, you were one year after the historical event of ${event?.description ?: "event"}")
                            }

                            else -> listOf("No historical events found for the age of $personAge.")
                        }
                        edtResult.text = events.joinToString("\n")
                    } else {
                        edtResult.text = "Please enter an age between 0 and 150."
                    }
                }
            } else {
                edtResult.text = "Please enter your age."
            }
        }

        btnClrResult.setOnClickListener {
            edtAge.text.clear()
            edtResult.text = ""
        }
    }
}