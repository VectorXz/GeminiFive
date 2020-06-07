/*
 * Copyright (c) 2020. Chaiyong Ragkhitwetsagul
 * All rights reserved.
 */

package edu.gemini.app.ocs.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

/***
 * Astronomical Data class which is a list of all the images taken by the Gemini observatory
 */
public class AstronomicalData {
    private ArrayList<BufferedImage> images;

    public AstronomicalData() {
        this.images = new ArrayList<>();
    }

    public AstronomicalData(BaseObservingProgram op, ArrayList<BufferedImage> images) {
        this.images = images;
    }

    public ArrayList<BufferedImage> getAstronomicalData() throws IOException {
        getAllImages();
        return this.images;
    }

    private void getAllImages() throws IOException {
        int noOfImages = randNum(6) + 4;
        ArrayList<String> imageList = getListOfImages("references" + File.separator + "images.txt");
        for (int i=0; i<noOfImages; i++) {
            String selectedImgLoc = imageList.remove(randNum(imageList.size()));
            BufferedImage img = (BufferedImage) downloadImage(selectedImgLoc);
            this.images.add(img);
        }
    }

    private Image downloadImage(String loc) throws IOException {
        Image image = null;
        URL url = new URL(loc);
        image = ImageIO.read(url);
        System.out.println("Downloading image from " + loc);
        return image;
    }

    private int randNum(int upperBound) {
        Random rand = new Random(); //instance of random class
        //generate random values from 0 to upper bound
        return rand.nextInt(upperBound);
    }

    private ArrayList<String> getListOfImages(String imageListFileLoc) throws IOException {
        ArrayList<String> imagePaths = new ArrayList<>();
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(imageListFileLoc));
        String line = reader.readLine();
        while (line != null) {
            // read next line
            line = reader.readLine();
            imagePaths.add(line);
        }
        reader.close();
        return imagePaths;
    }
}
