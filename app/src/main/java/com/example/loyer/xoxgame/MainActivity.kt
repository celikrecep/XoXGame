package com.example.loyer.xoxgame


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var allButtons = ArrayList<Button>()
    var activePlayer = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun clickCell(view : View){

        var selectedButton = view as Button
        allButtons.add(selectedButton)
        var cellId = 0
        when(selectedButton.id){

            R.id.btn1 -> cellId = 1
            R.id.btn2 -> cellId = 2
            R.id.btn3 -> cellId = 3
            R.id.btn4 -> cellId = 4
            R.id.btn5 -> cellId = 5
            R.id.btn6 -> cellId = 6
            R.id.btn7 -> cellId = 7
            R.id.btn8 -> cellId = 8
            R.id.btn9 -> cellId = 9

        }
        Log.d("ButtonClicked" , cellId.toString())
        playGame(cellId,selectedButton)
    }

  private  fun playGame(cellId : Int, selectedButton: Button){

        if(activePlayer == 1){
            selectedButton.text = "X"
            selectedButton.setBackgroundColor(resources.getColor(R.color.player1))
            player1.add(cellId)
            checkWin(player1)
            activePlayer = 2
            selectedButton.isClickable = false
        }else{

            selectedButton.text = "O"
            selectedButton.setBackgroundColor(resources.getColor(R.color.player2))
            player2.add(cellId)
            checkWin(player2)
            activePlayer = 1
            selectedButton.isClickable = false
        }

    }
        //checkwin method
    private fun checkWin(list : ArrayList<Int>){

        var winner = -1
        if (list.containsAll(listOf(1,2,3)) || list.containsAll(listOf(1,5,9))
                || list.containsAll(listOf(1,4,7)) || list.containsAll(listOf(2,5,8))
                || list.containsAll(listOf(3,6,9)) || list.containsAll(listOf(4,5,6))
                || list.containsAll(listOf(7,8,9)) || list.containsAll(listOf(3,5,7))){
            winner = activePlayer
            alert(winner.toString())

        }
    }
    //alert mesajı
 private  fun alert(s : String){
        val alert = AlertDialog.Builder(this)

        alert.setTitle("Player $s kazandı!")
        alert.setMessage(R.string.alert_message)
        alert.setCancelable(false)
        alert.setIcon(android.R.drawable.ic_dialog_alert)
        alert.setPositiveButton(R.string.alert_positive){
            dialogInterface, i -> // refreshing method been called
            refresh()
        }
        alert.setNegativeButton(R.string.alert_negative){
            dialogInterface, i ->
            System.exit(1)

        }
        alert.show()

    }
        //refreshing the clicked buttons
    fun refresh(){

    for (x in allButtons){
        x.setBackgroundResource(R.drawable.button)
        x.text = ""
    }

    }


}


