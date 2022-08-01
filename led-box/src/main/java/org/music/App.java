package org.music;
import com.diozero.api.GpioPullUpDown;
import com.diozero.devices.Button;
import com.diozero.devices.LED;
import com.diozero.util.Diozero;
import com.diozero.util.SleepUtil;
import org.tinylog.Logger;

/**
 * led box
 *
 */
public class App 
{
    public static void main( String[] args ) {
        while (true) {
            ledBox(12, 16, 21, 17, 27, 22, 10);
        }


    }
    public static void ledBox(int buttonPin, int buttonPin2, int buttonPin3, int ledPin, int ledPin2, int ledPin3, int delaySeconds) {
        try(LED led = new LED(ledPin); LED led2 = new LED(ledPin2); LED led3 = new LED(ledPin3); Button button = new Button(buttonPin, GpioPullUpDown.PULL_UP); Button button2 = new Button(buttonPin2, GpioPullUpDown.PULL_UP); Button button3 = new Button(buttonPin3, GpioPullUpDown.PULL_UP)) {
            Diozero.registerForShutdown(button, led, button2, button3, led2, led3);
            button.whenPressed(nanoTime -> led.on());
            button.whenReleased(nanoTime -> led.off());
            button2.whenPressed(nanoTime -> led2.on());
            button2.whenReleased(nanoTime -> led2.off());
            button3.whenPressed(nanoTime -> led3.on());
            button3.whenReleased(nanoTime -> led3.off());
            Logger.info("Waiting for {}s -- **Press the buttons connected to pin {}, {}, and {} **", Integer.valueOf(delaySeconds), Integer.valueOf(buttonPin), Integer.valueOf(buttonPin2), Integer.valueOf(buttonPin3));
            SleepUtil.sleepSeconds(delaySeconds);

        }
        catch (Exception ex) {
            Logger.error(ex, "Error: {}", ex);
        }
    }
}

