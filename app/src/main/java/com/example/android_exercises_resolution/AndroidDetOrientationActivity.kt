package com.example.android_exercises_resolution

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class AndroidDetOrientationActivity : AppCompatActivity(), SensorEventListener {

    private val sensorManager by lazy {
        getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    private val sensorAccelerometer by lazy {
        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    private val sensorMagneticField by lazy {
        sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
    }

    private var valuesAccelerometer: FloatArray = FloatArray(3)
    private var valuesMagneticField: FloatArray = FloatArray(3)

    private var matrixR: FloatArray = FloatArray(9)
    private var matrixI: FloatArray = FloatArray(9)
    private var matrixValues: FloatArray = FloatArray(3)

    /**
     * Called when the activity is first created.
     */
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        sensorManager.registerListener(
            this,
            sensorAccelerometer,
            SensorManager.SENSOR_DELAY_NORMAL
        )
        sensorManager.registerListener(
            this,
            sensorMagneticField,
            SensorManager.SENSOR_DELAY_NORMAL
        )
        super.onResume()
    }

    override fun onPause() {
        sensorManager.unregisterListener(
            this,
            sensorAccelerometer
        )
        sensorManager.unregisterListener(
            this,
            sensorMagneticField
        )
        super.onPause()
    }

    override fun onAccuracyChanged(arg0: Sensor, arg1: Int) {}

    override fun onSensorChanged(event: SensorEvent) {
        when (event.sensor.type) {
            Sensor.TYPE_ACCELEROMETER -> for (i in 0..2) {
                valuesAccelerometer[i] = event.values[i]
            }
            Sensor.TYPE_MAGNETIC_FIELD -> for (i in 0..2) {
                valuesMagneticField[i] = event.values[i]
            }
        }

        val success = SensorManager.getRotationMatrix(
            matrixR,
            matrixI,
            valuesAccelerometer,
            valuesMagneticField
        )

        if (success) {
            SensorManager.getOrientation(matrixR, matrixValues)

            val azimuth = Math.toDegrees(matrixValues[0].toDouble())
            val pitch = Math.toDegrees(matrixValues[1].toDouble())
            val roll = Math.toDegrees(matrixValues[2].toDouble())

            readingAzimuth.text = "Azimuth: " + azimuth.toString()
            readingPitch.text = "Pitch: " + pitch.toString()
            readingRoll.text = "Roll: " + roll.toString()
            myCompass.update(matrixValues[0])
        }
    }
}