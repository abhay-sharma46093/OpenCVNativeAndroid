#include "opencv-utils.h"
#include <opencv2/imgproc.hpp>

void flipImage(Mat src){
    flip(src, src, 0);
}
void applyBlur(Mat src, float sigma){
    GaussianBlur(src, src, Size(), sigma);
}