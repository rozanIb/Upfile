package com.example.addfiles


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import java.util.ArrayList


class Charts : AppCompatActivity() {
   lateinit var barChart:BarChart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charts)
        barChart = findViewById(R.id.chart)
        setBarChartValues()
    }
private fun setBarChartValues(){
    val xvalue=ArrayList<String>()
    xvalue.add("Saturday")
    xvalue.add("Sun")
    xvalue.add("Monday")
    xvalue.add("Tuesday")
    xvalue.add("Wednesday")
    xvalue.add("Thursday")
    xvalue.add("Friday")
    // Create data entries
 val yaxis= arrayOf<Float>(2.0f,6f,7.8f,3.4f,8f,5f,4.7f)
    val yaxis1= arrayOf<Float>(1.0f,5f,6.8f,2.4f,9f,5f,2.7f)
    val yaxis2= arrayOf<Float>(6.0f,4f,5.8f,1.4f,10f,5f,1.7f)
        val entries = ArrayList<BarEntry>()
    val entries1 = ArrayList<BarEntry>()
    val entries2 = ArrayList<BarEntry>()
    for (i in 0..yaxis.size-1) {
        entries.add(BarEntry(yaxis[i], i))
    }
    for (i in 0..yaxis1.size-1) {
        entries1.add(BarEntry(yaxis1[i], i))
    }
    for (i in 0..yaxis2.size-1){
        entries2.add(BarEntry(yaxis2[i],i))
        }
       val barDataSet = BarDataSet(entries, "First")
   val barDataSet1 = BarDataSet(entries1, "Second")
   val barDataSet2 = BarDataSet(entries2, "Third")
    barDataSet.color=resources.getColor(R.color.purple_500)
    barDataSet1.color=resources.getColor(R.color.green)
    barDataSet2.color=resources.getColor(R.color.orange)
    val finalData=ArrayList<BarDataSet>()
    finalData.add(barDataSet)
    finalData.add(barDataSet1)
    finalData.add(barDataSet2)
       val data = BarData(xvalue,finalData)
        barChart.data = data
        barChart.setBackgroundColor(resources.getColor(R.color.white))
        barChart.animateXY(3000,3000)
}}