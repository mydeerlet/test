package com.mydeerlet.myapplication.my_view_model

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import java.util.*


class MyViewModel(application: Application, handle: SavedStateHandle) : AndroidViewModel(application) {





    var handle: SavedStateHandle? = null
    val KEY_HIGH_SCORE = "key_high_score"
    val KEY_LEFT_NUMBER = "key_left_number"
    val KEY_RIGHT_NUMBER = "key_right_number"
    val KEY_OPERATOR = "key_operator"
    val KEY_ANSWER = "key_answer"
    val SAVE_SHP_DATA_MAME = "save_shp_data_name"
    val KEY_CURRENT_SCORE = "key_current_score"

    var win_flag = false

    init {
        if (!handle.contains(KEY_HIGH_SCORE)) {
            val shp = application.getSharedPreferences(SAVE_SHP_DATA_MAME, Context.MODE_PRIVATE)
            handle.set(KEY_HIGH_SCORE, shp.getInt(KEY_HIGH_SCORE, 0))
            handle.set(KEY_LEFT_NUMBER, 0)
            handle.set(KEY_RIGHT_NUMBER, 0)
            handle.set(KEY_OPERATOR, "+")
            handle.set(KEY_ANSWER, 0)
            handle.set(KEY_CURRENT_SCORE, 0)
            this.handle = handle
        }
    }

    fun getLeftNumber(): MutableLiveData<Int> {
        return handle!!.getLiveData(KEY_LEFT_NUMBER)
    }

    fun getRightNumber(): MutableLiveData<Int> {
        return handle!!.getLiveData(KEY_RIGHT_NUMBER)
    }

    fun getOperator(): MutableLiveData<String> {
        return handle!!.getLiveData(KEY_OPERATOR)
    }

    fun getHightScor(): MutableLiveData<Int> {
        return handle!!.getLiveData(KEY_HIGH_SCORE)
    }

    fun getCurrentScore(): MutableLiveData<Int> {
        return handle!!.getLiveData(KEY_CURRENT_SCORE)
    }

    fun getAnswer(): MutableLiveData<Int> {
        return handle!!.getLiveData(KEY_ANSWER)
    }

    fun generator(){
        val LEVL = 20
        val random = Random()

        val x = random.nextInt(LEVL)+1
        val y = random.nextInt(LEVL)+1

        val tmp = random.nextInt(100)
        if (tmp%2 ==0){
          getOperator().value = "+"
            if (x>y){
                getAnswer().value = x
                getLeftNumber().value = y
                getRightNumber().value = x-y
            }else{
                getAnswer().value = y
                getLeftNumber().value = x
                getRightNumber().value = y-x
            }

        }else{
            getOperator().value = "-"
            if (x>y){
                getAnswer().value= x-y
                getLeftNumber().value = x
                getRightNumber().value = y
            }else{
                getAnswer().value= y-x
                getLeftNumber().value = y
                getRightNumber().value = x
            }

        }
    }


    fun save(){
        val shp = getApplication<Application>().getSharedPreferences(SAVE_SHP_DATA_MAME,Context.MODE_PRIVATE)
        val edit = shp.edit()
        edit.putInt(KEY_HIGH_SCORE, getHightScor().value!!)
        edit.apply()
    }

    fun answerCorrect(){
        getCurrentScore().value = getCurrentScore().value!! +1
        if (getCurrentScore().value!!>getHightScor().value!!){
            getHightScor().value = getCurrentScore().value
            win_flag =true
        }
        generator()
    }

}