package com.example.madpractical_9_20012021041

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

class SMSViewAdapter (context: Context, private val array:ArrayList<SMSView>):
        ArrayAdapter<SMSView>(context,array.size,array){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
       val currentSms:SMSView?=getItem(position)
        val binding=SmsItemViewBinding.inflate(layoutInfluter)
    }
        }
