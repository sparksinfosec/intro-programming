import java.util.Scanner; import java.io.File; import java.io.IOException; import java.io.FileWriter; import java.io.BufferedWriter; import java.lang.Math;

class TrialGroup {

private String fileName = " ";
int count;
int sum; 
int sumOfSquares;

//Nothing in this yet need to make if you are not going to start with filename when you start each object (does not do anything)
TrialGroup(){
}

TrialGroup(String fileName, int count, int sum, int sumOfSquares){
    this.fileName = fileName;
    this.count = count;
    this.sum = sum;
    this.sumOfSquares = sumOfSquares;
}
        
TrialGroup(String startingFileName){
    fileName = startingFileName;
}
        
int getAverage(){
    return sum/count;
} 
    
public String getFileName(){
    return fileName;
}
    
double getStandardDeviation(){
    double variance = sumOfSquares / (count - 1);
    double sqrt = Math.sqrt(variance);
    double roundedSqrt = Math.round(sqrt *100)/100.0;
    return roundedSqrt;
}    
}

public class PeppersPillMill {

public static int readForLineCount(String fileName) throws IOException {
    Scanner fileInput = new Scanner(new File(fileName));
    int lineCount = 0;
    String currentLine = "";
    while(fileInput.hasNext()){
        lineCount++;
        currentLine = fileInput.nextLine(); 
    }
    return lineCount; 
}        
          
public static int readForSum(String fileName) throws IOException {
    Scanner fileInput = new Scanner(new File(fileName));
    int lineCount = 0;
    int currentSum = 0;
    while(fileInput.hasNext()){
        currentSum = currentSum + fileInput.nextInt();
    }
    return currentSum;
}       
      
public static int readForSquares(String fileName) throws IOException {
    Scanner fileInput = new Scanner(new File(fileName));
    int lineCount = 0;
    int currentLine = 0;
    int currentSquare = 0;
    int currentSum = 0;
    while(fileInput.hasNext()){
        lineCount++;
        currentLine = fileInput.nextInt();
        currentSquare = currentSquare + (currentLine * currentLine);
    }
    currentSum = readForSum(fileName);
    currentSum = currentSum * currentSum;
    currentSquare = currentSquare - (currentSum / lineCount);
        
        
    return currentSquare;
}

public static boolean compareGroups(int firstAverage, double firstStandardDeviation, int secondAverage, double secondStandardDeviation) {
    int differenceOfAverage = 0;
    if (firstAverage > secondAverage) {
        differenceOfAverage = firstAverage - secondAverage;
    }   else {
        differenceOfAverage = secondAverage - firstAverage;
    }
    boolean compareTwo = differenceOfAverage > firstStandardDeviation && differenceOfAverage > secondStandardDeviation;
    return compareTwo;
}
   

static public void main(String[] args) throws IOException {
    //try this in a method just (but not return!!)Maybe three method calls to return the names clean up the code.
    
    Scanner userInput = new Scanner(System.in);
    String firstFile = "";
    String secondFile = "";
    String thirdFile = "";
    String outputFileName = "";
    System.out.print("What is the first file name: ");
    firstFile = userInput.next();
    System.out.print("What is the second file name: ");
    secondFile = userInput.next();
    System.out.print("What is the third file name: ");
    thirdFile = userInput.next();
    System.out.print("What would you like the output file to be called?: ");
    outputFileName = userInput.next();
    // then take these and run them thru methods to get count, sum, and sum of squares then get that class created for each one. Then need to create the output This will be a method. 
    TrialGroup groupOne = new TrialGroup(firstFile); 
    TrialGroup groupTwo = new TrialGroup(secondFile);
    TrialGroup groupThree = new TrialGroup(thirdFile);
    // created the three classes and got the inputs. 
    
    //Getting the count for each file 
    groupOne.count = readForLineCount(groupOne.getFileName());
    groupTwo.count = readForLineCount(groupTwo.getFileName());
    groupThree.count = readForLineCount(groupThree.getFileName());
    
    // Getting the sum for each file. 
    groupOne.sum = readForSum(groupOne.getFileName());
    groupTwo.sum = readForSum(groupTwo.getFileName());
    groupThree.sum = readForSum(groupThree.getFileName());
    
    //Get sum of squares
    groupOne.sumOfSquares = readForSquares(groupOne.getFileName());
    groupTwo.sumOfSquares = readForSquares(groupTwo.getFileName());
    groupThree.sumOfSquares = readForSquares(groupThree.getFileName());
    
    // Still need a Boolean is this a method or can I do it directly in the file? 
    boolean firstCompare = compareGroups(groupOne.getAverage(), groupOne.getStandardDeviation(), groupTwo.getAverage(), groupTwo.getStandardDeviation());
    boolean secondCompare = compareGroups(groupOne.getAverage(), groupOne.getStandardDeviation(), groupThree.getAverage(), groupThree.getStandardDeviation());
    boolean thirdCompare = compareGroups(groupTwo.getAverage(), groupTwo.getStandardDeviation(), groupThree.getAverage(), groupThree.getStandardDeviation());
   
    
    //OUTPUT FILE 
    FileWriter outputFile = new FileWriter(outputFileName);
    BufferedWriter outputBuffer = new BufferedWriter(outputFile);
    
    outputBuffer.write("Statistics:\n");
    outputBuffer.write(groupOne.getFileName() + ": Average - " + groupOne.getAverage() + ", Standard Deviation - " + groupOne.getStandardDeviation() + "\n");
    outputBuffer.write(groupTwo.getFileName() + ": Average - " + groupTwo.getAverage() + ", Standard Deviation - " + groupTwo.getStandardDeviation() + "\n");
    outputBuffer.write(groupThree.getFileName() + ": Average - " + groupThree.getAverage() + ", Standard Deviation - " + groupThree.getStandardDeviation() + "\n");
    outputBuffer.write("Results:\n");
    outputBuffer.write(groupOne.getFileName() + " vs. " + groupTwo.getFileName() + ": " + firstCompare + "\n" ); 
    outputBuffer.write(groupOne.getFileName() + " vs. " + groupThree.getFileName() + ": " + secondCompare + "\n"  );
    outputBuffer.write(groupTwo.getFileName() + " vs. " + groupThree.getFileName() + ": " + thirdCompare + "\n");
    outputBuffer.close();        
}
} // Need to calculate the Sum of squares and the sum within the file read** Method?
