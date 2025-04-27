package com.example.object_detection_dishcovery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetectionAdapter(private val detections: List<DetectionData>) :
    RecyclerView.Adapter<DetectionAdapter.DetectionViewHolder>() {

    class DetectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val objectNameText: TextView = itemView.findViewById(R.id.objectNameText)
        val confidenceText: TextView = itemView.findViewById(R.id.confidenceText)
        val positionText: TextView = itemView.findViewById(R.id.positionText)
        val timestampText: TextView = itemView.findViewById(R.id.timestampText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetectionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_detection, parent, false)
        return DetectionViewHolder(view)
    }

    override fun getItemCount(): Int = detections.size

    override fun onBindViewHolder(holder: DetectionViewHolder, position: Int) {
        val detection = detections[position]
        val box = detection.boundingBox

        holder.objectNameText.text = box.clsName
        holder.confidenceText.text = "Confidence: ${String.format("%.2f", box.cnf)}"
        holder.positionText.text = "Position: (${String.format("%.2f", box.cx)}, ${String.format("%.2f", box.cy)})"

        val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        val timeString = dateFormat.format(Date(detection.timestamp))
        holder.timestampText.text = "Time: $timeString"
    }
}