import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class App {
    public static int[] createRandomArray(int arrayLength){
        Random rand = new Random();
        int[] arr = new int[arrayLength];

        for(int i=0; i<arrayLength;i++){
            arr[i] = rand.nextInt(101);
        }
        return arr;
    }

    public static void writeArrayToFile(int[] arr, String file) throws IOException{
        FileWriter writer = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(writer);

        for(int i=0;i<arr.length;i++){
            bw.write("%d".formatted(arr[i]));
            if(i != arr.length-1){
                bw.write("\n");
            }
        }
        bw.close();
    }

    public static int[] readFileToArray(String file) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(file));

        int cols = 0;
        String line;
        while((line = br.readLine()) != null){
            String[] elements = line.split(" ");
            cols = elements.length;
        }
        int[] arr = new int[cols];

        br.close();
        br = new BufferedReader(new FileReader(file));

        while((line = br.readLine()) != null){
            String[] elements = line.split(" ");
            for(int col=0;col<cols;col++){
                arr[col] = Integer.parseInt(elements[col]);
            }
        }
        br.close();

        return arr;
    }

    public static void bubbleSort(int[] arr){
        int temp;

        for(int i=0;i<arr.length-1;i++){
            for(int k=0;k<arr.length-i-1;k++){
                if(arr[k] > arr[k+1]){
                    temp = arr[k];
                    arr[k] = arr[k+1];
                    arr[k+1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter array length or file name: ");
        String usrIn;
        int[] arr;
        String file = "bubblesort.txt";
        
        if(scnr.hasNextInt()){
            usrIn = scnr.nextLine();
            int arrLength = Integer.parseInt(usrIn);

            arr = new int[arrLength];
            arr = createRandomArray(arrLength);
            System.out.println("Array before sort: " + Arrays.toString(arr));
            bubbleSort(arr);
            writeArrayToFile(arr, file);
        }else{
            usrIn = scnr.nextLine();
            arr = readFileToArray(usrIn);
            System.out.println("Array before sort: " + Arrays.toString(arr));
            bubbleSort(arr);
            writeArrayToFile(arr, file);
        }
        System.out.println("Array after sort: " + Arrays.toString(arr));
        System.out.println("Done.");
        scnr.close();
    }
}
