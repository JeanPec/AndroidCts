package com.shindra;

public class tramCardview {
    private int _tramLinePicture;
    private int _illustrationPicture;

    public tramCardview(int tramLinePicture, int illustrationPicture)
    {
        _tramLinePicture = tramLinePicture;
        _illustrationPicture = illustrationPicture;
    }

    public int getTramLinePicture()
    {
        return _tramLinePicture;
    }

    public int getIllustrationPicture()
    {
        return _illustrationPicture;
    }
}
