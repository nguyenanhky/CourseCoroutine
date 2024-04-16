package kynv1.fsoft.coursecoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kynv1.fsoft.coursecoroutine.databinding.ActivityMainBinding
import kynv1.fsoft.coursecoroutine.untis.Logger

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var count = 0;
    private var isShowResult = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            binding.processBar.visibility = View.VISIBLE
            requestDataWithSuspend()
        }

        binding.btnIncrease.setOnClickListener {
            if (!isShowResult) {
                count++
                binding.txtData.text = "count: $count"
            }
        }
    }

    private fun requestDataWithSuspend() {
        val mainScope = CoroutineScope(Dispatchers.Main)
        mainScope.launch {
            Logger.lod("main scope with thread : ${Thread.currentThread().name}")
            withContext(Dispatchers.IO){
                Logger.lod("main withContext with thread : ${Thread.currentThread().name}")
                delay(6000L)
                Logger.lod("nguyen anh ky ")
            }
            Logger.lod("after  withContext thread : ${Thread.currentThread().name}")
            isShowResult = true
            binding.processBar.visibility = View.GONE
            binding.txtData.text = "data from server"
        }

//        val ioScope = CoroutineScope(Dispatchers.IO)
//        ioScope.launch {
//            Logger.lod("IO scope with thread : ${Thread.currentThread().name}")
//        }
//
//        val defaultScope = CoroutineScope(Dispatchers.Default)
//        defaultScope.launch{
//            Logger.lod("Default scope with thread : ${Thread.currentThread().name}")
//        }
    }
}