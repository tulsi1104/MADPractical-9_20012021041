package com.example.madpractical_9_20012021041

import android.Manifest
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony
import android.widget.ListView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.madpractical_9_20012021041.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var lv:ListView
    private lateinit var al:ArrayList<SMSView>
    private lateinit var smsreceive:SMSBroadCastReciever
    private val SMS_PERMISSION_CODE=100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lv=binding.listview
        al= ArrayList()
        if(checkRequestPermission()){
            loadSMSInbox()
        }
        smsreceive= SMSBroadCastReciever()
        registerReceiver(smsreceive, IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION))

    }
    inner class ListenerImplement:SMSBroadCastReciever.Listener{

    }
    private fun requestSMSPermission() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_SMS)){

        }
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_SMS,Manifest.permission.SEND_SMS,Manifest.permission.RECEIVE_SMS),SMS_PERMISSION_CODE)
    }
    private val isSMSReadPermission:Boolean
    get() = ContextCompat.checkSelfPermission(this,Manifest.permission.READ_SMS)==PackageManager.PERMISSION_DENIED

    private val isSMSWritePermission:Boolean
        get() = ContextCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS)==PackageManager.PERMISSION_GRANTED

    private fun checkRequestPermission(): Boolean {
        return if (!isSMSReadPermission || !isSMSWritePermission) {
            requestSMSPermission()
            false
        } else true
    }

    private fun loadSMSInbox() {
        if (!checkRequestPermission()) return
        val uriSMS = Uri.parse("content://sms/inbox")
        val c = contentResolver.query(uriSMS, null, null, null, null)
        al.clear()
        while (c!!.moveToNext()) {
            al.add(SMSView(c.getString(2),c.getString(12)))
        }
        lv.adapter = SMSViewAdapter(this,al)
    }

    override fun onDestroy() {
        unregisterReceiver(smsreceive)
        super.onDestroy()
    }
}