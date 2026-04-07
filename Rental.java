import java.util.Scanner;
import java.util.ArrayList;

abstract class Vehicle {
    String kode, nama;
    int harga;
    boolean tersedia = true;

    Vehicle(String kode, String nama, int harga) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
    }

    abstract String getTipe();

    int hitungSewa(int hari, boolean promo) {
        int total = harga * hari;
        if (promo) total = Math.max(0, total - getDiskon());
        return total;
    }

    abstract int getDiskon();
}

class Car extends Vehicle {
    Car(String kode, String nama, int harga) {
        super(kode, nama, harga);
    }

    String getTipe() { return "CAR"; }

    int getDiskon() { return 20000; }
}

class Bike extends Vehicle {
    Bike(String kode, String nama, int harga) {
        super(kode, nama, harga);
    }

    String getTipe() { return "BIKE"; }

    int getDiskon() { return 10000; }
}

public class Rental {

    static Vehicle cari(ArrayList<Vehicle> list, String kode) {
        for (Vehicle v : list)
            if (v.kode.equals(kode)) return v;
        return null;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(); input.nextLine();

        ArrayList<Vehicle> daftar = new ArrayList<>();

        while (n-- > 0) {
            String[] p = input.nextLine().split(" ");
            String aksi = p[0];

            if (aksi.equals("ADD")) {
                String tipe = p[1], kode = p[2], nama = p[3];
                int harga = Integer.parseInt(p[4]);

                if (cari(daftar, kode) != null) {
                    System.out.println("Kendaraan sudah terdaftar");
                } else {
                    Vehicle v = tipe.equals("CAR") ?
                        new Car(kode, nama, harga) :
                        new Bike(kode, nama, harga);

                    daftar.add(v);
                    System.out.println(tipe + " " + kode + " berhasil ditambahkan");
                }

            } else if (aksi.equals("RENT")) {
                String kode = p[1];
                int hari = Integer.parseInt(p[2]);
                boolean promo = p.length > 3;

                Vehicle v = cari(daftar, kode);

                if (v == null) {
                    System.out.println("Kendaraan tidak ditemukan");
                } else if (!v.tersedia) {
                    System.out.println("Kendaraan sedang disewa");
                } else {
                    int total = v.hitungSewa(hari, promo);
                    v.tersedia = false;
                    System.out.println("Total sewa " + kode + ": " + total);
                }

            } else if (aksi.equals("RETURN")) {
                String kode = p[1];
                Vehicle v = cari(daftar, kode);

                if (v == null) {
                    System.out.println("Kendaraan tidak ditemukan");
                } else if (v.tersedia) {
                    System.out.println("Kendaraan belum disewa");
                } else {
                    v.tersedia = true;
                    System.out.println(kode + " berhasil dikembalikan");
                }

            } else if (aksi.equals("DETAIL")) {
                String kode = p[1];
                Vehicle v = cari(daftar, kode);

                if (v == null) {
                    System.out.println("Kendaraan tidak ditemukan");
                } else {
                    String status = v.tersedia ? "TERSEDIA" : "DISEWA";
                    System.out.println(
                        v.kode + " | " + v.getTipe() + " | " + v.nama +
                        " | harga: " + v.harga + " | status: " + status
                    );
                }

            } else if (aksi.equals("COUNT")) {
                System.out.println("Total kendaraan: " + daftar.size());
            }
        }
    }
}