package diceGame;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import java.util.Random;

public class Dice3D extends Group{
    private static final int SIZE = 100; // Cube size
    private Box dice;
    private Rotate rotateX, rotateY;
    private Random random = new Random();

    //Possible Dice Final Rotations (Aligning One Face Towards the Camera)
    private final int[][] possibleRotations = {
        {0, 0},    // 1 Facing Up
        {0, 180},  // 6 Facing Up
        {0, 90},   // 2 Facing Up
        {0, -90},  // 5 Facing Up
        {90, 0},   // 3 Facing Up
        {-90, 0}   // 4 Facing Up
    };

    public Dice3D() {
        dice = new Box(SIZE, SIZE, SIZE); // 🎲 Create a 3D cube
        //PhongMaterial material = new PhongMaterial(Color.BLUE); // Change color here
        //Create the cube using six textured planes
        PhongMaterial testMaterial = new PhongMaterial();
        testMaterial.setDiffuseMap(new Image(getClass().getResourceAsStream("/resources/img/cube_side.png")));
        dice.setMaterial(testMaterial); // Apply test image to the entire cube

        //Rotation transforms
        rotateX = new Rotate(0, Rotate.X_AXIS);
        rotateY = new Rotate(0, Rotate.Y_AXIS);
        dice.getTransforms().addAll(rotateX, rotateY);
    }
    

    public SubScene createScene() {
        Group root = new Group(dice);
        SubScene scene = new SubScene(root, 300, 300, true, SceneAntialiasing.BALANCED); //reference window, width, height
        scene.setFill(Color.TRANSPARENT); // Make background transparent
        
        //Camera Positioning to center the Box
        PerspectiveCamera camera = new PerspectiveCamera();
        camera.setTranslateZ(330);  // Move camera back
        camera.setTranslateX(-150);     // Center X
        camera.setTranslateY(-150);     // Center Y
        scene.setCamera(camera);
        
        return scene;
    }

    public void roll3DDice() {
    	
        // Define the rotation speed inside the method
        double rotationSpeed = 2.5 + random.nextDouble() * 1.5; // Randomized speed between 2.5 - 5

        // Select a random final rotation
        int[] finalRotation = possibleRotations[random.nextInt(possibleRotations.length)];
        int targetX = finalRotation[0];
        int targetY = finalRotation[1];

        // Generate rolling angles
        int initialRollX = -(360 + random.nextInt(360 * 2)); // Forward roll rotation  
        int initialRollY = -(360 + random.nextInt(360)); // Random side roll

        // Adjust launch movement based on rotation speed
        double launchSpeed = rotationSpeed * 3;  // Scale movement based on speed

        // Movement calculations
        double moveZ = Math.cos(Math.toRadians(initialRollX)) * launchSpeed; // Forward movement
        double moveY = launchSpeed * 3;  // **Positive for proper upward motion**
        double moveX = (random.nextDouble() - 0.5) * launchSpeed * 0.7; // Small random sideways movement

        double moveAway = 500; // Extra movement in Z-direction (deeper into the scene)

        // Animate dice rolling and movement
        Timeline timeline = new Timeline(
            // Phase 1: Initial roll and launch upwards, moving AWAY from the camera
            new KeyFrame(Duration.seconds(0.5),
                new KeyValue(rotateX.angleProperty(), initialRollX, Interpolator.EASE_OUT),
                new KeyValue(rotateY.angleProperty(), initialRollY, Interpolator.EASE_OUT),
                new KeyValue(dice.translateXProperty(), moveX, Interpolator.EASE_OUT), 
                new KeyValue(dice.translateZProperty(), moveZ + moveAway, Interpolator.EASE_OUT), 
                new KeyValue(dice.translateYProperty(), moveY, Interpolator.EASE_OUT) // Moves UPWARDS
            ),

            // Phase 2: Stop rotation mid-air, holding final face
            new KeyFrame(Duration.seconds(1),
                new KeyValue(rotateX.angleProperty(), targetX, Interpolator.EASE_OUT),
                new KeyValue(rotateY.angleProperty(), targetY, Interpolator.EASE_OUT),
                new KeyValue(dice.translateXProperty(), moveX * 0.5, Interpolator.EASE_OUT), 
                new KeyValue(dice.translateZProperty(), moveZ * 1.2 + moveAway * 0.5, Interpolator.EASE_OUT),
                new KeyValue(dice.translateYProperty(), moveY * 1.2, Interpolator.EASE_OUT) // Peak height
            ),

            // Phase 3: Smooth return to original position WITHOUT rotation
            new KeyFrame(Duration.seconds(1.5),
                new KeyValue(dice.translateXProperty(), 0, Interpolator.EASE_OUT), // Return to center
                new KeyValue(dice.translateZProperty(), 0, Interpolator.EASE_OUT),
                new KeyValue(dice.translateYProperty(), 0, Interpolator.EASE_OUT), // Lands on table
                new KeyValue(rotateX.angleProperty(), targetX, Interpolator.DISCRETE), // No further rotation
                new KeyValue(rotateY.angleProperty(), targetY, Interpolator.DISCRETE) 
            )
        );

        timeline.play();
    }









    
}
