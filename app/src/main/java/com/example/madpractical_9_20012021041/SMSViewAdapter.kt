package com.example.madpractical_9_20012021041

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.madpractical_9_20012021041.databinding.SmsItemViewBinding

class SMSViewAdapter(context: Context, private val array:ArrayList<SMSView>):
    ArrayAdapter<SMSView>(context,array.size,array) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //val currentItemView:View = LayoutInflater.from(context).inflate(R.layout.sms_item_view, parent, false)
        // get the position of the view from the ArrayAdapter
        val currentSms: SMSView? = getItem(position)
        val binding = SmsItemViewBinding.inflate(LayoutInflater.from(context))
        binding.textViewPhoneNo.text = currentSms!!.PhoneNo
        binding.textViewMessage.text = currentSms.Message
        return binding.root
        //return super.getView(position, convertView, parent)
    }
}