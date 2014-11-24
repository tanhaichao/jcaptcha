package com.octo.captcha.component.image.deformation;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.util.List;
import java.util.ArrayList;

/**
 * @author mag
 * @Date 5 mars 2008
 */
public class ImageDeformationByBufferedImageOp implements ImageDeformation{

    private List<BufferedImageOp> ImageOperations = new ArrayList<BufferedImageOp>();

    public void setImageOperations(List<BufferedImageOp> imageOperations) {
        ImageOperations = imageOperations;
    }

    public ImageDeformationByBufferedImageOp(List<BufferedImageOp> imageOperations) {
        ImageOperations = imageOperations;
    }

    public ImageDeformationByBufferedImageOp(BufferedImageOp imageOperation) {
        ImageOperations.add(imageOperation);
    }

    public BufferedImage deformImage(BufferedImage image) {
        for(BufferedImageOp operation:ImageOperations){
            image = operation.filter(image, null);
        }
        return image;
    }
}
