/**
 * Name : Martinus Lukas
 * Project Name : Console Task 
 * 
 */
package udemy.pemrogramanjavapzn.todolist;

import java.util.InputMismatchException;
import java.util.Scanner;

 public class App {

    private static Scanner scanner = new Scanner(System.in);
    private static String[] todos = new String[2];
    public static void main(String... args) {
        testViewTodoList();
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
                if(todos[i] == null) {
                    continue; // skip jika terdapat nilai null dalam array
                }
                
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
     */
    public static boolean addTodoList(String todo) {
        int index = indexAvailable(); 
        if(index >= 0) {
            todos[index] = todo;    
        } else {
            resize(); // tambah ukuran array jika kondisi diatas false
            index = indexAvailable();
            todos[index] = todo;    
        }
        return true;
    }

    public static void testAddTodoList() {
        addTodoList("Makanan");
        addTodoList("Minum");
        addTodoList("Tidur");
        addTodoList("Olahraga");
  
 
        for(String todo : todos) {
            System.out.println(todo);
        }
    }

    /**
     * TODO:
     * 1. Buat sebuah fungsi untuk menghapus 
     *    sebuah tugas di dalam daftar tugas
     * 2. Fungsi tersebut menerima inputan 
     *    berupa nomor urut didalam daftar
     *    tugas
     * 3. Jika nomor tersedia, maka proses
     *    untuk menghapus
     * 4. Jika tidak, maka berikan nilai
     *    boolean untuk dapat diproses pada
     *    fungsi view
     */
    public static boolean removeTodoList(int number) {
        int index = number - 1; // penyesuaian dengan index element pada array
        if(index >= todos.length || index < 0){
            return false;
        } 

        String[] temp = todos;
        todos = new String[todos.length - 1];
        System.arraycopy(temp, 0, todos, 0, index); // copy setengah element 
        System.arraycopy(temp, index + 1, todos, index, temp.length - index - 1); // copy sisa element

        return true;
    }

    private static void testRemoveTodoList() {
        addTodoList("Belajar Java");
        addTodoList("Belajar C#");
        addTodoList("Belajar Go");

        removeTodoList(4);
        removeTodoList(1);

        for(String todo : todos) {
            System.out.println(todo);
        }
    }
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
        int menu = 0;
        while(menu != 3) {
            try {
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
                menu = scanner.nextInt();
                scanner.nextLine();

                switch(menu) {
                    case 1:
                        viewAddTodoList();
                        break;
                    case 2:
                        viewRemoveTodoList();
                        break;
                    default :
                        System.out.println("See you next time!");
                        System.out.println("Bye... Bye.. Bye..");
                        exit();
                        break;    
                }
            } catch (InputMismatchException ie) {
                System.out.println("Please insert a correct number!");
                scanner.nextLine();
            }            
        }
    }

    // test 
    public static void testViewTodoList() {
        viewTodoList();
    }

    public static void viewAddTodoList() {
        delimiter('*', 22);
        System.out.println("~ Menu Add Todo ~");
        delimiter('*', 22);
        System.out.print("Add your todo : ");
        String todo = scanner.nextLine();
        
        if(addTodoList(todo)) {
            System.out.println("Todo has been added!");
        }
        
    }

    public static void viewRemoveTodoList() {
        System.out.println("Tampilan Menu Hapus Tugas!");
    }
    /**
     * Helper :
    * 1. Fungsi garis sederhana dengan character
    * 2. Fungsi untuk mengecek jika todolist kosong/tidak
    * 3. Fungsi pengecekan index yang tersedia dari todolist
    * 4. Fungsi untuk memperbesar ukuran todolist
    * 5. Fungsi untuk keluar dari program
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

    public static int indexAvailable() {
        int result = -1;
        for(int i = 0; i < todos.length; i++) {
            if(todos[i] == null) {
                result = i;
                break;
            }
        }

        return result;
    }

    /**
    * Fungsi ini digunakan untuk memperbesar
    * ukuran array dua kali lipat dengan
    * cara membuat sebuah array lain sebagai
    * penampung sementara.
    */
    public static void resize() {
        String[] temp = todos;
        todos = new String[todos.length + 1];

        System.arraycopy(temp, 0, todos, 0, temp.length);
        //     for(String todo : todos) {
        //     System.out.println(todo);
        // }
    }

    private static void exit() {
        System.exit(0);
    }
 }
 