package frc.robot;

import edu.wpi.first.math.controller.PIDController;

/** This class provides a place for Calibrations: arbitrary but tuned values, like PID values. */
public final class Cal {
  public static final double PLACEHOLDER_DOUBLE = 0.0;
  public static final int PLACEHOLDER_INT = 0;
  public static final float PLACEHOLDER_FLOAT = 0;

  public static final class SwerveModule {
    /** Input meters/second, output [-1,1] */
    public static final double DRIVING_P = 4.0,
        DRIVING_I = 0.0,
        DRIVING_D = 0.05,
        DRIVING_FF = 1 / Constants.SwerveModule.DRIVE_WHEEL_FREE_SPEED_METERS_PER_SECOND;

    public static final double DRIVING_MIN_OUTPUT = -1;
    public static final double DRIVING_MAX_OUTPUT = 1;

    /** Input radians, output [-1,1] */
    public static final double TURNING_P = PLACEHOLDER_DOUBLE,
        TURNING_I = PLACEHOLDER_DOUBLE,
        TURNING_D = PLACEHOLDER_DOUBLE,
        TURNING_FF = 0;

    public static final double TURNING_MIN_OUTPUT = -1;
    public static final double TURNING_MAX_OUTPUT = 1;
  }

  public static final class SwerveSubsystem {

    /** For the purposes of trajectory constraints */
    public static final double MAX_LINEAR_SPEED_METERS_PER_SEC = 4.0,
        MAX_LINEAR_ACCELERATION_METERS_PER_SEC_SQ = 3.0;

    /**
     * Angular offset of the modules relative to the zeroing fixture in radians. COmmon to all
     * modules
     */
    public static final double SWERVE_COMMON_ANGULAR_OFFSET_RAD = PLACEHOLDER_DOUBLE;

    /**
     * Angular offsets of the modules relative to the chassis in radians. The modules form an O when
     * fixtured, so they are iteratively 90 deg from each other.
     */
    public static final double
        FRONT_LEFT_CHASSIS_ANGULAR_OFFSET_RAD =
            SWERVE_COMMON_ANGULAR_OFFSET_RAD - (3.0 * Math.PI / 4.0),
        FRONT_RIGHT_CHASSIS_ANGULAR_OFFSET_RAD = SWERVE_COMMON_ANGULAR_OFFSET_RAD - (Math.PI / 4.0),
        BACK_LEFT_CHASSIS_ANGULAR_OFFSET_RAD =
            SWERVE_COMMON_ANGULAR_OFFSET_RAD + (3.0 * Math.PI / 4.0),
        BACK_RIGHT_CHASSIS_ANGULAR_OFFSET_RAD = SWERVE_COMMON_ANGULAR_OFFSET_RAD + (Math.PI / 4.0);

    /**
     * Controller on module speed for rotating to target, input degrees [-180,180], output [0,1].
     */
    public static final PIDController ROTATE_TO_TARGET_PID_CONTROLLER =
        new PIDController(0.030, 0, 0.000); // From 2022

    /** Feed forward for rotating to target, gets added to or subtracted from PID controller. */
    public static final double ROTATE_TO_TARGET_FF = 0.1; // From 2022

    /** Auton path finding controllers */
    public static final PIDController PATH_X_CONTROLLER = new PIDController(0.100506, 0.0, 0.0),
        PATH_Y_CONTROLLER = new PIDController(0.1, 0.0, 0.0);

    /** High profile constraints = pure P controller */
    public static final PIDController PATH_THETA_CONTROLLER = new PIDController(9.0, 0.0, 0.80);
  }

  public static final class Intake {
    public static final double INTAKING_POWER = 1.0;
    public static final double EJECTION_POWER = -1.0;

    /** Input deg/s, output [0,1] */
    public static final double DEPLOY_MOTOR_P = Cal.PLACEHOLDER_DOUBLE,
        DEPLOY_MOTOR_I = Cal.PLACEHOLDER_DOUBLE,
        DEPLOY_MOTOR_D = Cal.PLACEHOLDER_DOUBLE;

    /** Intake positions in degrees */
    public static final double STARTING_POSITION_DEGREES = 38.0, DEPLOYED_POSITION_DEGREES = 180.0;

    /** Past this position, the intake is free to clamp */
    public static final double CLAMP_POSITION_THRESHOLD_DEGREES = 110.0;

