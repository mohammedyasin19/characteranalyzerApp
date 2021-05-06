package com.example.characteranalyzer2app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class DrawView extends AppCompatImageView {
    public int X_DISPLACEMENT = 46;
    public int Y_DISPLACEMENT_VARIES = 100;
    private static  String TAG = "STORE";
    public static  int CIRCLE_WIDTH = 10;
    public static  int CIRCLE_RADIUS = 48;
    public ViewGroup.LayoutParams params;
    private Path path=new Path();
    private Paint brush=new Paint();
    private Paint brush2=new Paint();
    private Path path2=new Path();
    private ArrayList<Pixel>pixels=new ArrayList<>();
    private ArrayList<Pixel>spotted=new ArrayList<>();
    private ArrayList<Bitmap>resultBitmaps=new ArrayList<>();
    private ArrayList<Bitmap>bitmapInputs=new ArrayList<>();
    private ArrayList<String>results=new ArrayList<>();
    private ArrayList<Error> errors=new ArrayList<>();
    private Bitmap bitmap=null;
    private boolean outIndex=false;
    private String region="";
    private float xCoordinate=0;
    private float yCoordinate=0;
    private int pixel=0;
    private int redValue=0;
    private int blueValue=0;
    private int greenValue=0;
    private int xDp=0;
    private int yDp=0;
    private int startDpX =0;
    private int startDpY =0;
    private float startPixelX=0;
    private float startPixelY=0;
    private int endPixelX=0;
    private int endPixelY=0;
    private int size=0;
    private String direction="";
    private String quadrant="";
    private float downXValue;
    private float downYValue;
    private boolean bgPlaced=false;
    private String selectedLetter="A";
    private int positionY;
    private int positionX;
    private  String bgColor="";
    private int pixel2;
    private int blueValue2;
    private int redValue2;
    private int greenValue2;
    private float currentSwipeX;
    private float currentSwipeY;
    private Paint paintText;

    public ArrayList<Pixel> getSpotted() {
        return spotted;
    }

    public void setSpotted(ArrayList<Pixel> spotted) {
        this.spotted = spotted;
    }

    public ArrayList<Bitmap> getResultBitmaps() {
        return resultBitmaps;
    }

    public void setResultBitmaps(ArrayList<Bitmap> resultBitmaps) {
        this.resultBitmaps = resultBitmaps;
    }

    public ArrayList<Bitmap> getBitmapInputs() {
        return bitmapInputs;
    }

    public void setBitmapInputs(ArrayList<Bitmap> bitmapInputs) {
        this.bitmapInputs = bitmapInputs;
    }

    public ArrayList<String> getResults() {
        return results;
    }

    public void setResults(ArrayList<String> results) {
        this.results = results;
    }

    public ArrayList<Error> getErrors() {
        return errors;
    }

    public void setErrors(ArrayList<Error> errors) {
        this.errors = errors;
    }

    public static String getTAG() {
        return TAG;
    }

    public static int getCircleWidth() {
        return CIRCLE_WIDTH;
    }

    public static int getCircleRadius() {
        return CIRCLE_RADIUS;
    }

    public float getCurrentSwipeX() {
        return currentSwipeX;
    }

    public void setCurrentSwipeX(float currentSwipeX) {
        this.currentSwipeX = currentSwipeX;
    }

    public float getCurrentSwipeY() {
        return currentSwipeY;
    }

    public void setCurrentSwipeY(float currentSwipeY) {
        this.currentSwipeY = currentSwipeY;
    }

    public Paint getPaintText() {
        return paintText;
    }

    public void setPaintText(Paint paintText) {
        this.paintText = paintText;
    }

    public  int getxDisplacement() {
        return X_DISPLACEMENT;
    }

    public  int getyDisplacementVaries() {
        return Y_DISPLACEMENT_VARIES;
    }

    public Paint getBrush2() {
        return brush2;
    }

    public void setBrush2(Paint brush2) {
        this.brush2 = brush2;
    }

    public Path getPath2() {
        return path2;
    }

    public void setPath2(Path path2) {
        this.path2 = path2;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public int getPixel2() {
        return pixel2;
    }

    public void setPixel2(int pixel2) {
        this.pixel2 = pixel2;
    }

    public int getBlueValue2() {
        return blueValue2;
    }

    public void setBlueValue2(int blueValue2) {
        this.blueValue2 = blueValue2;
    }

    public int getRedValue2() {
        return redValue2;
    }

    public void setRedValue2(int redValue2) {
        this.redValue2 = redValue2;
    }

    public int getGreenValue2() {
        return greenValue2;
    }

    public void setGreenValue2(int greenValue2) {
        this.greenValue2 = greenValue2;
    }

    public int getStartDpX() {
        return startDpX;
    }

    public void setStartDpX(int startDpX) {
        this.startDpX = startDpX;
    }

    public int getStartDpY() {
        return startDpY;
    }

    public void setStartDpY(int startDpY) {
        this.startDpY = startDpY;
    }

    public int getEndPixelX() {
        return endPixelX;
    }

    public void setEndPixelX(int endPixelX) {
        this.endPixelX = endPixelX;
    }

    public int getEndPixelY() {
        return endPixelY;
    }

    public void setEndPixelY(int endPixelY) {
        this.endPixelY = endPixelY;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getQuadrant() {
        return quadrant;
    }

    public void setQuadrant(String quadrant) {
        this.quadrant = quadrant;
    }

    public float getDownXValue() {
        return downXValue;
    }

    public void setDownXValue(float downXValue) {
        this.downXValue = downXValue;
    }

    public float getDownYValue() {
        return downYValue;
    }

    public void setDownYValue(float downYValue) {
        this.downYValue = downYValue;
    }

    public int getxDp() {
        return xDp;
    }

    public void setxDp(int xDp) {
        this.xDp = xDp;
    }

    public int getyDp() {
        return yDp;
    }

    public void setyDp(int yDp) {
        this.yDp = yDp;
    }

    public ViewGroup.LayoutParams getParams() {
        return params;
    }

    public void setParams(ViewGroup.LayoutParams params) {
        this.params = params;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public Paint getBrush() {
        return brush;
    }

    public void setBrush(Paint brush) {
        this.brush = brush;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public boolean isOutIndex() {
        return outIndex;
    }

    public void setOutIndex(boolean outIndex) {
        this.outIndex = outIndex;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public float getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(float xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public float getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(float yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public int getPixel() {
        return pixel;
    }

    public void setPixel(int pixel) {
        this.pixel = pixel;
    }

    public int getRedValue() {
        return redValue;
    }

    public void setRedValue(int redValue) {
        this.redValue = redValue;
    }

    public int getBlueValue() {
        return blueValue;
    }

    public void setBlueValue(int blueValue) {
        this.blueValue = blueValue;
    }

    public int getGreenValue() {
        return greenValue;
    }

    public void setGreenValue(int greenValue) {
        this.greenValue = greenValue;
    }

    public float getStartPixelX() {
        return startPixelX;
    }

    public void setStartPixelX(float startPixelX) {
        this.startPixelX = startPixelX;
    }

    public float getStartPixelY() {
        return startPixelY;
    }

    public void setStartPixelY(float startPixelY) {
        this.startPixelY = startPixelY;
    }

    public boolean isBgPlaced() {
        return bgPlaced;
    }

    public void setBgPlaced(boolean bgPlaced) {
        this.bgPlaced = bgPlaced;
    }

    public String getSelectedLetter() {
        return selectedLetter;
    }

    public void setSelectedLetter(String selectedLetter) {
        this.selectedLetter = selectedLetter;
    }

    public ArrayList<Pixel> getPixels() {
        return pixels;
    }

    public int getPixelSize(){
        return pixels.size();
    }
    public void setPixels(ArrayList<Pixel> pixels) {
        this.pixels = pixels;
    }


    public DrawView(Context context) {
        super(context);
        brush.setAntiAlias(true);
        brush.setColor(Color.MAGENTA);
        brush.setStyle(Paint.Style.STROKE);
        brush.setStrokeJoin(Paint.Join.ROUND);
        brush.setStrokeWidth(15f);
        brush2.setAntiAlias(true);
        brush2.setColor(Color.BLUE);
        brush2.setStyle(Paint.Style.STROKE);
        brush2.setStrokeJoin(Paint.Join.ROUND);
        brush2.setStrokeWidth(15f);
        this.setDrawingCacheEnabled(true);
        this.buildDrawingCache(true);
        params=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        brush.setAntiAlias(true);
        brush.setColor(Color.MAGENTA);
        brush.setStyle(Paint.Style.STROKE);
        brush.setStrokeJoin(Paint.Join.ROUND);
        brush.setStrokeWidth(15f);

        brush2.setAntiAlias(true);
        brush2.setColor(Color.BLUE);
        brush2.setStyle (Paint.Style.STROKE);
        brush2.setStrokeJoin(Paint.Join.ROUND);
        brush2.setStrokeWidth(15f);

        this.setDrawingCacheEnabled(true);
        this.buildDrawingCache(true);
        params=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public DrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        brush.setAntiAlias(true);
        brush.setColor(Color.MAGENTA);
        brush.setStyle(Paint.Style.STROKE);
        brush.setStrokeJoin(Paint.Join.ROUND);
        brush.setStrokeWidth(15f);

        brush2.setAntiAlias(true);
        brush2.setColor(Color.BLUE);
        brush2.setStyle(Paint.Style.STROKE);
        brush2.setStrokeJoin(Paint.Join.ROUND);
        brush2.setStrokeWidth(15f);
        this.setDrawingCacheEnabled(true);
        this.buildDrawingCache(true);
        params=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float pointX=event.getX();
        float pointY=event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(pointX,pointY);
                path2.moveTo(pointX,pointY-dpToPixel(size)+dpToPixel(Y_DISPLACEMENT_VARIES)-dpToPixel(20));
                downXValue = event.getX();
                downYValue = event.getY();
                return true;

            case MotionEvent.ACTION_MOVE:
                bitmap=this.getDrawingCache();
                if((int)event.getX()>0&&(int)event.getY()>0&&(int)event.getY()<bitmap.getHeight()
                        &&(int)event.getX()<bitmap.getWidth()) {
                    if(startDpX ==0&& startDpY ==0){
                        startPixelX=event.getX();
                        startPixelY=event.getY();
                        startDpX =pxTodp((int)event.getX());
                        startDpY =pxTodp((int)event.getY());

                    }
                    endPixelX=pxTodp((int)event.getX());
                    endPixelY=pxTodp((int)event.getY());
                    xCoordinate=event.getX();
                    yCoordinate=event.getY();
                    xDp=pxTodp((int)xCoordinate);
                    yDp=pxTodp((int)yCoordinate);
                    pixel=bitmap.getPixel((int)xCoordinate,(int)yCoordinate);
                    redValue = Color.red(pixel);
                    blueValue = Color.blue(pixel);
                    greenValue = Color.green(pixel);
                    if(redValue==241&&blueValue==241&&greenValue==241){
                        region="inner_region_1";
                    }else if(redValue==242&&blueValue==242&&greenValue==242){
                        region="inner_region_2";
                    }else if(redValue==243&&blueValue==243&&greenValue==243){
                        region="inner_region_3";
                    }else if(redValue==255&&blueValue==255&&greenValue==255){
                        region="outer_region_1";
                    }else if(redValue==250&&blueValue==250&&greenValue==250){
                        region="outer_region_2";
                    }else if(redValue==00&&blueValue==00&&greenValue==244){
                        region="on_line_1";
                    }else if(redValue==00&&blueValue==00&&greenValue==248){
                        region="on_line_2";
                    }else if(redValue==00&&blueValue==00&&greenValue==252){
                        region="on_line_3";
                    }else if(redValue==00&&blueValue==00&&greenValue==255){
                        region="on_line_4";
                    }
                    else if(redValue==255&&blueValue==255&&greenValue==00){
                        region="on_trace";
                    }
                    currentSwipeX = event.getX();
                    currentSwipeY = event.getY();
                    if (Math.abs(downXValue - currentSwipeX) > Math.abs(downYValue - currentSwipeY)) {
                        if (downXValue < currentSwipeX) {
                            direction = "right";
                        }
                        if (downXValue > currentSwipeX) {
                            direction = "left";
                        }

                    } else {
                        if (downYValue < currentSwipeY) {
                            direction = "down";
                        }
                        if (downYValue > currentSwipeY) {
                            direction = "up";
                        }
                    }

//                    pixel2 = bitmap.getPixel((int)(pointX), (int)(pointY-dpToPixel(size)+dpToPixel(Y_DISPLACEMENT_VARIES)-dpToPixel(20)));
//                    redValue2 = Color.red(pixel2);
//                    blueValue2 = Color.blue(pixel2);
//                    greenValue2 = Color.green(pixel2);
//                    if(redValue2 ==255&& blueValue2 ==255&& greenValue2 ==255){
//                        bgColor="white";
//                        pixels.add(new Pixel((int)(pointX),(int)(pointY-dpToPixel(size)+dpToPixel(Y_DISPLACEMENT_VARIES)-dpToPixel(20))));
//                    }else if(redValue2 ==0&& blueValue2 ==0&& greenValue2 ==0){
//                        bgColor="black";
//                    }

                    path.lineTo(pointX, pointY);
//                    path2.lineTo((pointX),(pointY-dpToPixel(size)+dpToPixel(Y_DISPLACEMENT_VARIES)-dpToPixel(20)));
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            default: return false;
        }
        postInvalidate();
        return false;

    }
    @Override
    protected void onDraw(Canvas canvas) {
//        drawLetter(canvas,
//            selectedLetter,
//                startPixelX,
//                startPixelY);
        canvas.drawPath(path,brush);
//        canvas.drawPath(path2,brush2);
    }


    public void clear(){
        path=new Path();
        brush=new Paint();
        brush.setAntiAlias(true);
        brush.setColor(Color.MAGENTA);
        brush.setStyle(Paint.Style.STROKE);
        brush.setStrokeJoin(Paint.Join.ROUND);
        brush.setStrokeWidth(15f);

        brush2=new Paint();
        brush2.setAntiAlias(true);
        brush2.setColor(Color.BLUE);
        brush2.setStyle(Paint.Style.STROKE);
        brush2.setStrokeJoin(Paint.Join.ROUND);
        brush2.setStrokeWidth(15f);

    }

    public int pxTodp(int px)
    {
        return (int) (px / this.getResources().getDisplayMetrics().density);
    }
    public int dpToPixel(int dp){
        return (int) (dp * this.getResources().getDisplayMetrics().density);

    }

    public void drawLetter(Canvas canvas,String selectedLetter , float startPixelX,float startPixelY){
        configureData();
        size=pxTodp((int)(getContext().getResources().getDisplayMetrics().heightPixels/1.07)-(getContext().getResources().getDisplayMetrics().heightPixels/2));
        positionX=(getContext().getResources().getDisplayMetrics().widthPixels)/2;
        positionY=(getContext().getResources().getDisplayMetrics().heightPixels)/2;
        paintText = new Paint();
        paintText.setStyle(Paint.Style.FILL);
        paintText.setColor(Color.BLACK);
        paintText.setFakeBoldText(true);
        paintText.setTextSize(getTextSize(size));
        canvas.drawText(selectedLetter,startPixelX-dpToPixel(X_DISPLACEMENT),positionY-dpToPixel(20), paintText);

    }

    private float getTextSize(int dp){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dp,
                getResources().getDisplayMetrics());

    }

    private String drawCircleOnBitmapAndSave(float x,float y, Bitmap bitmap){
        Bitmap bitmapMutable=bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas=new Canvas(bitmapMutable);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(CIRCLE_WIDTH);
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        canvas.drawCircle(
                x,y,
                CIRCLE_RADIUS,
                paint);
        resultBitmaps.add(bitmapMutable);
        return storeImage(bitmapMutable);
    }


    public String storeImage(Bitmap bitmapImage) {
        File directory = getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        File myPath = new File(directory, System.currentTimeMillis() + "__.jpeg");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(myPath);
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            Log.d(TAG,"Save successfull");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG,"Error while saving file 1");

        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
                Log.d(TAG,"Error while saving file 2");

            }
        }
        return myPath.getAbsolutePath();
    }
    public Bitmap getBitmap(String path) {
        Bitmap bitmap = null;
        try {
            File f = new File(path);
            bitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public void savedResults(){
        for(int i=0;i<bitmapInputs.size();i++){
            results.add(
                    drawCircleOnBitmapAndSave(
                            spotted.get(i).getX(),
                            spotted.get(i).getY(),
                            bitmapInputs.get(i))
            );
            Error error=new Error(
                    spotted.get(i),
                    "Traced out",
                    resultBitmaps.get(i),
                    results.get(i)
            );
            errors.add(error);
        }
    }
    public void detectErrorsAndSave(){
        // This function spots the pixels where there are errors
        addSpotted();
        // This part add input bitmaps
        for(int i=0;i<spotted.size();i++){

            bitmapInputs.add(getBitmap(storeImage(getDrawingCache())));
        }
        //This function draw spots on image and saves it
        savedResults();
        Toast.makeText(getContext(),"Save Successfull",Toast.LENGTH_LONG).show();
    }
    private void addSpotted(){
        for(int i=0;i<pixels.size()-1;i++){
            Pixel pixel1=pixels.get(i);
            Pixel pixel2=pixels.get(i+1);
            if(distance(pixel1,pixel2)>=40){
                spotted.add(pixel2);
            }
        }
    }
    private int distance(Pixel pixel1,Pixel pixel2) {
        int x1 = pixel1.x;
        int y1 = pixel1.y;
        int x2 = pixel2.x;
        int y2 = pixel2.y;
        return (int) Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }
    public int getTotalErrorsForScore(){
        return errors.size();
    }
    public void configureData(){
        char selected=getSelectedLetter().charAt(0);
        Log.d(TAG,selected+"");
        switch (selected){
            case 'B': case 'D': case 'H':
            case 'I': case 'J': case 'K':
            case 'L': case 'M': case 'N':
            case 'P': case 'R': case 'T':
            case 'U': case 'V': case 'W':
            case 'X':case 'Y':case 'Z':
                X_DISPLACEMENT = 46;
                Y_DISPLACEMENT_VARIES = 100;
                break;
            case 'C': case 'E': case 'F':
            case 'G': case 'S':
                X_DISPLACEMENT = 46+46+46;
                Y_DISPLACEMENT_VARIES = 100;
                break;
            case 'A': case 'O': case 'Q':
                X_DISPLACEMENT = 46+46;
                Y_DISPLACEMENT_VARIES = 100;
                break;


        }
    }



    public class Pixel{
        private int x;
        private int y;

        public Pixel(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }



    class Error {
        private Pixel pixel;
        private String message;
        private Bitmap data;
        private String savedPath;


        public Error(Pixel pixel, String message, Bitmap data, String savedPath) {
            this.pixel = pixel;
            this.message = message;
            this.data = data;
            this.savedPath = savedPath;
        }

        public Pixel getPixel() {
            return pixel;
        }

        public void setPixel(Pixel pixel) {
            this.pixel = pixel;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Bitmap getData() {
            return data;
        }

        public void setData(Bitmap data) {
            this.data = data;
        }

        public String getSavedPath() {
            return savedPath;
        }

        public void setSavedPath(String savedPath) {
            this.savedPath = savedPath;
        }

    }
}