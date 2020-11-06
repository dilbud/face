package main;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ui.FaceRecogntionUI;
import ui.ProgressBar;

import javax.swing.*;
import java.util.concurrent.Executors;

@Slf4j
public class RunFaceRecognition {

    private static final Logger log = LogManager.getLogger(RunFaceRecognition.class);

    public static void main(String[] args) throws Exception {

        JFrame mainFrame = new JFrame();
        ProgressBar progressBar = new ProgressBar(mainFrame, true);
        progressBar.showProgressBar("Loading model, this make take few moments");
        FaceRecogntionUI faceRecogntionUi = new FaceRecogntionUI();
        Executors.newCachedThreadPool().submit(() -> {
            try {
                faceRecogntionUi.initUI();
            } catch (Exception e) {
                log.error("Failed to start", e);
                throw new RuntimeException(e);
            } finally {
                progressBar.setVisible(false);
                mainFrame.dispose();
            }
        });

    }

}
