#pragma once
#include <opencv2/core.hpp>

using namespace cv;

void flipImage(Mat Src);

void applyBlur(Mat src, float sigma);