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
        viewTodoList();
    }

    // Model (business logic) ===============================================
    /**
     * TODO:
     * 1. Buat bisnis logic untuk menampilkan
     *    daftar tugas.
     * 2. Jika daftar tugas kosong, maka
     *    tampilkan pesan.
     * 3. Sedangkan jika terdapat daftar tugas
     *    maka tampilkan daftar tugas.
     */
    private static void showTodoList() {
        // reset jika item di todos sudah habis bukan null
        if(todos.length == 0) {
            todos = new String[2];
        }

        if(isEmpty()) {
            System.out.println("You haven't created a to-do list yet.");
        } else {
            for(int i = 0; i < todos.length; i++) {
                System.out.printf("%d. %s%n", (i + 1), todos[i]);
            }
        }
    }

    // test
    static void testShowTodoList() {
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
    private static boolean addTodoList(String todo) {
        int index = indexAvailable(); 
        if(index >= 0) {
            todos[index] = todo;    
        } else {
            resize(); // tambah ukuran array jika array penuh
            index = indexAvailable();
            todos[index] = todo;    
        }
        return true;
    }

    static void testAddTodoList() {
        addTodoList("Makanan");
        addTodoList("Minum");
        addTodoList("Tidur");
        addTodoList("Olahraga");
        showTodoList();
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
    private static boolean removeTodoList(int number) {
        int index = number - 1; // penyesuaian dengan index element pada array
        if(index >= todos.length || index < 0 || isNull(index)){
            return false;
        } 

        String[] temp = todos;
        todos = new String[todos.length - 1];
        System.arraycopy(temp, 0, todos, 0, index); // copy setengah element 
        System.arraycopy(temp, index + 1, todos, index, temp.length - index - 1); // copy sisa element

        return true;
    }

    static void testRemoveTodoList() {
        addTodoList("Belajar Java");
        removeTodoList(1);
        showTodoList();
    }

    // VIEW ==============================================================
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
                delimiter('=', 25);
                System.out.println("~ My TodoList ~");
                delimiter('=', 25);
                showTodoList();
                delimiter('=', 25);
                System.out.println("Menu :");
                System.out.println("1. Add to-do list.");
                System.out.println("2. Remove to-do list.");
                System.out.println("3. Exit.");
                System.out.print("Select menu (eq. 1,2,3): ");
                menu = scanner.nextInt();
                scanner.nextLine(); // konsumsi sisa whitespace dari enter

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

    static void testViewTodoList() {
        viewTodoList();
    }

    public static void viewAddTodoList() {
        delimiter('*', 25);
        System.out.println("~ Menu Add Todo ~");
        delimiter('*', 25);
        System.out.print("Add your todo: ");
        String todo = scanner.nextLine();
        
        if(addTodoList(todo)) {
            System.out.println("Todo has been added!");
        }
        
    }

    public static void viewRemoveTodoList() {
        delimiter('*', 25);
        System.out.println("~ Menu Remove Todo ~");
        delimiter('*', 25);
        System.out.print("Please select the one you want to delete: ");
        int item = scanner.nextInt();
        scanner.nextLine(); // konsumsi sisa whitespace dari enter

        if(removeTodoList(item)) {
            System.out.println("Todo with selection number has been deleted!");
        } else {
            System.out.println("Item does not exist with the number you entered!");
            System.out.println("Please try again...");
        }
    }
    /**
     * Helper :
    * 1. Fungsi garis sederhana dengan character
    * 2. Fungsi untuk mengecek jika todolist kosong/tidak
    * 3. Fungsi pengecekan index yang tersedia dari todolist
    * 4. Fungsi untuk memperbesar ukuran todolist
    * 5. Fungsi untuk keluar dari program
    * 6. Fungsi untuk mengecek jika item isinya kosong
    */

    private static void delimiter(char c, int length) {
        for(int i = 1; i <= length; i++) {
            System.out.print(c);
        }
        System.out.println();
    }

    private static boolean isEmpty() {
        return todos[0] == null;
    }

    private static int indexAvailable() {
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
    private static void resize() {
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

    private static boolean isNull(int index) {
        return todos[index] == null;
    }
 }
 