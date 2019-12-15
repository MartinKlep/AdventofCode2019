
public class SpaceImage{
    
    private String rawFile;
    private int width;
    private int height;
    private int layers;
    private int layerSize;
    private int[][][] pixels;
    
    public SpaceImage(int w, int h, String raw){
        this.width = w;
        this.height = h;
        this.rawFile = raw;
        this.layerSize = width*height;
        this.layers = rawFile.length() / layerSize;
        pixels = new int[layers][height][width];
        System.out.println("Pixels: " + rawFile.length() + " | W: " + width + " | H: " + height + " | Layers: " + layers + " | Lsize " + layerSize);
        System.out.println();
        int i = 0;
        for(int x = 0; x < layers; x++){
            for(int y = 0; y < height; y++){
                for(int z = 0; z < width; z++){
                    pixels[x][y][z] = Character.getNumericValue(rawFile.charAt(i));  
                    i++;
                    //System.out.println("Added " + rawFile.charAt(i) +" at [" + x +"][" + y + "][" + z + "]");
                }
            }
        }
        
    }

    public int countDigits(int digit, int [][] layer){
        int count = 0;
        for(int x = 0; x < height; x++){
            for(int y = 0; y < width; y++){
                if(layer[x][y] == digit){
                    count++;
                }
            }
        }
        return count;
    }

    public int[][] getLayerFewestZeros(){
        int fewest = width*height;
        int index = 0;
        for(int i = 0; i < layers; i++){
            int temp = countDigits(0, pixels[i]);
            if(temp < fewest){
                fewest = temp;
                index = i;
            }
        }
        
        return pixels[index];
    }

    public int oneXtwo(int[][] layer){
        int ones = countDigits(1, layer);
        int twos = countDigits(2, layer);
        return ones*twos;
    }

    public void printLayer(int lay){
        System.out.println("LAYER: " + lay);
        for(int i = 0; i < height; i++){
            for(int y = 0; y < width; y++){
                System.out.print(pixels[lay][i][y] + " ");
            }
            System.out.println();
        }
    }

    public void printLayer(int[][] lay){
        for(int i = 0; i < height; i++){
            for(int y = 0; y < width; y++){
                System.out.print(lay[i][y] + " ");
            }
            System.out.println();
        }
    }

    public void printPicture(){
        for(int i = 0; i < layers; i++){
            printLayer(i);
            System.out.println();
        }
    }
}