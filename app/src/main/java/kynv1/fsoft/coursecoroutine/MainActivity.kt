package kynv1.fsoft.coursecoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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
            //requestData()
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

    private fun requestData() {
        Logger.lod("requestData: ${Thread.currentThread().name}")
        Thread.sleep(2000L)
        binding.processBar.visibility = View.GONE
        isShowResult = true
        binding.txtData.text = "data from server"
    }

    private fun requestDataWithSuspend() {
        val scope = CoroutineScope(Job() + Dispatchers.Main)
        scope.launch {
            Logger.lod("requestDataWithSuspend: ${Thread.currentThread().name}")
            val startTime = System.currentTimeMillis()
            delay(1000L)
            val executeTime = System.currentTimeMillis()-startTime
            Logger.lod("executeTime : $executeTime")
            binding.processBar.visibility = View.GONE
            isShowResult = true
            binding.txtData.text = "data from server"
        }
    }
}