    /** Absolute encoder position when the arm is at 0 degrees */
    public static final double ABSOLUTE_ENCODER_START_POS_DEG = Cal.PLACEHOLDER_DOUBLE;

    /** Voltage required to hold the intake in the horizontal position */
    // Stall torque: 3.36 Nm * 75 = 252 Nm
    // Max torque: 6 kg * 9.81 (N/kg) * 0.26m = 15.3 Nm
    // Ratio: 0.73 V
    public static final double ARBITRARY_FEED_FORWARD_VOLTS = 0.7;

    /** Parameters for intake SmartMotion */
    public static final double
        // angular accel = Torque / Inertia. 3.36 Nm * 75 / (6 * 0.26^2) kg-m^2 * (360 deg / 2pi
        // rad) = 35600 deg/s^2
        DEPLOY_MAX_ACCELERATION_DEG_PER_SECOND_SQUARED = 1600.0,
        // 5880 rpm / (60 sec/min) * (360 deg/rev) / 75 = 470.4
        DEPLOY_MAX_VELOCITY_DEG_PER_SECOND = 400.0,
        DEPLOY_MIN_OUTPUT_VELOCITY_DEG_PER_SECOND = 3.0,
        DEPLOY_ALLOWED_CLOSED_LOOP_ERROR_DEG = 3.0;

    /** Margin for having achieved the desired intake position (in degrees) */
    public static final double POSITION_MARGIN_DEGREES = 3.0;

    /** Sets the min and max positions that the intake deploy motor will be allowed to reach. */
    public static final float
        INTAKE_DEPLOY_MOTOR_POSITIVE_LIMIT_DEGREES = (float) DEPLOYED_POSITION_DEGREES,
        INTAKE_DEPLOY_MOTOR_NEGATIVE_LIMIT_DEGREES = (float) STARTING_POSITION_DEGREES;

    /** Time for the intake to unclamp, in seconds */
    public static final double UNCLAMP_TIME_SECONDS = 0.2;
  }

  public static final class Lift {
    /** Input in/s, output [0,1] */
    public static final double ELEVATOR_P = Cal.PLACEHOLDER_DOUBLE,
        ELEVATOR_I = Cal.PLACEHOLDER_DOUBLE,
        ELEVATOR_D = Cal.PLACEHOLDER_DOUBLE;

    /** Input deg/s, output [0,1] */
    public static final double ARM_P = Cal.PLACEHOLDER_DOUBLE,
        ARM_I = Cal.PLACEHOLDER_DOUBLE,
        ARM_D = Cal.PLACEHOLDER_DOUBLE;

    /** Absolute encoder position when the arm is at 0 degrees */
    public static final double ARM_ABSOLUTE_ENCODER_ZERO_POS_DEG = Cal.PLACEHOLDER_DOUBLE;

    /**
     * Position reading from the absolute encoders when the elevator is at the start (zero) position
     */
    public static final double ELEVATOR_ABS_ENCODER_POS_AT_START_INCHES = PLACEHOLDER_DOUBLE;

    /** Voltage required to hold the arm in the horizontal position */
    // Stall torque: 3.36 Nm * 75 = 252 Nm
    // Max torque: 3.4 kg * 9.81 (N/kg) * 0.68m = 22.68
    // Ratio: 1.08 V
    public static final double ARBITRARY_ARM_FEED_FORWARD_VOLTS = 1.0;

    /** Voltage required to hold the elevator */
    // Stall force: 2 * 1.08 Nm * 14.11 / (0.5625 / 39.37) m = 2133 N
    // Max force: 6.4 kg * 9.81 N/kg = 63 N
    // Ratio: 0.32 V
    public static final double ARBITRARY_ELEVATOR_FEED_FORWARD_VOLTS = 0.3;

    /** Parameters for arm SmartMotion */
    public static final double
        // Angular accel = Torque / Inertia.
        // Stall torque: 3.36 Nm * 75 = 252 Nm
        // Inertia: 3.4 kg * 0.68^2 m^2 = 1.57 kg-m^2
        // Accel = 160.5 rad/s^2 = 9200 deg/s^2
        ARM_MAX_ACCELERATION_DEG_PER_SECOND_SQUARED = 1600.0,
        // 5880 rpm / (60 sec/min) / 75 * (360 deg / rev) = 470
        ARM_MAX_VELOCITY_DEG_PER_SECOND = 400.0,
        ARM_MIN_OUTPUT_VELOCITY_DEG_PER_SECOND = 3.0,
        ARM_ALLOWED_CLOSED_LOOP_ERROR_DEG = 1.0;

