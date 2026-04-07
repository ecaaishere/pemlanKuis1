import java.util.Scanner;
import java.util.ArrayList;

abstract class Student {
    String nama;
    int saldo;

    Student(String nama) {
        this.nama = nama;
        this.saldo = 0;
    }

    void save(int jumlah) {
        saldo += jumlah;
    }

    abstract boolean take(int jumlah);
    abstract String getTipe();
}

class Reguler extends Student {
    Reguler(String nama) { super(nama); }

    boolean take(int jumlah) {
        if (saldo < jumlah) return false;
        saldo -= jumlah;
        return true;
    }

    String getTipe() { return "REGULER"; }
}

class Beasiswa extends Student {
    Beasiswa(String nama) { super(nama); }

    boolean take(int jumlah) {
        int potong = Math.max(0, jumlah - 1000);
        if (saldo < potong) return false;
        saldo -= potong;
        return true;
    }

    String getTipe() { return "BEASISWA"; }
}

public class Tabungan {

    static Student cari(ArrayList<Student> list, String nama) {
        for (Student s : list)
            if (s.nama.equals(nama)) return s;
        return null;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(); input.nextLine();

        ArrayList<Student> daftar = new ArrayList<>();

        while (n-- > 0) {
            String[] p = input.nextLine().split(" ");
            String aksi = p[0];
            String nama = (p.length > 1) ? p[1] : "";
            Student s = cari(daftar, nama);

            if (aksi.equals("CREATE")) {
                if (s != null) {
                    System.out.println("Akun sudah terdaftar");
                } else {
                    Student baru = p[1].equals("REGULER") ?
                        new Reguler(p[2]) : new Beasiswa(p[2]);
                    daftar.add(baru);
                    System.out.println(p[1] + " " + p[2] + " berhasil dibuat");
                }

            } else if (aksi.equals("SAVE")) {
                if (s == null) {
                    System.out.println("Akun tidak ditemukan");
                } else {
                    s.save(Integer.parseInt(p[2]));
                    System.out.println("Saldo " + s.nama + ": " + s.saldo);
                }

            } else if (aksi.equals("TAKE")) {
                if (s == null) {
                    System.out.println("Akun tidak ditemukan");
                } else if (!s.take(Integer.parseInt(p[2]))) {
                    System.out.println("Saldo " + s.nama + " tidak cukup");
                } else {
                    System.out.println("Saldo " + s.nama + ": " + s.saldo);
                }

            } else if (aksi.equals("CHECK")) {
                if (s == null) {
                    System.out.println("Akun tidak ditemukan");
                } else {
                    System.out.println(s.nama + " | " + s.getTipe() + " | saldo: " + s.saldo);
                }
            }
        }
    }
}