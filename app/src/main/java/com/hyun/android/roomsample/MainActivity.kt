package com.hyun.android.roomsample

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.hyun.android.roomsample.database.AppDatabase
import com.hyun.android.roomsample.database.UserDao
import com.hyun.android.roomsample.database.UserEntity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    private val mJob = Job()
    private val mScope = CoroutineScope(Dispatchers.Main + mJob)
    private val userDao: UserDao by lazy { AppDatabase.getInstance(this).userDao() }
    private var userLiveData: LiveData<List<UserEntity>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUi()
        setListener()
    }

    private fun setUi() {
        userLiveData = userDao.getAllObservable()
        userLiveData?.observe(this, Observer {
            if (it.isNullOrEmpty())
                tv_guide.visibility = View.GONE
            else
                tv_guide.visibility = View.VISIBLE

            var userListAdapter = UserListAdapter {
                deleteUser(it)
                Toast.makeText(applicationContext, "삭제 완료: ${it}", Toast.LENGTH_SHORT).show()
            }
            userListAdapter.submitList(it)
            rv_name.adapter = userListAdapter
        })
    }

    private fun setListener() {
        btn_insert.setOnClickListener {
            if (et_addr.text.toString().isEmpty() || et_name.text.toString().isEmpty()) {
                Toast.makeText(applicationContext, "아이디, 주소는 필수 값입니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            insertUser()
        }
    }

    private fun insertUser() {
        mScope.launch {
            withContext(Dispatchers.IO) {
                userDao.insert(UserEntity(name = et_name.text.toString(), address = et_addr.text.toString()))
                et_addr.setText("")
                et_name.setText("")
            }
        }
    }

    private fun deleteUser(userEntity: UserEntity) {
        mScope.launch {
            withContext(Dispatchers.IO) {
                userDao.delete(userEntity)
            }
        }
    }
}