    /** Parameters for elevator SmartMotion */
    public static final double
        // accel = Force / mass.
        // Stall force: 2 * 1.08 Nm * 14.11 / (0.5625 / 39.37) m = 2133 N
        // mass: 6.4 kg
        // Accel = 333 m/s^2
        ELEVATOR_MAX_ACCELERATION_IN_PER_SECOND_SQUARED = 160.0,
        // 11710 rpm / (60 sec/min) * / 14.11 * (pi * 1.125 in) = 48.9 in/s
        ELEVATOR_MAX_VELOCITY_IN_PER_SECOND = 40.0,
        ELEVATOR_MIN_OUTPUT_VELOCITY_IN_PER_SECOND = 0.5,
        ELEVATOR_ALLOWED_CLOSED_LOOP_ERROR_IN = 0.25;

    public static final double ELEVATOR_LOW_POSITION_INCHES = 0.0,
        ELEVATOR_HIGH_POSITION_INCHES = 20.83;

    /** Sets the min and max positions that the elevator and arm motors will be allowed to reach */
    public static final float ELEVATOR_POSITIVE_LIMIT_INCHES = 20.83f,
        ELEVATOR_NEGATIVE_LIMIT_INCHES = 0.0f,
        ARM_POSITIVE_LIMIT_DEGREES = 273.0f,
        ARM_NEGATIVE_LIMIT_DEGREES = 74.0f;

    /** Threshold for when the lift is out of the zone where the intake moves. */
    public static final double ARM_INTAKE_ZONE_THRESHOLD_DEGREES = 114;

    /**
     * Margin for when we consider the lift has reached a position. This is logical (for considering
     * where the lift can go) but not functional (does not stop arm control or something when
     * reached). We apply broader margins for the Starting position as the lift transits through
     * this position.
     */
    public static final double ELEVATOR_MARGIN_INCHES = 0.5,
        ARM_MARGIN_DEGREES = 2.0,
        ELEVATOR_START_MARGIN_INCHES = 1.0,
        ARM_START_MARGIN_DEGREES = 8.0;

    /** Zone where the grabber must be closed, in degrees. Bottom is closer to intake. */
    public static final double GRABBER_CLOSED_ZONE_BOTTOM_DEGREES = PLACEHOLDER_DOUBLE,
        GRABBER_CLOSED_ZONE_TOP_DEGREES = PLACEHOLDER_DOUBLE;

    /** Time between when the grabber opens or closes and the intake unclamps, in seconds */
    public static final double GRABBER_CLOSE_TIME_SECONDS = 0.2, GRABBER_OPEN_TIME_SECONDS = 0.2;

    /** The time in seconds for the grabber to open in OuttakeSequence */
    public static final double OUTTAKE_GRABBER_WAIT_TIME_SECONDS = 0.2;

    /**
     * The time in seconds it takes for it to be safe to return to the starting position, from the
     * scoring high position
     */
    public static final double SAFE_TO_RETURN_TO_START_SECONDS = 0.5;
  }

  public static final class AutoBalance {
    /** Max speed to go on charge station in meters per second */
    public static final double MAX_CHARGE_STATION_CLIMB_SPEED_MPS = 0.5;

    /** Max speed to go on charge station in [-1,1] */
    public static final double MAX_CHARGE_STATION_CLIMB_NORM_SPEED =
        MAX_CHARGE_STATION_CLIMB_SPEED_MPS / Constants.SwerveDrive.MAX_SPEED_METERS_PER_SECOND;

    /**
     * Conversion factor between the robot pitch (in degrees) to a velocity (in [-1,1]) for
     * balancing on the charge station
     */
    public static final double CHARGE_STATION_PITCH_DEGREES_TO_NORM_VELOCITY =
        -1 * Constants.MAX_PITCH_DEGREES / MAX_CHARGE_STATION_CLIMB_NORM_SPEED;

    /**
     * Maximum speed to deadband at (that is to say, it won't auto-balance if the velocity is <=
     * 0.05, for a velocity of [-1,1])
     */
    public static final double CHARGE_STATION_DEADBAND_NORM_VELOCITY = 0.05;
  }

  public static final int SPARK_INIT_RETRY_ATTEMPTS = 5;
}
