import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by renaud on 01/10/14.
 */
public class Main {


    static short rayon;
    static short negligable;
    static String nom_photo;
    static BufferedImage photo;



    public static void main(String[] args){

        ArrayList<Pixel> tab;
        if (args.length != 3){
            System.out.println("NB Args");
            System.exit(-1);
        }
        convert_Args(args);
        try {
            photo = ImageIO.read(new File(nom_photo));
        } catch (IOException e) {
            System.out.println("Erreur ouverture Image !");
        }
        tab = img_to_pixel(photo);
        System.out.println(union_find(tab));
    }


    public static void convert_Args(String[] args){
        nom_photo = args[0];
        try {
            rayon = Short.parseShort(args[1]);
            negligable = Short.parseShort(args[2]);
        }catch (NumberFormatException e){
            System.out.println("Entiers incorrect !");
            System.exit(-1);
        }

    }


    public static ArrayList<Pixel> img_to_pixel(BufferedImage image){
        ArrayList<Pixel> liste = new ArrayList<Pixel>();
        for(int x = 0; x< image.getWidth(); x++){
            for(int y = 0; y< image.getHeight(); y++){
                if(image.getRGB(x,y) != -1) liste.add(new Pixel(y, x));
            }
        }
        return liste;
    }


    public static short union_find(ArrayList<Pixel> p){
        for(int i = 0; i<p.size(); i++){
            Pixel un = p.get(i);
            for ( int j = i+1; j<p.size(); j++){
                Pixel deux = p.get(j);
                byte r_voi = un.r_voisins(deux, rayon);
                if(r_voi == 2) break;
                else if (r_voi == 1) un.union(deux);

            }
        }
        short nombre = 0;
        for(Pixel pix: p){
            if(pix == pix.getPere() && pix.getTaille() > negligable){
                nombre++;
            }
        }
        return nombre;
    }


    public static class Pixel{

        private int x;
        private int y;
        private Pixel pere;
        private int taille = 1;

        public Pixel(int x, int y){
            this.pere = this;
            this.x = x;
            this.y = y;
        }

        public byte r_voisins(Pixel p, int r){
            int Y = (this.y - p.y) * (this.y - p.y);
            int X = (this.x - p.x) * (this.x - p.x);
            if(Y > r){
                return 2;
            }
            else if (Y + X <= r){
                return 1;
            }
            return 0;
        }

        public Pixel root(){
            if(this.pere != this){
                this.pere = this.pere.root();
            }
            return this.pere;
        }

        public boolean find(Pixel p){
            return (this.root() == p.root());
        }

        public void union(Pixel p){
            Pixel rootT = this.root();
            Pixel rootP = p.root();

            if( rootP != rootT){
                if(rootP.getTaille() > rootT.getTaille()){
                    rootT.setPere(rootP);
                    rootP.setTaille(rootP.getTaille()+rootT.getTaille());
                }
                else{
                    rootP.setPere(rootT);
                    rootT.setTaille(rootT.getTaille()+rootP.getTaille());
                }
            }
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

        public Pixel getPere() {
            return pere;
        }

        public void setPere(Pixel pere) {
            this.pere = pere;
        }

        public int getTaille() {
            return taille;
        }

        public void setTaille(int taille) {
            this.taille = taille;
        }
    }
}
