import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tugas_Pertemuan_Akhir {
    
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        boolean masuk = true;

        while(masuk == true){
            
            bersih();
    
            System.out.println("=======================");
            System.out.println("==    Tugas Akhir    ==");
            System.out.println("=======================");
            System.out.println("== 1. Program Method ==");
            System.out.println("== 2. Program Nilai  ==");
            System.out.println("== 0. Keluar         ==");
            System.out.println("=======================");
            System.out.print("== Masukkan pilihan : ");
    
            String menu = br.readLine();
            switch (menu) {
                case "1" :
                    metode();
                    break;
                case "2" :
                    nilai();
                    break;
                case "0" :
                    keluar();
                    masuk = false;
                    break;
                default :
                    inputan_salah();
                    break;
            }
        }
        
    }

    static void metode() throws IOException{
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        bersih();
        System.out.println("=============================================================================");
        System.out.println("==     JavaScript adalah bahasa pemrograman tingkat tinggi dan dinamis     ==");
        System.out.println("=============================================================================");
        System.out.print("== Lanjut? (y/n) : ");
        String menu = br.readLine();
        switch (menu) {
            case "y":
                bersih();
                System.out.println("==================================================");
                metode1();
                metode2();
                metode3();
                metode4();
                metode5();
                System.out.println("==================================================");
                lanjutan();
                break;
            case "n":
                break;
            default:
                bersih();
                System.out.println("===========================");
                System.out.println("==     Inputan Salah     ==");
                System.out.println("===========================");
                lanjutan();
                break;
        }
    }

    static void nilai() throws IOException{
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String matakuliah;
        int kehadiran;
        float Nilai_Kehadiran;
        float Nilai_UTS;
        float Nilai_UAS;
        double hasil;
        String grade;

        bersih();
    
        System.out.println("========================");
        System.out.println("==  Nilai Skor Akhir  ==");
        System.out.println("========================");
        System.out.print("== Masukkan Nama Mata Kuliah : ");
        matakuliah = br.readLine();

        bersih();

        System.out.println("========================");
        System.out.println("==  Nilai Skor Akhir  ==");
        System.out.println("========================");
        System.out.print("== Masukkan banyak kehadiran : ");
        kehadiran = Integer.parseInt(br.readLine());
        if (kehadiran < 3 ){
            Nilai_Kehadiran = 0;
        } else if(kehadiran == 3 || kehadiran == 4){
            Nilai_Kehadiran = 60;
        } else if(kehadiran == 5 || kehadiran == 6){
            Nilai_Kehadiran = 80;
        } else {
            Nilai_Kehadiran = 100;
        }

        bersih();

        System.out.println("========================");
        System.out.println("==  Nilai Skor Akhir  ==");
        System.out.println("========================");
        System.out.print("== Masukkan Nilai UTS : ");
        Nilai_UTS = Integer.parseInt(br.readLine());

        bersih();

        System.out.println("========================");
        System.out.println("==  Nilai Skor Akhir  ==");
        System.out.println("========================");
        System.out.print("== Masukkan Nilai UAS : ");
        Nilai_UAS = Integer.parseInt(br.readLine());
        
        hasil = (0.2 * Nilai_Kehadiran) + (0.3 * Nilai_UTS) + (0.5 * Nilai_UAS);
        
        if (hasil >= 85){
            grade = "A";
        } else if(hasil >= 70){
            grade = "B";
        } else if(hasil >= 60){
            grade = "C";
        } else if(hasil >= 50){
            grade = "D";
        } else{
            grade = "E";
        }

        bersih();
    
        System.out.println("==============================");
        System.out.println("== Nilai Mata Kuliah " + matakuliah);
        System.out.println("==============================");
        System.out.println("== Nilai Kehadiran : " + Nilai_Kehadiran);
        System.out.println("== Nilai UTS       : " + Nilai_UTS);
        System.out.println("== Nilai UAS       : " + Nilai_UAS);
        System.out.println("==============================");
        lanjutan();

        bersih();
    
        System.out.println("==============================");
        System.out.println("== Skor Akhir " + matakuliah);
        System.out.println("==============================");
        System.out.println("== Nilai Akhir : " +  hasil);
        System.out.println("== Grade       : " +  grade);
        System.out.println("==============================");
        lanjutan();
    }

    static void metode1(){
        System.out.println("==     JavaScript adalah bahasa pemrograman     ==");
    }

    static void metode2(){
        System.out.println("==     javascript adalah bahasa pemrograman     ==");
    }

    static void metode3(){
        System.out.println("==     JAVASCRIPT ADALAH BAHASA PEMROGRAMAN     ==");
    }

    static void metode4(){
        System.out.println("==                  pemrograman                 ==");
    }

    static void metode5(){
        System.out.println("==          tingkat tinggi dan dinamis          ==");
    }

    static void keluar(){
        bersih();
        System.out.println("==========================");
        System.out.println("==     Terima Kasih     ==");
        System.out.println("==========================");
    }

    static void inputan_salah(){
        bersih();
        System.out.println("===========================");
        System.out.println("==     Inputan Salah     ==");
        System.out.println("===========================");
        lanjutan();
    }

    static void bersih(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();    
    }

    static void lanjutan(){
        System.out.print("== Tekan ENTER...");
        Scanner enter = new Scanner(System.in);
        enter.nextLine();
    }
    
}
