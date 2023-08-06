/**
 * Name : Martinus Lukas
 * Project Name : Console Task 
 * 
 */
package udemy.pemrogramanjavapzn.todolist;

import java.util.Scanner;

 public class App {

    private static Scanner scanner = new Scanner(System.in);
    private static String[] todos = new String[10];
    public static void main(String... args) {
        testShowTodoList();
    }

    // Controller (bussines logic)
    /**
     * TODO:
     * 1. Buat bisnis logic untuk menampilkan
     *    daftar tugas.
     * 2. Jika daftar tugas kosong, maka
     *    tampilkan pesan.
     * 3. Sedangkan jika terdapat daftar tugas
     *    maka tampilkan daftar tugas.
     */
    public static void showTodoList() {
        if(isEmpty()) {
            System.out.println("You haven't created a to-do list yet.");
        } else {
            for(int i = 0; i < todos.length; i++) {
                System.out.printf("%d. %s%n", (i + 1), todos[i]);
            }
        }
    }

    // test
    public static void testShowTodoList() {
        showTodoList();
    }

    /**
     * TODO:
     * 1. Buat bisnis logic untuk menambahkan
     *    satu tugas kedalam daftar tugas
     * 2. Sebelum tugas dimasukkan, periksa 
     *    terlebih dahulu daftar tugas tersebut
     * 3. Jika daftar tugas penuh, tambahkan 
     *    kapasistas daftar tugas tersebut
     *    menjadi 2 kali lipat
     * 4. Jika daftar tugas belum penuh maka
     *    lakukan penginputan tugas
     * 5. Jika berhasil diinputkan, tampilkan 
     *    pesan berhasil
     */

    // VIEW
    /**
     * TODO:
     * 1. Buat sebuah tampilan awaasl untuk menu.
     * 2. Menu berisi daftar tugas dan pilihan 
     *    lainnya.
     * 3. Tampilkan pilihan menu dengan menggunakan
     *    angka.
     */
     public static void viewTodoList() {
        delimiter('=', 22);
        System.out.println("~ My TodoList ~");
        delimiter('=', 22);
        showTodoList();
        delimiter('=', 22);
        System.out.println("Menu :");
        System.out.println("1. Add to-do list.");
        System.out.println("2. Remove to-do list.");
        System.out.println("3. Exit.");
        System.out.print("Select menu (eq. 1,2,3): ");
     }

     // test 
     public static void testViewTodoList() {
        viewTodoList();
     }

     public static void viewAddTodoList() {

     }
     /**
      * Helper :
      * 1. Fungsi garis sederhana dengan character
      * 2. Fungsi untuk mengecek jika todolist kosong/tidak
      * 3. Fungsi untuk inputan berupa angka
      */

     public static void delimiter(char c, int length) {
        for(int i = 1; i <= length; i++) {
            System.out.print(c);
        }
        System.out.println();
     }

     public static boolean isEmpty() {
        return todos[0] == null;
     }

     public static int getInt(Scanner sc) {
        while(!sc.hasNextInt()) {
            System.out.println("Please insert a correct number!");
            sc.nextLine();
        }

        return scanner.nextInt();
     } 
 }
 