package com.pedro.rtplibrary.view;

import android.graphics.PointF;
import android.graphics.SurfaceTexture;
import android.view.Surface;
import com.pedro.encoder.input.gl.render.filters.BaseFilterRender;
import com.pedro.encoder.utils.gl.GifStreamObject;
import com.pedro.encoder.utils.gl.ImageStreamObject;
import com.pedro.encoder.utils.gl.TextStreamObject;
import com.pedro.encoder.utils.gl.TranslateTo;

public interface GlInterface {

  /**
   * Initialize necessary classes.
   */
  void init();

  /**
   * Set video encoder size use to Opengl
   * @param width video encoder width in px
   * @param height video encoder height in px
   */
  void setEncoderSize(int width, int height);

  /**
   * Get SurfaceTexture generated by Opengl. This should be called after start render.
   * @return surface texture generated by Opengl.
   */
  SurfaceTexture getSurfaceTexture();

  /**
   * Get Surface generated by Opengl. This should be called after start render.
   * @return surface texture generated by Opengl.
   */
  Surface getSurface();

  /**
   * Set surface from MediaCodec class to Opengl.
   * This surface is used to copy pixels from Opengl surface to this surface and encode this pixels.
   * @param surface surface created from MediaCodec.
   */
  void addMediaCodecSurface(Surface surface);

  /**
   * Remove surface generated from MediaCodec.
   */
  void removeMediaCodecSurface();

  /**
   * Capture an Image from Opengl.
   *
   * @param takePhotoCallback callback where you will get your image like a bitmap.
   */
  void takePhoto(TakePhotoCallback takePhotoCallback);

  /**
   * Set a filter to stream.
   * You can select any filter from {@link com.pedro.encoder.input.gl.render.filters} or create
   * your own filter if you extends from {@link com.pedro.encoder.input.gl.render.filters.BaseFilterRender}
   *
   * @param baseFilterRender filter to set. You can modify parameters to filter after set it to stream.
   */
  void setFilter(BaseFilterRender baseFilterRender);

  /**
   * Set a gif to stream. You can resize your gif before set it to stream.
   * Your GifStreamObject object will be released after set to stream.
   */
  void setGif(GifStreamObject gifStreamObject);

  /**
   * Set an image to stream. You can resize your image before set it to stream.
   * Your ImageStreamObject object will be released after set to stream.
   */
  void setImage(ImageStreamObject imageStreamObject);

  /**
   * Set a text to stream. You can resize text before set it to stream.
   * Your TextStreamObject object will be released after set to stream.
   */
  void setText(TextStreamObject textStreamObject);

  /**
   * Clear GifStreamObject, ImageStreamObject or TextStreamObject from stream.
   */
  void clear();

  /**
   * Set alpha to GifStreamObject, ImageStreamObject or TextStreamObject from stream.
   *
   * @param alpha values between 0.0f to 1.0f
   */
  void setStreamObjectAlpha(float alpha);

  /**
   * Set size to GifStreamObject, ImageStreamObject or TextStreamObject from stream.
   * Values is a stream percent so 0.0f is 0% (no X or Y) and 100.0f is 100% (full X or Y).
   *
   * @param sizeX values between 0.0f to 100.0f
   * @param sizeY values between 0.0f to 100.0f
   */
  void setStreamObjectSize(float sizeX, float sizeY);

  /**
   * Set position to GifStreamObject, ImageStreamObject or TextStreamObject from stream.
   * Values is a stream percent so 0.0f is 0% (no X or Y) and 100.0f is 100% (full X or Y).
   *
   * @param x values between 0.0f to 100.0f
   * @param x values between 0.0f to 100.0f
   */
  void setStreamObjectPosition(float x, float y);

  /**
   * Set position to GifStreamObject, ImageStreamObject or TextStreamObject from stream.
   *
   * @param translateTo enum to set position in stream with default values.
   */
  void setStreamObjectPosition(TranslateTo translateTo);

  /**
   * Enable or disable Anti aliasing (This method use FXAA).
   *
   * @param AAEnabled true is AA enabled, false is AA disabled. False by default.
   */
  void enableAA(boolean AAEnabled);

  /**
   * INTERNAL METHOD.
   *
   * Used to notify Opengl if you change to front camera for image rotation.
   *
   * @param frontCamera true if you are using front camera, false if you are using back camera.
   */
  void setCameraFace(boolean frontCamera);

  /**
   * Get Anti alias is enabled.
   * @return true is enabled, false is disabled.
   */
  boolean isAAEnabled();

  /**
   * INTERNAL METHOD.
   *
   * Set wait time for next frame to OpenglView, LightOpenglView or OffScreenGlThread.
   *
   * @param waitTime time in milliseconds
   */
  void setWaitTime(int waitTime);

  /**
   * Get size values of GifStreamObject, ImageStreamObject or TextStreamObject.
   *
   * @return Point with X and Y scale values in stream. Values in stream percent size.
   */
  PointF getScale();

  /**
   * Get position values of GifStreamObject, ImageStreamObject or TextStreamObject.
   *
   * @return Point with X and Y position values in stream. Values in stream percent size.
   */
  PointF getPosition();

  /**
   * INTERNAL METHOD.
   *
   * Start Opengl rendering.
   *
   * @param isCamera2Landscape true if you start stream with device in landscape and with camera2 to fix orientation error.
   */
  void start(boolean isCamera2Landscape);

  /**
   * INTERNAL METHOD.
   *
   * Stop Opengl rendering.
   */
  void stop();
}