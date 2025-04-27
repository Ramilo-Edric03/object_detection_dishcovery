package com.example.object_detection_dishcovery

import android.util.Log
import java.util.concurrent.CopyOnWriteArrayList

/**
 * Class to store and manage detected object data
 */
class DetectionStorage {
    // Using CopyOnWriteArrayList for thread safety when accessing from multiple threads
    private val detections = CopyOnWriteArrayList<DetectionData>()
    private val TAG = "DetectionStorage"

    /**
     * Add new detection data
     */
    fun addDetection(detection: DetectionData) {
        detections.add(detection)
        Log.d(TAG, "Added detection: $detection. Total detections: ${detections.size}")
    }

    /**
     * Add multiple detection data
    //     */
//    fun addDetections(newDetections: List<DetectionData>) {
//        detections.addAll(newDetections)
//    }

    /**
     * Get all stored detections
     */
    fun getAllDetections(): List<DetectionData> {
        Log.d(TAG, "Returning all detections. Count: ${detections.size}")
        return detections.toList()
    }

    /**
     * Clear all stored detections
     */
    fun clearDetections() {
        Log.d(TAG, "Clearing all detections.")
        detections.clear()
    }

    /**
     * Get detection count
     */
    fun getCount(): Int {
        Log.d(TAG, "Getting detection count: ${detections.size}")
        return detections.size
    }
}

/**
 * Data class for storing detection information
 */
data class DetectionData(
    val boundingBox: BoundingBox,
    val timestamp: Long,
    val frameWidth: Int,
    val frameHeight: Int
)