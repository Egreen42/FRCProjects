package frc.robot.utils;

import edu.wpi.first.wpilibj.SerialPort;

public class ThriftyAbsoluteEncoder {
    private final SerialPort port;
    private final int id;

    public ThriftyAbsoluteEncoder(SerialPort.Port port, int id) {
        this.port = new SerialPort(9600, port); // Adjust baud rate as per your device specs
        this.id = id;
    }

    // Get the encoder position in radians (or appropriate units for your swerve drive)
    public double getAngle() {
        // Retrieve the angle from the encoder
        return 
