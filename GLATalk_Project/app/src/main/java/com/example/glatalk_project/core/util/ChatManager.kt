package com.example.glatalk_project.core.util

import android.util.Log
import com.example.glatalk_project.core.data.ChatData
import com.example.glatalk_project.core.data.InitData
import com.google.gson.Gson
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import java.lang.Exception

class ChatManager{
    private lateinit var mSocket: Socket
    private lateinit var sender_id: String
    private lateinit var receiver_id: String
    private lateinit var room_id: String
    private val gson: Gson = Gson()
    private var onChatListener: OnChatListener? = null

    fun setChatListener(listener: OnChatListener) {
        onChatListener = listener
    }

    fun init(sender: String, receiver: String, room_id: String) {
        try {
            mSocket = IO.socket("http://211.215.19.77:3333/")
            Log.d("success",/* mSocket.id()*/"Success")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("fail", "Failed to connect")
        }

        this.sender_id = sender
        this.receiver_id = receiver
        this.room_id = room_id

        mSocket.on(Socket.EVENT_CONNECT, onConnect)
        mSocket.on(Socket.EVENT_DISCONNECT, onDisconnect)
        mSocket.on(Socket.EVENT_CONNECT_ERROR, onConnectError)
        mSocket.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectError)
        mSocket.on("updateChat", onUpdateChat)
        mSocket.on("userCountRcv", onUserCountRcv)
        mSocket.connect()
    }

    fun userCount(){
        val data = InitData(sender_id, receiver_id, room_id)
        val jsonData = gson.toJson(data)
        mSocket.emit("userCount", jsonData)
    }

    fun sendMessage(chatData: ChatData?) {
        val jsonData = gson.toJson(chatData)
        mSocket.emit("newMessage", jsonData)
    }

    fun release() {
        val data = InitData(sender_id, receiver_id, room_id)
        val jsonData = gson.toJson(data)
        mSocket.emit("unsubscribe", jsonData)
        mSocket.disconnect()
        mSocket.off(Socket.EVENT_CONNECT, onConnect)
        mSocket.off(Socket.EVENT_DISCONNECT, onDisconnect)
        mSocket.off(Socket.EVENT_CONNECT_ERROR, onConnectError)
        mSocket.off(Socket.EVENT_CONNECT_TIMEOUT, onConnectError)
        mSocket.off("updateChat", onUpdateChat)
        mSocket.off("userCountRcv", onUserCountRcv)

        onChatListener = null
    }

    private var onUpdateChat = Emitter.Listener {
        val arriveData = gson.fromJson(it[0].toString(), ChatData::class.java)
        onChatListener?.onReceive(arriveData)
    }

    private var onConnect = Emitter.Listener {
        Log.d(TAG, "onConnect")
        val data = InitData(sender_id, receiver_id, room_id)
        val jsonData = gson.toJson(data)
        mSocket.emit("subscribe", jsonData)
        onChatListener?.onConnected()
    }

    private var onDisconnect = Emitter.Listener {
        Log.d(TAG, "onDisconnect")
        onChatListener?.onDisconnect()
    }

    private var onConnectError = Emitter.Listener {
        Log.d(TAG, "onConnectError")
        onChatListener?.onConnectError()
    }

    private var onUserCountRcv = Emitter.Listener {
        val userCount = gson.fromJson(it[0].toString(), String::class.java)
        Log.d("ChatManager", userCount)
    }

    companion object {
        val TAG = "ChatManager"
        private var _instance: ChatManager? = null

        val instance: ChatManager
            get() {
                if (_instance == null) {
                    _instance = ChatManager()
                }
                return _instance!!
            }
    }

    interface OnChatListener {
        fun onConnected()
        fun onReceive(chat: ChatData)
        fun onConnectError()
        fun onDisconnect()
    }
}
